package com.gruposalinas.msdprppreciosvtex.configuration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.gruposalinas.log.common.Nivel;
import com.gruposalinas.response.exception.AppSetupException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.gruposalinas.log.service.Logger;
import com.gruposalinas.msdprppreciosvtex.common.Constantes;
import com.gruposalinas.msdprppreciosvtex.common.Util;
import com.gruposalinas.msdprppreciosvtex.dao.StatusDao;
import org.springframework.http.HttpStatus;

@SpringBootTest
class ApplicationSetupTest {
  @Mock
  private Logger mockLogger;

  @Mock
  private Util mockUtil;

  @Mock
  private StatusDao mockStatusDao;

  private ApplicationSetup applicationSetup;
  @MockBean
  private StatusDao statusdao;

  @MockBean
  private Util util;

  @Test
  void initTest() {
    when(util.getFolio()).thenReturn("X");
    when(statusdao.getStatus()).thenReturn("X");
    when(util.getCodigo()).thenReturn(".".concat(Constantes.NOMBRE_MSD).concat(".").concat(Constantes.ID_MSD.toString()));
    assertEquals("X", statusdao.getStatus());
  }

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    applicationSetup = new ApplicationSetup("1.0", mockLogger, mockUtil, mockStatusDao);
  }

  @Test
  public void testInitSuccess() {
    when(mockStatusDao.getStatus()).thenReturn("Connected");
    assertDoesNotThrow(() -> applicationSetup.init());
    verify(mockLogger).trace(eq(ApplicationSetup.class), eq(Nivel.INFORMATIVO), anyString());
  }

  @Test
  public void testInitFailure() {
    when(mockStatusDao.getStatus()).thenThrow(new RuntimeException("Simulated error"));
    AppSetupException exception = assertThrows(AppSetupException.class, () -> applicationSetup.init());
    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, exception.getStatus());
    verify(mockLogger).trace(eq(ApplicationSetup.class), eq(Nivel.ERROR), anyString());
  }

  @Test
  public void testGetLog() {
    Logger result = applicationSetup.getLog();
    assertNotNull(result);
    assertEquals(mockLogger, result);
  }

  @Test
  public void testGetUtil() {
    Util result = applicationSetup.getUtil();
    assertNotNull(result);
    assertEquals(mockUtil, result);
  }

  @Test
  public void testGetStatusDao() {
    StatusDao result = applicationSetup.getStatusDao();
    assertNotNull(result);
    assertEquals(mockStatusDao, result);
  }
}
