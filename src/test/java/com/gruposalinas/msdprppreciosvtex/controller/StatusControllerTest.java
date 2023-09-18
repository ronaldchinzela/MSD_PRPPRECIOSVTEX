package com.gruposalinas.msdprppreciosvtex.controller;

import com.gruposalinas.msdprppreciosvtex.common.Constantes;
import com.gruposalinas.msdprppreciosvtex.common.Util;
import com.gruposalinas.msdprppreciosvtex.service.StatusServices;
import com.gruposalinas.response.model.ResponseService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class StatusControllerTest {

  @MockBean
  private StatusServices statusServices;

  @MockBean
  private Util util;

  @Autowired
  private StatusController statusController;

  @Test
  void testStatus() {
    when(util.getCodigo()).thenReturn("100");
    when(util.getFolio()).thenReturn("100");
    when(statusServices.getStatus()).thenReturn("X");

    ResponseService esperado = new ResponseService(HttpStatus.OK, util.getCodigo(), Constantes.SUCCESS_OPERATION,
            util.getFolio(), "X");
    ResponseService response = statusController.getStatus();

    assertEquals(esperado.getCodigo(), response.getCodigo());
  }
}