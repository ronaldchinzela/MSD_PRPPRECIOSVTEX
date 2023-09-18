package com.gruposalinas.msdprppreciosvtex.model.response;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ResponseProductosTest {
  @Test
  void testEquals_SameContent_ShouldReturnTrue() {
    List<Long> skus1 = Arrays.asList(1L, 2L, 3L);
    List<Long> skus2 = Arrays.asList(1L, 2L, 3L);

    ResponseProductos response1 = new ResponseProductos(skus1);
    ResponseProductos response2 = new ResponseProductos(skus2);

    assertTrue(response1.equals(response2));
    assertEquals(response1.hashCode(), response2.hashCode());
  }

  @Test
  void testEquals_WithItself_ShouldReturnTrue() {
    List<Long> skus = Arrays.asList(1L, 2L, 3L);
    ResponseProductos response = new ResponseProductos(skus);

    assertTrue(response.equals(response));
    assertEquals(response.hashCode(), response.hashCode());
  }

  @Test
  void testEquals_WithNullObject_ShouldReturnFalse() {
    List<Long> skus = Arrays.asList(1L, 2L, 3L);
    ResponseProductos response = new ResponseProductos(skus);

    assertFalse(response.equals(null));
  }

  @Test
  void testEquals_DifferentClass_ShouldReturnFalse() {
    List<Long> skus = Arrays.asList(1L, 2L, 3L);
    ResponseProductos response = new ResponseProductos(skus);

    assertFalse(response.equals("SomeString"));
  }

  @Test
  void testEquals_WithDifferentSkus_ShouldReturnFalse() {
    List<Long> skus1 = Arrays.asList(1L, 2L, 3L);
    List<Long> skus2 = Arrays.asList(4L, 5L, 6L);

    ResponseProductos response1 = new ResponseProductos(skus1);
    ResponseProductos response2 = new ResponseProductos(skus2);

    assertFalse(response1.equals(response2));
  }

  @Test
  void testEquals_WithSameSkusDifferentOrder_ShouldReturnFalse() {
    List<Long> skusOriginales = Arrays.asList(1L, 2L, 3L);
    List<Long> skusEsperados = Arrays.asList(3L, 2L, 1L);

    ResponseProductos responseOriginal = new ResponseProductos(skusOriginales);
    ResponseProductos responseEsperado = new ResponseProductos(skusEsperados);

    assertFalse(responseOriginal.equals(responseEsperado));
  }


  @Test
  void testHashCodeConsistency() {
    Set<Integer> hashCodes = new HashSet<>();
    List<Long> skus = Arrays.asList(1L, 2L, 3L);

    for (int i = 0; i < 1000; i++) {
      ResponseProductos response = new ResponseProductos(skus);
      hashCodes.add(response.hashCode());
    }
    assertEquals(1, hashCodes.size());
  }

  @Test
  void testSetSkus() {
    List<Long> initialSkus = Arrays.asList(1L, 2L, 3L);
    ResponseProductos response = new ResponseProductos(initialSkus);

    List<Long> newSkus = Arrays.asList(4L, 5L, 6L);

    response.setSkus(newSkus);
    assertEquals(newSkus, response.getSkus());
  }

  @Test
  void testToString() {
    ResponseProductos response = new ResponseProductos(List.of(1L, 2L, 3L));

    String expectedString = "ResponseProductos(skus=[1, 2, 3])";

    assertEquals(expectedString, response.toString());
  }

  @Test
  void testEquals_Same_Content_ShouldReturnTrue() {
    List<Long> skus1 = Arrays.asList(1L, 2L, 3L);
    List<Long> skus2 = Arrays.asList(1L, 2L, 3L);

    ResponseProductos response1 = new ResponseProductos(skus1);
    ResponseProductos response2 = new ResponseProductos(skus2);

    assertTrue(response1.equals(response2));
    assertEquals(response1.hashCode(), response2.hashCode());
  }

  @Test
  void testEquals_With_Itself_ShouldReturnTrue() {
    List<Long> skus = Arrays.asList(1L, 2L, 3L);
    ResponseProductos response = new ResponseProductos(skus);

    assertTrue(response.equals(response));
    assertEquals(response.hashCode(), response.hashCode());
  }

  @Test
  void testEquals_WithNull_Object_ShouldReturnFalse() {
    List<Long> skus = Arrays.asList(1L, 2L, 3L);
    ResponseProductos response = new ResponseProductos(skus);

    assertFalse(response.equals(null));
  }

  @Test
  void testEquals_Different_Class_ShouldReturnFalse() {
    List<Long> skus = Arrays.asList(1L, 2L, 3L);
    ResponseProductos response = new ResponseProductos(skus);

    assertFalse(response.equals("SomeString"));
  }

  @Test
  void testEquals_With_DifferentSkus_ShouldReturnFalse() {
    List<Long> skus1 = Arrays.asList(1L, 2L, 3L);
    List<Long> skus2 = Arrays.asList(4L, 5L, 6L);

    ResponseProductos response1 = new ResponseProductos(skus1);
    ResponseProductos response2 = new ResponseProductos(skus2);

    assertFalse(response1.equals(response2));
  }

  @Test
  void testEquals_WithSameSkus_DifferentOrder_ShouldReturnFalse() {
    List<Long> skusOriginales = Arrays.asList(1L, 2L, 3L);
    List<Long> skusEsperados = Arrays.asList(3L, 2L, 1L);

    ResponseProductos responseOriginal = new ResponseProductos(skusOriginales);
    ResponseProductos responseEsperado = new ResponseProductos(skusEsperados);

    assertFalse(responseOriginal.equals(responseEsperado));
  }

  @Test
  void testHash_CodeConsistency() {
    List<Long> skus = Arrays.asList(1L, 2L, 3L);

    for (int i = 0; i < 1000; i++) {
      ResponseProductos response = new ResponseProductos(skus);
      assertEquals(response.hashCode(), response.hashCode(), "HashCode should be consistent");
    }
  }

  @Test
  void testHashCodeWith_DifferentObjects_ShouldReturnFalse() {
    List<Long> skus1 = Arrays.asList(1L, 2L, 3L);
    List<Long> skus2 = Arrays.asList(3L, 2L, 1L);

    ResponseProductos response1 = new ResponseProductos(skus1);
    ResponseProductos response2 = new ResponseProductos(skus2);

    assertNotEquals(response1.hashCode(), response2.hashCode());
  }

  @Test
  void testHashCodeWith_EqualContent_ShouldBeEqual() {
    List<Long> skus1 = Arrays.asList(1L, 2L, 3L);
    List<Long> skus2 = Arrays.asList(1L, 2L, 3L);

    ResponseProductos response1 = new ResponseProductos(skus1);
    ResponseProductos response2 = new ResponseProductos(skus2);

    assertEquals(response1.hashCode(), response2.hashCode());
  }

  @Test
  void testEquals_EmptyLists_ShouldReturnTrue() {
    List<Long> skus1 = Collections.emptyList();
    List<Long> skus2 = Collections.emptyList();

    ResponseProductos response1 = new ResponseProductos(skus1);
    ResponseProductos response2 = new ResponseProductos(skus2);

    assertTrue(response1.equals(response2));
    assertEquals(response1.hashCode(), response2.hashCode());
  }

  @Test
  void testEquals_DifferentSizeLists_ShouldReturnFalse() {
    List<Long> skus1 = Arrays.asList(1L, 2L, 3L);
    List<Long> skus2 = Arrays.asList(1L, 2L);

    ResponseProductos response1 = new ResponseProductos(skus1);
    ResponseProductos response2 = new ResponseProductos(skus2);

    assertFalse(response1.equals(response2));
  }

  @Test
  void testEquals_OneEmptyListOneNonEmptyList_ShouldReturnFalse() {
    List<Long> skus1 = Arrays.asList(1L, 2L, 3L);
    List<Long> skus2 = Collections.emptyList();

    ResponseProductos response1 = new ResponseProductos(skus1);
    ResponseProductos response2 = new ResponseProductos(skus2);

    assertFalse(response1.equals(response2));
  }

  @Test
  void testEquals_Empty_Lists_ShouldReturnTrue() {
    List<Long> skus1 = Collections.emptyList();
    List<Long> skus2 = Collections.emptyList();

    ResponseProductos response1 = new ResponseProductos(skus1);
    ResponseProductos response2 = new ResponseProductos(skus2);

    assertTrue(response1.equals(response2));
    assertEquals(response1.hashCode(), response2.hashCode());
  }

  @Test
  void testEquals_DifferentSize_Lists_ShouldReturnFalse() {
    List<Long> skus1 = Arrays.asList(1L, 2L, 3L);
    List<Long> skus2 = Arrays.asList(1L, 2L);

    ResponseProductos response1 = new ResponseProductos(skus1);
    ResponseProductos response2 = new ResponseProductos(skus2);

    assertFalse(response1.equals(response2));
  }

  @Test
  void testEquals_OneEmptyList_OneNonEmptyList_ShouldReturnFalse() {
    List<Long> skus1 = Arrays.asList(1L, 2L, 3L);
    List<Long> skus2 = Collections.emptyList();

    ResponseProductos response1 = new ResponseProductos(skus1);
    ResponseProductos response2 = new ResponseProductos(skus2);

    assertFalse(response1.equals(response2));
  }
}