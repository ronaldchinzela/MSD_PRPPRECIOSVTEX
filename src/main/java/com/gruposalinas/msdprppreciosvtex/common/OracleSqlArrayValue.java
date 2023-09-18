package com.gruposalinas.msdprppreciosvtex.common;

import oracle.jdbc.OracleConnection;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.jdbc.core.support.AbstractSqlTypeValue;

import java.sql.Connection;
import java.sql.SQLException;

public class OracleSqlArrayValue<T> extends AbstractSqlTypeValue {
  private T[] values;
  private String defaultTypeName;

  public OracleSqlArrayValue(T[] values) {
    this.values = values.clone();
  }

  public OracleSqlArrayValue(T[] values, String defaultTypeName) {
    this.values = values.clone();
    this.defaultTypeName = defaultTypeName;
  }

  @Override
  protected Object createTypeValue(Connection conn, int sqlType,
                                     String typeName) throws SQLException {

    String actualTypeName;

    if (typeName != null) {
      actualTypeName = typeName;
    }
    else {
      actualTypeName = defaultTypeName;
    }

    if (!conn.isWrapperFor(OracleConnection.class)) {
      throw new InvalidDataAccessApiUsageException(
                  "Unable to unwrap OracleConnection. Ensure you are connecting to an Oracle DB.");
      }

    return conn.unwrap(OracleConnection.class).createOracleArray(actualTypeName, values);
  }
}
