package com.gruposalinas.msdprppreciosvtex.common;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExceptionsTest {

  @Test
  void testConstructorWithMessage() {
    String message = "Test exception message";
    Exceptions exception = new Exceptions(message);

    assertEquals(message, exception.getMessage());
    assertNull(exception.getCause());
  }

  @Test
  void testConstructorWithMessageAndCause() {
    String message = "Test exception message";
    Throwable cause = new IllegalArgumentException("Test cause");
    Exceptions exception = new Exceptions(message, cause);

    assertEquals(message, exception.getMessage());
    assertEquals(cause, exception.getCause());
  }

  @Test
  void testConstructorWithCause() {
    Throwable cause = new IllegalArgumentException("Test cause");
    Exceptions exception = new Exceptions("Some message", cause);

    assertEquals("Some message", exception.getMessage()); // Verifica que el mensaje sea igual al que pasaste al constructor
    assertEquals("Test cause", exception.getCause().getMessage()); // Verifica el mensaje de la causa
  }


  @Test
  void testDefaultConstructor() {
    Exceptions exception = new Exceptions();

    assertNull(exception.getMessage());
    assertNull(exception.getCause());
  }

  @Test
  void testConstructorWithFullArguments() {
    String message = "Test exception message";
    Throwable cause = new IllegalArgumentException("Test cause");
    boolean enableSuppression = false;
    boolean writableStackTrace = true;

    Exceptions exception = new Exceptions(message, cause, enableSuppression, writableStackTrace);

    assertEquals(message, exception.getMessage());
    assertEquals(cause, exception.getCause());
  }

  @Test
  void testThrowException() {
    assertThrows(Exceptions.class, this::throwException);
  }

  @Test
  void testConstructorWithCauseOnly() {
    Throwable cause = new IllegalArgumentException("Test cause");

    Assertions.assertThrows(Exceptions.class, () -> {
      throw new Exceptions(cause);
    });
  }


  private void throwException() throws Exceptions {
    // Simulate throwing your custom exception
    throw new Exceptions("Custom exception thrown");
  }
}