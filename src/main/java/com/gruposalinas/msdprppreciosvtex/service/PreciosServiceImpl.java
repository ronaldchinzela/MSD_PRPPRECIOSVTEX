package com.gruposalinas.msdprppreciosvtex.service;


import com.gruposalinas.msdprppreciosvtex.common.Exceptions;
import com.gruposalinas.msdprppreciosvtex.dao.PreciosDao;
import com.gruposalinas.msdprppreciosvtex.model.request.RequestLog;
import com.gruposalinas.msdprppreciosvtex.model.request.RequestPrecios;
import com.gruposalinas.msdprppreciosvtex.model.response.ResponseProductos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PreciosServiceImpl implements PreciosService {
  private final PreciosDao catalogoPedidoDao;

  @Autowired
  public PreciosServiceImpl(PreciosDao catalogoPedidoDao) {
    this.catalogoPedidoDao = catalogoPedidoDao;
  }

  @Override
  public ResponseProductos actualizaProductos(RequestPrecios request) throws Exceptions {
    return catalogoPedidoDao.actualizaProductos(request);
  }

  @Override
  public Map<String, Object> log(RequestLog request) throws Exceptions {
    return catalogoPedidoDao.log(request);
  }
}
