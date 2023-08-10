package com.gruposalinas.msdprppreciosvtex.configuration;

import javax.annotation.PostConstruct;

import com.gruposalinas.msdprppreciosvtex.common.Constantes;
import com.gruposalinas.msdprppreciosvtex.common.Util;
import com.gruposalinas.msdprppreciosvtex.dao.StatusDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

import com.gruposalinas.log.common.Nivel;
import com.gruposalinas.log.service.Logger;


import com.gruposalinas.response.exception.AppSetupException;

import lombok.Getter;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Getter
@Configuration
public class ApplicationSetup {

    @Value("${info.app.version}")
    private String appVersion;

    @Autowired
    private Logger log;

    @Autowired
    private Util util;

    @Autowired
    private StatusDao statusDao;

    @PostConstruct
    public void init() {
        try {
            StringBuilder application = new StringBuilder();
            application.append("****************** Configurando ");
            application.append(Constantes.NOMBRE_MSD);
            application.append(" ");
            application.append(getAppVersion());
            application.append(" ******************");
            application.append(" Conexion  DataBase:");
            application.append(statusDao.getStatus());

            log.trace(getClass(), Nivel.INFORMATIVO, application.toString());
        } catch (Exception e) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Fallo el Setup de la Aplicacion. Extra Message: ");
            stringBuilder.append(e);
            stringBuilder.append("============>>>>>>> Error en el Setup de la Aplicacion <<<<<<<============");
            log.trace(ApplicationSetup.class, Nivel.ERROR, stringBuilder.toString());
            throw new AppSetupException(HttpStatus.INTERNAL_SERVER_ERROR, util.getFolio(), util.getCodigo(),
                    stringBuilder.toString());
        }
    }
}
