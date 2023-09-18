package com.gruposalinas.msdprppreciosvtex.dao;


import com.gruposalinas.msdprppreciosvtex.common.Exceptions;
import com.gruposalinas.msdprppreciosvtex.model.request.RequestLog;
import com.gruposalinas.msdprppreciosvtex.model.request.RequestPrecios;
import com.gruposalinas.msdprppreciosvtex.model.response.ResponseProductos;

import java.util.Map;


public interface PreciosDao {


  ResponseProductos actualizaProductos(RequestPrecios request)
            throws Exceptions;

  Map<String, Object> log(RequestLog request)
            throws Exceptions;
}
