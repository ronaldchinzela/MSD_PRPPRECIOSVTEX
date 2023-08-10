package com.gruposalinas.msdprppreciosvtex.controller;


import com.gruposalinas.msdprppreciosvtex.common.Constantes;
import com.gruposalinas.msdprppreciosvtex.common.Util;
import com.gruposalinas.response.model.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/status")
public class StatusController {

    @Value("${info.app.version}")
    private String appVersion;

    @Autowired
    private Util util;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseService getStatus() {
        Map<String, String> status = Map.of("aplicacion", Constantes.NOMBRE_MSD, "appVersion", appVersion);
        return new ResponseService(HttpStatus.OK, util.getCodigo(), Constantes.SUCCESS_OPERATION, util.getFolio(),
                status);
    }
}
