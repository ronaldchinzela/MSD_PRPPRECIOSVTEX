package com.gruposalinas.msdprppreciosvtex.dao;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.*;

import com.gruposalinas.log.service.Logger;

import com.gruposalinas.msdprppreciosvtex.common.Constantes;
import com.gruposalinas.msdprppreciosvtex.common.Exceptions;
import com.gruposalinas.msdprppreciosvtex.model.request.Item;
import com.gruposalinas.msdprppreciosvtex.model.request.RequestLog;

import com.gruposalinas.msdprppreciosvtex.model.request.RequestPrecios;
import com.gruposalinas.msdprppreciosvtex.model.response.ResponseProductos;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PreciosDaoImplTest {
  private PreciosDaoImpl preciosDao;

  @Mock
  private JdbcTemplate jdbcTemplate;
  @Mock
  private Logger log;

  @BeforeEach
  public void setUp() throws SQLException {
    DatabaseMetaData metaData = mock(DatabaseMetaData.class);
    Connection con = mock(Connection.class);
    DataSource ds = mock(DataSource.class);

    when(con.getMetaData()).thenReturn(metaData);
    when(ds.getConnection()).thenReturn(con);
    when(jdbcTemplate.getDataSource()).thenReturn(ds);

    doNothing().when(log).debug(any());

    preciosDao = new PreciosDaoImpl(jdbcTemplate, log);
  }

  @Test
  void testActualizaProductosSuccess() throws SQLException {

    RequestPrecios request = new RequestPrecios("2023-09-10");

    Map<String, Object> response = new HashMap<>();
    response.put(Constantes.PA_FECHAS, "2023-09-10");
    response.put(Constantes.PA_COERRMSG, "Operación exitosa");
    response.put(Constantes.PA_IOCERR, 0);

    List<Map<String, Object>> itemsList = new ArrayList<>();
    Map<String, Object> item1 = new HashMap<>();
    item1.put(Constantes.SKU_ELEMENT_SP, 12345L); // SKU simulado
    itemsList.add(item1);
    response.put(Constantes.PA_TAB_DATA, itemsList);

    when(jdbcTemplate.call(any(), any())).thenReturn(response);

    ResponseProductos result = null;
    try {
      result = preciosDao.actualizaProductos(request);
    }
    catch (Exceptions ex) {
      fail("Se generó una excepción inesperada: " + ex.getMessage());
    }

    assertNotNull(result);

    assertNotNull(result.getSkus());
    assertFalse(result.getSkus().isEmpty());

    verify(log, atLeastOnce()).debug(any());

    verify(jdbcTemplate, times(1)).call(any(), any());
  }

  @Test
  void testLogSuccess() throws Exceptions {

    List<Item> items = new ArrayList<>();
    items.add(new Item(12345, "Mensaje 1"));
    items.add(new Item(67890, "Mensaje 2"));
    RequestLog request = new RequestLog(items);

    Map<String, Object> response = new HashMap<>();
    response.put(Constantes.PA_IOCERR, 0);
    response.put(Constantes.PA_COERRMSG, "Operación exitosa");

    when(jdbcTemplate.call(any(), any())).thenReturn(response);

    try {
      preciosDao.log(request);
    }
    catch (Exceptions ex) {
      fail("Se generó una excepción inesperada: " + ex.getMessage());
    }

    verify(log, atLeastOnce()).debug(any());

    verify(jdbcTemplate, times(1)).call(any(), any());
  }

  @Test
  void testExceptionHandling() {
    List<Item> items = new ArrayList<>();
    items.add(new Item(12345, "Mensaje 1"));
    items.add(new Item(67890, "Mensaje 2"));
    RequestLog request = new RequestLog(items);

    doThrow(new RuntimeException("Simulated Exception")).when(jdbcTemplate).call(any(), any());

    try {
      preciosDao.log(request);
      fail("Se esperaba una excepción de tipo Exceptions");
    }
    catch (Exceptions ex) {
      assertNotNull(ex);
      assertEquals("Simulated Exception", ex.getCause().getMessage());
    }

    verify(log, times(2)).debug(any());

    verify(jdbcTemplate, times(1)).call(any(), any());
  }

  @Test
  void testCatchBlock() {
    RequestPrecios request = new RequestPrecios("2023-05-01 08:22:12");
    request.setFechaHoraEjecucion("2023-09-07");

    Exceptions exception = assertThrows(Exceptions.class, () -> {
      preciosDao.actualizaProductos(request);
    });

  }

}