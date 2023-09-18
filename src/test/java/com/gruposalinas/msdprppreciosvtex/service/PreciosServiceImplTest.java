package com.gruposalinas.msdprppreciosvtex.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.gruposalinas.msdprppreciosvtex.common.Exceptions;
import com.gruposalinas.msdprppreciosvtex.common.Util;
import com.gruposalinas.msdprppreciosvtex.dao.PreciosDao;
import com.gruposalinas.msdprppreciosvtex.model.request.Item;
import com.gruposalinas.msdprppreciosvtex.model.request.RequestLog;
import com.gruposalinas.msdprppreciosvtex.model.request.RequestPrecios;
import com.gruposalinas.msdprppreciosvtex.model.response.ResponseProductos;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class PreciosServiceImplTest {

  @Mock
  private PreciosDao catalogoPedidoDao;

  @InjectMocks
  private PreciosServiceImpl preciosService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testActualizaProductos() throws Exceptions {
    RequestPrecios request = new RequestPrecios("valorEjemplo");
    List<Long> valores = Arrays.asList(1L, 2L, 3L);
    ResponseProductos respuestaEsperada = new ResponseProductos(valores);
    when(catalogoPedidoDao.actualizaProductos(request)).thenReturn(respuestaEsperada);

    ResponseProductos respuestaActual = preciosService.actualizaProductos(request);
    assertEquals(respuestaEsperada, respuestaActual);
  }

  @Test
  void testLog() throws Exceptions {

    Util util = new Util("nombreDeEjemplo", "idDeEjemplo");

    String valor1 = util.getFolio();
    String valor2 = util.getCodigo();

    List<Item> items = Arrays.asList(new Item(1L, "valor1"), new Item(2L, "valor2"));

    RequestLog requestLog = new RequestLog(items);

    Map<String, Object> someMap = new HashMap<>();
    someMap.put("clave", "valor");

    when(catalogoPedidoDao.log(requestLog)).thenReturn(someMap);

    Map<String, Object> result = preciosService.log(requestLog);

    assertNotNull(result);

  }


}

