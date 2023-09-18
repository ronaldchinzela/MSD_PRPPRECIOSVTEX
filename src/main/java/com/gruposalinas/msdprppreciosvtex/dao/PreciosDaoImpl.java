package com.gruposalinas.msdprppreciosvtex.dao;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.gruposalinas.log.common.Estatus;
import com.gruposalinas.log.common.Proceso;
import com.gruposalinas.log.dto.InfoProceso;
import com.gruposalinas.log.service.Logger;
import com.gruposalinas.msdprppreciosvtex.common.Constantes;
import com.gruposalinas.msdprppreciosvtex.common.Exceptions;
import com.gruposalinas.msdprppreciosvtex.common.ProcesosCommon;
import com.gruposalinas.msdprppreciosvtex.model.request.RequestLog;
import com.gruposalinas.msdprppreciosvtex.model.request.RequestPrecios;
import com.gruposalinas.msdprppreciosvtex.model.response.ResponseProductos;
import oracle.jdbc.OracleTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.List;
import java.util.Map;

@Repository
public class PreciosDaoImpl implements PreciosDao {
  private final JdbcTemplate jdbcTemplate;
  private final Logger logger;

  @Autowired
  public PreciosDaoImpl(JdbcTemplate jdbcTemplate, Logger logger) {
    this.jdbcTemplate = jdbcTemplate;
    this.logger = logger;
  }

  public ResponseProductos actualizaProductos(RequestPrecios request) throws Exceptions {
    InfoProceso infoProceso = new InfoProceso();

    try {
      infoProceso.setProceso("PreciosDaoImpl.actualizaProductos");
      infoProceso.setRequest(request);
      infoProceso.setEnable(Proceso.INICIO);

      logger.debug(infoProceso);

      SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
        .withSchemaName(Constantes.SCHEMA)
        .withCatalogName(ProcesosCommon.MODIFICA_PRECIOS.getCatalogo())
        .withProcedureName(ProcesosCommon.MODIFICA_PRECIOS.getFn())
        .declareParameters(
          new SqlOutParameter(Constantes.PA_FECHAS, OracleTypes.NVARCHAR),
          new SqlOutParameter(Constantes.PA_COERRMSG, OracleTypes.NVARCHAR),
          new SqlOutParameter(Constantes.PA_IOCERR, OracleTypes.NUMBER),
          new SqlOutParameter(Constantes.PA_TAB_DATA, OracleTypes.REF_CURSOR));
      MapSqlParameterSource params = new MapSqlParameterSource();
      params.addValue(Constantes.PA_FECHAS, request.getFechaHoraEjecucion());
      Map<String, Object> responseDB = simpleJdbcCall.execute(params);

      infoProceso.setEnable(Proceso.FIN);
      infoProceso.setStatus(Estatus.SUCCESS);
      infoProceso.setResponse(responseDB);

      List<?> itemsList = (List<?>) responseDB.get(Constantes.PA_TAB_DATA);

      List<Long> items = itemsList.stream().map((Object i) -> {
        String jsonItem = new Gson().toJson(i);
        JsonObject jsonObject = new Gson().fromJson(jsonItem, JsonObject.class);

        return jsonObject.get(Constantes.SKU_ELEMENT_SP).getAsLong();
      }).toList();

      ResponseProductos response = new ResponseProductos(items);
      response.setSkus(items);

      logger.debug(infoProceso);

      return response;
    }
    catch (Exception e) {
      infoProceso.setStatus(Estatus.ERROR);
      infoProceso.setResponse(e);

      logger.debug(infoProceso);

      throw new Exceptions(e);

    }
  }

  public Map<String, Object> log(RequestLog request) throws Exceptions {
    InfoProceso infoProceso = new InfoProceso();

    try {

      infoProceso.setProceso("PreciosDaoImpl.log");
      infoProceso.setRequest(request);
      infoProceso.setEnable(Proceso.INICIO);

      logger.debug(infoProceso);

      SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
        .withSchemaName(Constantes.SCHEMA)
        .withCatalogName(ProcesosCommon.LOG.getCatalogo())
        .withProcedureName(ProcesosCommon.LOG.getFn())
        .declareParameters(
          new SqlOutParameter(Constantes.PA_IOCERR, OracleTypes.NUMBER),
          new SqlOutParameter(Constantes.PA_COERRMSG, OracleTypes.VARCHAR));

      String jsonItems = new Gson().toJson(request);

      SqlParameterSource values = new MapSqlParameterSource()
        .addValue(Constantes.PA_JSONLOGVTEX, jsonItems, Types.CLOB)
        .addValue(Constantes.PA_USUARIOMODIF, Constantes.SCSELLCEN, Types.VARCHAR);

      Map<String, Object> responseDB = simpleJdbcCall.execute(values);

      infoProceso.setResponse(responseDB);
      infoProceso.setEnable(Proceso.FIN);
      infoProceso.setStatus(Estatus.SUCCESS);

      logger.debug(infoProceso);

      return null;
    }
    catch (Exception e) {
      infoProceso.setStatus(Estatus.ERROR);
      infoProceso.setResponse(e);

      logger.debug(infoProceso);
      throw new Exceptions(e);
    }
  }
}
