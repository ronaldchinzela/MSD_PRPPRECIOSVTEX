package com.gruposalinas.msdprppreciosvtex.controller;


import com.gruposalinas.log.common.Nivel;
import com.gruposalinas.log.dto.DatosKibana;
import com.gruposalinas.log.service.Logger;
import com.gruposalinas.msdprppreciosvtex.common.Constantes;
import com.gruposalinas.msdprppreciosvtex.common.Exceptions;
import com.gruposalinas.msdprppreciosvtex.common.Util;
import com.gruposalinas.msdprppreciosvtex.model.request.RequestLog;
import com.gruposalinas.msdprppreciosvtex.model.request.RequestPrecios;
import com.gruposalinas.msdprppreciosvtex.service.PreciosService;
import com.gruposalinas.response.exception.ExceptionAPI;
import com.gruposalinas.response.model.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/productos")
public class PreciosController {

  private final String appVersion;
  private final Util util;
  private final PreciosService preciosService;
  private final Logger logger;

  @Autowired
  public PreciosController(
    @Value("${info.app.version}") String appVersion,
    Util util,
    PreciosService preciosService,
    Logger logger) {
    this.appVersion = appVersion;
    this.util = util;
    this.preciosService = preciosService;
    this.logger = logger;
  }

  ResponseService handleRequest(Object requestObject, String operation) throws ExceptionAPI, Exceptions {
    DatosKibana datosKibana = new DatosKibana();
    try {
      datosKibana.setDatosPeticion(requestObject);
      Object resultant = null;

      if ("actualizaProductos".equals(operation)) {
        resultant = preciosService.actualizaProductos((RequestPrecios) requestObject);
      }
      else if ("log".equals(operation)) {
        resultant = preciosService.log((RequestLog) requestObject);
      }
      else {
        throw new IllegalArgumentException("Operación no válida: " + operation);
      }

      datosKibana.setRespuestaObtenida(requestObject);

      logger.trace(this.getClass(), Nivel.INFORMATIVO, Constantes.SUCCESS_OPERATION, datosKibana);

      return new ResponseService(HttpStatus.OK, util.getCodigo(), Constantes.SUCCESS_OPERATION, util.getFolio(),
        resultant);
    }
    catch (ExceptionAPI e) {
      datosKibana.setRespuestaObtenida(e.getMessage());
      logger.trace(this.getClass(), Nivel.ERROR, Constantes.SUCCESS_OPERATION, datosKibana);
      throw e;
    }
    catch (Exception ex) {
      datosKibana.setRespuestaObtenida(ex.getMessage());
      logger.trace(this.getClass(), Nivel.ERROR, Constantes.SUCCESS_OPERATION, datosKibana);
      throw new Exceptions(ex);
    }
  }

  @PostMapping("/precios")
  @ResponseStatus(HttpStatus.OK)
  public ResponseService actualizaProductos(@RequestBody RequestPrecios request) throws Exceptions {
    return handleRequest(request, "actualizaProductos");
  }

  @PostMapping("/log")
  @ResponseStatus(HttpStatus.OK)
  public ResponseService log(@RequestBody RequestLog request) throws Exceptions {
    return handleRequest(request, "log");
  }

}
