package com.gruposalinas.msdprppreciosvtex.model.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {
  @Test
  public void testEqualsWithEqualObjects() {
    Item item1 = new Item(1, "Mensaje1");
    Item item2 = new Item(1, "Mensaje1");

    assertTrue(item1.equals(item2));
    assertTrue(item2.equals(item1));
  }

  @Test
  public void testEqualsWithDifferentSku() {
    Item item1 = new Item(1, "Mensaje1");
    Item item2 = new Item(2, "Mensaje1");

    assertFalse(item1.equals(item2));
    assertFalse(item2.equals(item1));
  }

  @Test
  public void testEqualsWithDifferentMensaje() {
    Item item1 = new Item(1, "Mensaje1");
    Item item2 = new Item(1, "Mensaje2");

    assertFalse(item1.equals(item2));
    assertFalse(item2.equals(item1));
  }

  @Test
  public void testEqualsWithNullObject() {
    Item item1 = new Item(1, "Mensaje1");

    assertFalse(item1.equals(null));
  }

  @Test
  public void testEqualsWithDifferentClass() {
    Item item1 = new Item(1, "Mensaje1");
    Object obj = new Object();

    assertFalse(item1.equals(obj));
  }

  @Test
  public void testHashCodeWithEqualObjects() {
    Item item1 = new Item(1, "Mensaje1");
    Item item2 = new Item(1, "Mensaje1");

    assertEquals(item1.hashCode(), item2.hashCode());
  }

  @Test
  public void testHashCodeWithDifferentSku() {
    Item item1 = new Item(1, "Mensaje1");
    Item item2 = new Item(2, "Mensaje1");

    assertNotEquals(item1.hashCode(), item2.hashCode());
  }

  @Test
  public void testHashCodeWithDifferentMensaje() {
    Item item1 = new Item(1, "Mensaje1");
    Item item2 = new Item(1, "Mensaje2");

    assertNotEquals(item1.hashCode(), item2.hashCode());
  }

  @Test
  public void testToString() {
    Item item = new Item(1, "Mensaje1");
    String expectedToString = "Item(sku=1, mensaje=Mensaje1)";

    assertEquals(expectedToString, item.toString());
  }

  @Test
  public void testSetSku() {
    Item item = new Item(1, "Mensaje1");
    long newSku = 2;

    item.setSku(newSku);

    assertEquals(newSku, item.getSku());
  }

  @Test
  public void testSetMensaje() {
    Item item = new Item(1, "Mensaje1");
    String newMensaje = "Mensaje2";

    item.setMensaje(newMensaje);

    assertEquals(newMensaje, item.getMensaje());
  }

  @Test
  public void testConstructorWithValidData() {
    long sku = 1;
    String mensaje = "Mensaje1";

    Item item = new Item(sku, mensaje);

    assertEquals(sku, item.getSku());
    assertEquals(mensaje, item.getMensaje());
  }

  @Test
  public void testEqualsWithNullField() {
    Item item1 = new Item(1, "Mensaje1");
    Item item2 = new Item(1, null);

    assertFalse(item1.equals(item2));
    assertFalse(item2.equals(item1));
  }

  @Test
  public void testEqualsWithNullFieldOnDifferentItems() {
    Item item1 = new Item(1, null);
    Item item2 = new Item(2, null);

    assertFalse(item1.equals(item2));
    assertFalse(item2.equals(item1));
  }

  @Test
  public void testEqualsWithDifferentFieldNull() {
    Item item1 = new Item(1, "Mensaje1");
    Item item2 = new Item(1, null);

    assertFalse(item1.equals(item2));
    assertFalse(item2.equals(item1));
  }

  @Test
  public void testEqualsWithDifferentFieldNullOnDifferentItems() {
    Item item1 = new Item(1, "Mensaje1");
    Item item2 = new Item(2, null);

    assertFalse(item1.equals(item2));
    assertFalse(item2.equals(item1));
  }

  @Test
  public void testEqualsWithDifferentType() {
    Item item1 = new Item(1, "Mensaje1");
    String str = "No es un Item";

    assertFalse(item1.equals(str));
  }

  @Test
  public void testEqualsWithNull() {
    Item item1 = new Item(1, "Mensaje1");

    assertFalse(item1.equals(null));
  }

  @Test
  public void testEqualsReflexive() {
    Item item1 = new Item(1, "Mensaje1");

    assertTrue(item1.equals(item1));
  }

  @Test
  public void testEqualsSymmetric() {
    Item item1 = new Item(1, "Mensaje1");
    Item item2 = new Item(1, "Mensaje1");

    assertTrue(item1.equals(item2));
    assertTrue(item2.equals(item1));
  }

  @Test
  public void testEqualsTransitive() {
    Item item1 = new Item(1, "Mensaje1");
    Item item2 = new Item(1, "Mensaje1");
    Item item3 = new Item(1, "Mensaje1");

    assertTrue(item1.equals(item2));
    assertTrue(item2.equals(item3));
    assertTrue(item1.equals(item3));
  }

  @Test
  public void testEqualsWithEqualItemsAndNullFields() {
    Item item1 = new Item(1, null);
    Item item2 = new Item(1, null);

    assertTrue(item1.equals(item2));
    assertTrue(item2.equals(item1));
  }

  @Test
  public void testEqualsWithDifferentSkuAndNullMensaje() {
    Item item1 = new Item(1, null);
    Item item2 = new Item(2, null);

    assertFalse(item1.equals(item2));
    assertFalse(item2.equals(item1));
  }

  @Test
  public void testEqualsWithNullSkuAndEqualMensajes() {
    Item item1 = new Item(10, "Mensaje1");
    Item item2 = new Item(10, "Mensaje1");

    assertTrue(item1.equals(item2));
    assertTrue(item2.equals(item1));
  }

  @Test
  public void testEqualsWithNullSkuAndDifferentMensajes() {
    Item item1 = new Item(20, "Mensaje1");
    Item item2 = new Item(20, "Mensaje2");

    assertFalse(item1.equals(item2));
    assertFalse(item2.equals(item1));
  }

  @Test
  public void testEqualsWithEqualItemsAndEmptyMensaje() {
    Item item1 = new Item(1, "");
    Item item2 = new Item(1, "");

    assertTrue(item1.equals(item2));
    assertTrue(item2.equals(item1));
  }

  @Test
  public void testEqualsWithDifferentSkuAndEmptyMensaje() {
    Item item1 = new Item(1, "");
    Item item2 = new Item(2, "");

    assertFalse(item1.equals(item2));
    assertFalse(item2.equals(item1));
  }

  @Test
  public void testEqualsWithEmptySkuAndEqualMensajes() {
    Item item1 = new Item(40, "Mensaje1");
    Item item2 = new Item(40, "Mensaje1");

    assertTrue(item1.equals(item2));
    assertTrue(item2.equals(item1));
  }

  @Test
  public void testEqualsWithEmptySkuAndDifferentMensajes() {
    Item item1 = new Item(50, "Mensaje1");
    Item item2 = new Item(80, "Mensaje2");

    assertFalse(item1.equals(item2));
    assertFalse(item2.equals(item1));
  }

  @Test
  public void testEqualsWithDifferentSkuAndMensaje() {
    Item item1 = new Item(1, "Mensaje1");
    Item item2 = new Item(2, "Mensaje2");

    assertFalse(item1.equals(item2));
    assertFalse(item2.equals(item1));
  }

  @Test
  public void testEqualsWithEqualSkuAndNullMensaje() {
    Item item1 = new Item(1, null);
    Item item2 = new Item(1, null);

    assertTrue(item1.equals(item2));
    assertTrue(item2.equals(item1));
  }

  @Test
  public void testEqualsWithDifferentSkuAndEqualMensaje() {
    Item item1 = new Item(1, "Mensaje1");
    Item item2 = new Item(2, "Mensaje1");

    assertFalse(item1.equals(item2));
    assertFalse(item2.equals(item1));
  }

  @Test
  public void testEqualsWithEqualSkuAndEmptyMensaje() {
    Item item1 = new Item(1, "");
    Item item2 = new Item(1, "");

    assertTrue(item1.equals(item2));
    assertTrue(item2.equals(item1));
  }

  @Test
  public void testEqualsWithDifferentSkuAndEmptyMensajes() {
    Item item1 = new Item(1, "");
    Item item2 = new Item(2, "");

    assertFalse(item1.equals(item2));
    assertFalse(item2.equals(item1));
  }

  @Test
  public void testEqualsWithEqualSkuAndDifferentCaseMensaje() {
    Item item1 = new Item(1, "Mensaje1");
    Item item2 = new Item(1, "mensaje1");

    assertFalse(item1.equals(item2));
    assertFalse(item2.equals(item1));
  }

  @Test
  public void testEqualsWithEqualSkuAndDifferentCaseMensajes() {
    Item item1 = new Item(1, "Mensaje1");
    Item item2 = new Item(1, "mensaje1");

    assertFalse(item1.equals(item2));
    assertFalse(item2.equals(item1));
  }

  @Test
  public void testEqualsWithNullSkuAndEmptyMensaje() {
    Item item1 = new Item(1l, "");
    Item item2 = new Item(1l, "");

    assertTrue(item1.equals(item2));
    assertTrue(item2.equals(item1));
  }

  @Test
  public void testEqualsWithEmptySkuAndNullMensaje() {
    Item item1 = new Item(7, null);
    Item item2 = new Item(7, null);

    assertTrue(item1.equals(item2));
    assertTrue(item2.equals(item1));
  }

  @Test
  public void testEqualsWithSkuOfDifferentType() {
    Item item1 = new Item(1, "Mensaje1");
    Item item2 = new Item(Integer.valueOf(1), "Mensaje1");

    assertTrue(item1.equals(item2));
    assertTrue(item2.equals(item1));
  }

  @Test
  public void testHashCodeWithDifferentTypeSkuAndDifferentMessage() {
    Item item1 = new Item(1, "Mensaje1");
    Item item2 = new Item(Integer.valueOf(1), "Mensaje2");

    assertNotEquals(item1.hashCode(), item2.hashCode());
  }

  @Test
  public void testHashCodeWithDifferentTypeButSameValueSku() {
    Item item1 = new Item(1, "Mensaje1");
    Item item2 = new Item(Integer.valueOf(1), "Mensaje1");

    assertEquals(item1.hashCode(), item2.hashCode());
  }

  @Test
  public void testHashCodeWithDifferentMessage() {
    Item item1 = new Item(1, "Mensaje1");
    Item item2 = new Item(1, "Mensaje2");

    assertNotEquals(item1.hashCode(), item2.hashCode());
  }

  @Test
  public void testHashCodeWithDifferentSkus() {
    Item item1 = new Item(1, "Mensaje1");
    Item item2 = new Item(2, "Mensaje1");

    assertNotEquals(item1.hashCode(), item2.hashCode());
  }

  @Test
  public void testHashCodeWithDifferentSkuAndMessage() {
    Item item1 = new Item(1, "Mensaje1");
    Item item2 = new Item(2, "Mensaje2");

    assertNotEquals(item1.hashCode(), item2.hashCode());
  }

  @Test
  public void testHashCodeWithEqualMessage() {
    Item item1 = new Item(1, "Mensaje1");
    Item item2 = new Item(2, "Mensaje1");

    assertNotEquals(item1.hashCode(), item2.hashCode());
  }

  @Test
  public void testHashCodeWithEqualSkuButDifferentTypes() {
    Item item1 = new Item(1, "Mensaje1");
    Item item2 = new Item((long) 1, "Mensaje1");

    assertEquals(item1.hashCode(), item2.hashCode());
  }

}