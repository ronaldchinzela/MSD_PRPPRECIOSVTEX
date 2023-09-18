package com.gruposalinas.msdprppreciosvtex.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.gruposalinas.msdprppreciosvtex.dao.StatusDao;

@SpringBootTest
class StatusServicesTest {

  private StatusServices statusServices;

  @MockBean
  private StatusDao statusdao;

  @Autowired
  StatusServicesTest(StatusServices statusServices) {
    this.statusServices = statusServices;
  }

  @Test
  void testGetStatus() {
    when(statusdao.getStatus()).thenReturn("X");
    assertEquals(1, statusServices.getStatus().length(), "El tamano maximo es de 1");
  }

  @Test
  void testGet_Status() {
    when(statusdao.getStatus()).thenReturn("X");
    assertEquals("X", statusServices.getStatus(), "Debe devolver 'X'");
  }

  @Test
  void testGetStatusEmpty() {
    when(statusdao.getStatus()).thenReturn("");
    assertEquals("", statusServices.getStatus(), "Debe devolver una cadena vacía");
  }

}
