package com.gruposalinas.msdprppreciosvtex.service;



import com.gruposalinas.msdprppreciosvtex.common.Exceptions;
import com.gruposalinas.msdprppreciosvtex.model.request.RequestLog;
import com.gruposalinas.msdprppreciosvtex.model.request.RequestPrecios;
import com.gruposalinas.msdprppreciosvtex.model.response.ResponseProductos;

import java.util.HashMap;


public interface PreciosService {


  ResponseProductos actualizaProductos(RequestPrecios request)
    throws Exceptions, Exceptions;
  HashMap<String, Object> log(RequestLog request)
    throws Exceptions, Exceptions;


}
