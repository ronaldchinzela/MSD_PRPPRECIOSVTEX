package com.gruposalinas.msdprppreciosvtex.common;

import oracle.jdbc.OracleConnection; // Implementación concreta
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import java.sql.Array;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class OracleSqlArrayValueTest {

  @Mock
  private Connection connection;

  @Mock
  private OracleConnection oracleConnection;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testCreateTypeValue_WithTypeName() throws SQLException {
    String typeName = "TypeA";
    String defaultTypeName = "TypeB";
    String[] values = {"value1", "value2"};

    OracleSqlArrayValue<String> arrayValue = new OracleSqlArrayValue<>(values, defaultTypeName);

    Array oracleArray = mock(Array.class);

    when(connection.isWrapperFor(OracleConnection.class)).thenReturn(true);
    when(connection.unwrap(OracleConnection.class)).thenReturn(oracleConnection);
    when(oracleConnection.createOracleArray(typeName, values)).thenReturn(oracleArray);

    Object result = arrayValue.createTypeValue(connection, 0, typeName);

    assertSame(oracleArray, result);
  }

  @Test
  public void testCreateTypeValue_WithDefaultTypeName() throws SQLException {
    String defaultTypeName = "TypeB";
    String[] values = {"value1", "value2"};

    OracleSqlArrayValue<String> arrayValue = new OracleSqlArrayValue<>(values, defaultTypeName);

    Array oracleArray = mock(Array.class);

    when(connection.isWrapperFor(OracleConnection.class)).thenReturn(true);
    when(connection.unwrap(OracleConnection.class)).thenReturn(oracleConnection);
    when(oracleConnection.createOracleArray(defaultTypeName, values)).thenReturn(oracleArray);

    Object result = arrayValue.createTypeValue(connection, 0, null);

    assertSame(oracleArray, result);
  }

  @Test
  public void testCreateTypeValue_InvalidConnection() throws SQLException {
    String[] values = {"value1", "value2"};

    OracleSqlArrayValue<String> arrayValue = new OracleSqlArrayValue<>(values);

    when(connection.isWrapperFor(OracleConnection.class)).thenReturn(false);

    assertThrows(InvalidDataAccessApiUsageException.class,
            () -> arrayValue.createTypeValue(connection, 0, null));
  }
}