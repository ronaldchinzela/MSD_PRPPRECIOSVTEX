package com.gruposalinas.msdprppreciosvtex.model.request;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RequestLogTest {
  @Test
  public void testEquals_SameObject_ShouldReturnTrue() {
    RequestLog log = new RequestLog(null);
    assertTrue(log.equals(log));
  }

  @Test
  public void testEquals_NullObject_ShouldReturnFalse() {
    RequestLog log = new RequestLog(null);
    assertFalse(log.equals(null));
  }

  @Test
  public void testEquals_DifferentClass_ShouldReturnFalse() {
    RequestLog log = new RequestLog(null);
    assertFalse(log.equals("SomeString"));
  }

  @Test
  public void testEquals_SameContent_ShouldReturnTrue() {
    List<Item> items1 = Arrays.asList(new Item(1L, "item1"), new Item(2L, "item2"));
    List<Item> items2 = Arrays.asList(new Item(1L, "item1"), new Item(2L, "item2"));

    RequestLog log1 = new RequestLog(items1);
    RequestLog log2 = new RequestLog(items2);
    assertTrue(log1.equals(log2));
  }

  @Test
  public void testHashCode_SameContent_ShouldBeEqual() {
    List<Item> items1 = Arrays.asList(new Item(1L, "item1"), new Item(2L, "item2"));
    List<Item> items2 = Arrays.asList(new Item(1L, "item1"), new Item(2L, "item2"));
    RequestLog log1 = new RequestLog(items1);
    RequestLog log2 = new RequestLog(items2);

    assertEquals(log1.hashCode(), log2.hashCode());
  }

  @Test
  public void testHashCode_DifferentContent_ShouldNotBeEqual() {
    List<Item> items1 = Arrays.asList(new Item(1L, "item1"), new Item(2L, "item2"));
    List<Item> items2 = Arrays.asList(new Item(1L, "item1"), new Item(3L, "item3")); // Diferente contenido
    RequestLog log1 = new RequestLog(items1);
    RequestLog log2 = new RequestLog(items2);

    assertNotEquals(log1.hashCode(), log2.hashCode());
  }

  @Test
  public void testSetItems_UpdateItems() {
    List<Item> originalItems = Arrays.asList(new Item(1L, "item1"), new Item(2L, "item2"));
    RequestLog log = new RequestLog(originalItems);

    List<Item> newItems = Arrays.asList(new Item(3L, "item3"), new Item(4L, "item4"));
    log.setItems(newItems);
    assertEquals(newItems, log.getItems());
  }

  @Test
  public void testToString() {
    Item item1 = new Item(1L, "item1");
    Item item2 = new Item(2L, "item2");

    RequestLog log = new RequestLog(Arrays.asList(item1, item2));

    String toString = log.toString();

    assertTrue(toString.contains("RequestLog"));
    assertTrue(toString.contains("Item(sku=1, mensaje=item1)"));
    assertTrue(toString.contains("Item(sku=2, mensaje=item2)"));
  }

  @Test
  public void testEqualsSameObject_ShouldReturnTrue() {
    List<Item> items = new ArrayList<>();
    RequestLog log1 = new RequestLog(items);

    assertTrue(log1.equals(log1));
  }

  @Test
  public void testEquals_EqualObjects_ShouldReturnTrue() {
    // Arrange
    List<Item> items1 = new ArrayList<>();
    List<Item> items2 = new ArrayList<>();
    RequestLog log1 = new RequestLog(items1);
    RequestLog log2 = new RequestLog(items2);

    // Act & Assert
    assertTrue(log1.equals(log2));
  }

  @Test
  public void testEquals_DifferentObjects_ShouldReturnFalse() {
    // Arrange
    List<Item> items1 = new ArrayList<>();
    List<Item> items2 = new ArrayList<>();
    RequestLog log1 = new RequestLog(items1);
    RequestLog log2 = new RequestLog(items2);
    items2.add(new Item(2L, "mensaje=item2"));

    assertFalse(log1.equals(log2));
  }

  @Test
  public void testEquals_NullObjectShouldReturnFalse() {
    // Arrange
    List<Item> items = new ArrayList<>();
    RequestLog log = new RequestLog(items);

    // Act & Assert
    assertFalse(log.equals(null));
  }

  @Test
  public void testHashCode_SameContentShouldBeEqual() {
    // Arrange
    List<Item> items1 = Arrays.asList(new Item(1L, "item1"), new Item(2L, "item2"));
    List<Item> items2 = Arrays.asList(new Item(1L, "item1"), new Item(2L, "item2"));
    RequestLog log1 = new RequestLog(items1);
    RequestLog log2 = new RequestLog(items2);

    // Act & Assert
    assertEquals(log1.hashCode(), log2.hashCode());
  }

  @Test
  public void testHashCodeDifferentContent_ShouldNotBeEqual() {
    // Arrange
    List<Item> items1 = Arrays.asList(new Item(1L, "item1"), new Item(2L, "item2"));
    List<Item> items2 = Arrays.asList(new Item(1L, "item1"), new Item(3L, "item3")); // Diferente contenido
    RequestLog log1 = new RequestLog(items1);
    RequestLog log2 = new RequestLog(items2);

    // Act & Assert
    assertNotEquals(log1.hashCode(), log2.hashCode());
  }

  @Test
  public void testHashCode_EmptyList_ShouldBeEqual() {
    // Arrange
    RequestLog log1 = new RequestLog(new ArrayList<>());
    RequestLog log2 = new RequestLog(new ArrayList<>());

    // Act & Assert
    assertEquals(log1.hashCode(), log2.hashCode());
  }

  @Test
  public void testHashCode_NullList_ShouldBeEqual() {
    // Arrange
    RequestLog log1 = new RequestLog(null);
    RequestLog log2 = new RequestLog(null);

    // Act & Assert
    assertEquals(log1.hashCode(), log2.hashCode());
  }

  @Test
  public void testEquals_NullItems_ShouldReturnTrue() {
    RequestLog log1 = new RequestLog(null);
    RequestLog log2 = new RequestLog(null);

    assertTrue(log1.equals(log2));
  }

  @Test
  public void testEquals_OneNullItems_ShouldReturnFalse() {
    RequestLog log1 = new RequestLog(null);
    List<Item> items = Arrays.asList(new Item(1L, "item1"), new Item(2L, "item2"));
    RequestLog log2 = new RequestLog(items);

    assertFalse(log1.equals(log2));
  }

  @Test
  public void testEquals_DifferentItemsSize_ShouldReturnFalse() {
    List<Item> items1 = Arrays.asList(new Item(1L, "item1"), new Item(2L, "item2"));
    List<Item> items2 = Arrays.asList(new Item(1L, "item1"));
    RequestLog log1 = new RequestLog(items1);
    RequestLog log2 = new RequestLog(items2);

    assertFalse(log1.equals(log2));
  }

  @Test
  public void testEquals_SameItemsDifferentOrder_ShouldReturnTrue() {
    List<Item> items1 = Arrays.asList(new Item(1L, "item1"), new Item(2L, "item2"));
    List<Item> items2 = Arrays.asList(new Item(2L, "item2"), new Item(1L, "item1"));
    RequestLog log1 = new RequestLog(items1);
    RequestLog log2 = new RequestLog(items2);

    assertFalse(log1.equals(log2));
  }

}