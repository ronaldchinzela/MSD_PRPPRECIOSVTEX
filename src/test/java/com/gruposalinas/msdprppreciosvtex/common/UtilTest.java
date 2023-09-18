package com.gruposalinas.msdprppreciosvtex.common;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilTest {

  @Test
  public void testGetFolio() {
    Util util = new Util("name", "id");
    String folio = util.getFolio();

    assertNotNull(folio); // Asegura que el folio no sea nulo
    assertFalse(folio.isEmpty()); // Asegura que el folio no esté vacío
  }

  @Test
  public void testGetCodigo() {
    Util util = new Util("TestName", "TestId");
    String codigo = util.getCodigo();

    // Verificar que el código generado sea el esperado
    assertEquals(".TestName.TestId", codigo);
  }

  @Test
  public void testVNullWithNonNullMessage() {
    Util util = new Util("TestName", "TestId");
    String mensaje = "Mensaje de prueba";
    String resultado = util.vNull(mensaje);

    // Verificar que el resultado sea igual al mensaje original
    assertEquals(mensaje, resultado);
  }

  @Test
  public void testVNullWithNullMessage() {
    Util util = new Util("TestName", "TestId");
    String mensaje = null;
    String resultado = util.vNull(mensaje);

    // Verificar que el resultado sea "Mensaje Nulo"
    assertEquals("Mensaje Nulo", resultado);
  }

}