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
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/productos")
public class PreciosController {

    @Value("${info.app.version}")
    private String appVersion;

    @Autowired
    private Util util;

    @Autowired
    private PreciosService preciosService;

    @Autowired
    private Logger logger;

    @PostMapping("/precios")
    @ResponseStatus(HttpStatus.OK)
    public ResponseService actualizaProductos(@RequestBody RequestPrecios request) throws Exceptions {
        DatosKibana datosKibana = new DatosKibana();
        try {
            datosKibana.setDatosPeticion(Constantes.CONSULTA_ALL);
            Object resultant = preciosService.actualizaProductos(request);

            datosKibana.setRespuestaObtenida(request);

            logger.trace(this.getClass(), Nivel.INFORMATIVO, Constantes.SUCCESS_OPERATION, datosKibana);

            return new ResponseService(HttpStatus.OK, util.getCodigo(), Constantes.SUCCESS_OPERATION, util.getFolio(),
                    resultant);
        } catch (ExceptionAPI e) {
            datosKibana.setRespuestaObtenida(e.getMessage());
            logger.trace(this.getClass(), Nivel.ERROR, Constantes.SUCCESS_OPERATION, datosKibana);
            throw e;
        } catch (Exception ex) {
            datosKibana.setRespuestaObtenida(ex.getMessage());
            logger.trace(this.getClass(), Nivel.ERROR, Constantes.SUCCESS_OPERATION, datosKibana);

            throw new Exceptions(ex);
        }
    }

    @PostMapping("/log")
    @ResponseStatus(HttpStatus.OK)
    public ResponseService log(@RequestBody RequestLog request) throws Exceptions {
        DatosKibana datosKibana = new DatosKibana();
        try {
            datosKibana.setDatosPeticion(request);
            Object resultant = preciosService.log(request);

            logger.trace(this.getClass(), Nivel.INFORMATIVO, Constantes.SUCCESS_OPERATION, datosKibana);

            return new ResponseService(HttpStatus.OK, util.getCodigo(), Constantes.SUCCESS_OPERATION, util.getFolio(),
                    resultant);
        } catch (ExceptionAPI e) {
            datosKibana.setRespuestaObtenida(e.getMessage());
            logger.trace(this.getClass(), Nivel.ERROR, Constantes.SUCCESS_OPERATION, datosKibana);
            throw e;
        } catch (Exception ex) {
            datosKibana.setRespuestaObtenida(ex.getMessage());
            logger.trace(this.getClass(), Nivel.ERROR, Constantes.SUCCESS_OPERATION, datosKibana);

            throw new Exceptions(ex);
        }
    }
}
