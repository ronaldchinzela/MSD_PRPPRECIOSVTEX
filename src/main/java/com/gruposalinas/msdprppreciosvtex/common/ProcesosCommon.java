package com.gruposalinas.msdprppreciosvtex.common;


import lombok.Getter;

@Getter
public enum ProcesosCommon {


  MODIFICA_PRECIOS("CONSULTA PRECIOS", Constantes.PKG_CRUDCPRECIOS, "SPINFOPROMO", ""),
  LOG("CONSULTA PRECIOS", Constantes.PKG_CRUDCPRECIOS, "SPCREATELOGVTEX", "");

  private String nombre;
  private String catalogo;
  private String fn;
  private String identificador;

  ProcesosCommon(String nombre, String catalogo, String fn, String identificador)
  {
    this.nombre = nombre;
    this.catalogo = catalogo;
    this.fn = fn;
    this.identificador = identificador;
  }


}