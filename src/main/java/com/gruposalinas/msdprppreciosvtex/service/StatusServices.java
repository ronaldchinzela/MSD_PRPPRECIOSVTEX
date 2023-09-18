package com.gruposalinas.msdprppreciosvtex.service;

import com.gruposalinas.msdprppreciosvtex.dao.StatusDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatusServices {
  private final StatusDao statusDao;

  @Autowired
  public StatusServices(StatusDao statusDao) {
    this.statusDao = statusDao;
  }

  public String getStatus() {
    return statusDao.getStatus();
  }
}
