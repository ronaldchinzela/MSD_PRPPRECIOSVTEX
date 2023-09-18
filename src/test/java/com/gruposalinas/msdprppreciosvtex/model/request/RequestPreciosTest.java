package com.gruposalinas.msdprppreciosvtex.model.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RequestPreciosTest {
  @Test
  public void testSetFechaHoraEjecucion() {

    RequestPrecios requestPrecios = new RequestPrecios("2023-09-06T12:00:00");

    requestPrecios.setFechaHoraEjecucion("2023-09-07T13:30:00");

    assertEquals("2023-09-07T13:30:00", requestPrecios.getFechaHoraEjecucion());
  }

  @Test
  public void testGetFechaHoraEjecucion() {

    RequestPrecios requestPrecios = new RequestPrecios("2023-09-06T12:00:00");
    assertEquals("2023-09-06T12:00:00", requestPrecios.getFechaHoraEjecucion());
  }

}