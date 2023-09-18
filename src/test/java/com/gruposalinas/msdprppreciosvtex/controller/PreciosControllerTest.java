package com.gruposalinas.msdprppreciosvtex.controller;

import com.gruposalinas.log.common.Nivel;
import com.gruposalinas.log.dto.DatosKibana;
import com.gruposalinas.log.service.Logger;
import com.gruposalinas.msdprppreciosvtex.common.Constantes;
import com.gruposalinas.msdprppreciosvtex.common.Exceptions;
import com.gruposalinas.msdprppreciosvtex.common.Util;
import com.gruposalinas.msdprppreciosvtex.model.request.Item;
import com.gruposalinas.msdprppreciosvtex.model.request.RequestLog;
import com.gruposalinas.msdprppreciosvtex.model.request.RequestPrecios;
import com.gruposalinas.msdprppreciosvtex.model.response.ResponseProductos;
import com.gruposalinas.msdprppreciosvtex.service.PreciosService;
import com.gruposalinas.response.exception.ExceptionAPI;
import com.gruposalinas.response.model.ResponseService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.util.*;

import static com.jayway.jsonpath.internal.path.PathCompiler.fail;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class PreciosControllerTest {
  @InjectMocks
  private PreciosController preciosController;

  @Mock
  private Util util;

  @Mock
  private PreciosService preciosService;

  @Mock
  private Logger logger;

  private RequestPrecios requestPrecios;
  private RequestLog requestLog;
  private HashMap<String, Object> mockHashMap;
  private ExceptionAPI badExceptionAPI;

  @BeforeEach
  public void setUp() {
    mockHashMap = new HashMap<>();
    mockHashMap.put("clave1", "valor1");
    mockHashMap.put("clave2", "valor2");
    when(util.getCodigo()).thenReturn("simulatedCode");
    when(util.getFolio()).thenReturn("simulatedFolio");
    badExceptionAPI = new ExceptionAPI(
            HttpStatus.BAD_REQUEST,
            "23",
            "Bad Request",
            "100",
            List.of(1, 2, 3)
    );
    requestPrecios = new RequestPrecios("2023-05-01 08:22:12");
    List<Item> itemList = new ArrayList<>();
    requestLog = new RequestLog(itemList);
  }


  @Test
  void testActualizaProductos() throws Exception {
    ResponseProductos responseProductos = new ResponseProductos(Collections.singletonList(123L));
    when(preciosService.actualizaProductos(requestPrecios)).thenReturn(responseProductos);

    ResponseService response = preciosController.actualizaProductos(requestPrecios);

    assertEquals(responseProductos, response.getResultado());
    verify(preciosService).actualizaProductos(requestPrecios);
  }

  @Test
  void testLog() throws Exception {
    Map<String, Object> expectedResponse = new HashMap<>();
    expectedResponse.put("skus", Collections.singletonList(123L));

    when(preciosService.log(requestLog)).thenReturn(expectedResponse);

    ResponseService response = preciosController.log(requestLog);

    assertEquals(expectedResponse, response.getResultado());
    verify(preciosService).log(requestLog);
  }

  @Test
  void testHandleRequest() throws Exception {
    ResponseProductos expectedResponse = new ResponseProductos(Collections.singletonList(123L));
    when(preciosService.actualizaProductos(any(RequestPrecios.class))).thenReturn(expectedResponse);

    String operation = "actualizaProductos";
    ResponseService response = preciosController.handleRequest(requestPrecios, operation);

    assertEquals(expectedResponse.getSkus(), ((ResponseProductos) response.getResultado()).getSkus());
    verify(preciosService).actualizaProductos(any(RequestPrecios.class));
  }

  @Test
  void testHandleRequestWithExceptions() throws Exception {
    when(preciosService.actualizaProductos(any(RequestPrecios.class))).thenThrow(badExceptionAPI);

    String operation = "actualizaProductos";
    try {
      preciosController.handleRequest(requestPrecios, operation);
      fail("Se esperaba una excepción ExceptionAPI");
    }
    catch (ExceptionAPI e) {
      verify(logger, times(1)).trace(eq(PreciosController.class), eq(Nivel.ERROR), eq(Constantes.SUCCESS_OPERATION), any(DatosKibana.class));
      assertEquals(e.getMessage(), "Bad Request");
    }
    catch (Exception ex) {
      fail("Se esperaba una excepción ExceptionAPI, pero se lanzó otra excepción: " + ex.getMessage());
    }
  }

  @Test
  void testHandleRequestWithGenericException() {
    doNothing().when(logger).trace(any(), eq(Nivel.ERROR), eq(Constantes.SUCCESS_OPERATION), any(DatosKibana.class));
    doThrow(new RuntimeException("Mensaje de excepción diferente")).when(logger).trace(any(), eq(Nivel.ERROR), eq(Constantes.SUCCESS_OPERATION), any(DatosKibana.class));

    assertThrows(RuntimeException.class, () -> preciosController.handleRequest(null, "operacion"));
    verify(logger, times(1)).trace(any(), eq(Nivel.ERROR), eq(Constantes.SUCCESS_OPERATION), any(DatosKibana.class));
  }

  @Test
  void testHandleRequestConExceptions() throws Exceptions {
    doNothing().when(logger).trace(any(), eq(Nivel.ERROR), eq(Constantes.SUCCESS_OPERATION), any(DatosKibana.class));

    doThrow(new ExceptionAPI(HttpStatus.BAD_REQUEST, "23", "Bad Request", "100", List.of(1, 2, 3)))
            .when(preciosService).actualizaProductos(any(RequestPrecios.class));

    assertThrows(com.gruposalinas.response.exception.ExceptionAPI.class, () -> preciosController.handleRequest(new RequestPrecios("2023-05-01 08:22:12"), "actualizaProductos"));
    verify(logger, times(1)).trace(any(), eq(Nivel.ERROR), eq(Constantes.SUCCESS_OPERATION), any(DatosKibana.class));
  }

  @Test
  void testHandleRequestWithTracesAndExceptions() throws Exceptions {
    doThrow(new RuntimeException("Mensaje de excepción diferente")).when(preciosService).actualizaProductos(any(RequestPrecios.class));

    Exception ex = assertThrows(Exceptions.class, () -> {
      preciosController.actualizaProductos(new RequestPrecios("2023-05-01 08:22:12"));
    });

    assertEquals("Mensaje de excepción diferente", ex.getCause().getMessage());

    verify(logger, times(1)).trace(any(), eq(Nivel.ERROR), eq(Constantes.SUCCESS_OPERATION), any(DatosKibana.class));
  }


}