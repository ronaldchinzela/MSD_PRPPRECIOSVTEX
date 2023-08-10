package com.gruposalinas.msdprppreciosvtex.common;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Util {

    private static final Pattern PATTERN = Pattern.compile("[-:T.]");
    private static final int MAXSIZE = 16;


    @Value("${info.api.name}")
    private String name;

    @Value("${info.api.id}")
    private String id;

    public String getFolio() {
        LocalDateTime localDateTime = LocalDateTime.now();
        Matcher m = PATTERN.matcher(localDateTime.toString());
        return id + "-" + m.replaceAll("").substring(0, MAXSIZE) + "00";
    }

    public String getCodigo() {
        return ".".concat(name).concat(".").concat(id);
    }

    public String vNull(String mensaje) {
        if (mensaje == null) {
            mensaje = "Mensaje Nulo";
        }
        return mensaje;
    }
}
