package com.gruposalinas.msdprppreciosvtex.common;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ProcesosCommonTest {

  @Test
  void testGetNombre() {
    assertEquals("CONSULTA PRECIOS", ProcesosCommon.MODIFICA_PRECIOS.getNombre());
    assertEquals("CONSULTA PRECIOS", ProcesosCommon.LOG.getNombre());
  }

  @Test
  void testGetCatalogo() {
    assertEquals(Constantes.PKG_CRUDCPRECIOS, ProcesosCommon.MODIFICA_PRECIOS.getCatalogo());
    assertEquals(Constantes.PKG_CRUDCPRECIOS, ProcesosCommon.LOG.getCatalogo());
  }

  @Test
  void testGetFn() {
    assertEquals("SPINFOPROMO", ProcesosCommon.MODIFICA_PRECIOS.getFn());
    assertEquals("SPCREATELOGVTEX", ProcesosCommon.LOG.getFn());
  }

  @Test
  void testGetIdentificador() {
    assertEquals("", ProcesosCommon.MODIFICA_PRECIOS.getIdentificador());
    assertEquals("", ProcesosCommon.LOG.getIdentificador());
  }
}
