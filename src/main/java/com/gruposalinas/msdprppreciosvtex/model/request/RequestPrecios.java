package com.gruposalinas.msdprppreciosvtex.model.request;


public class RequestPrecios {
  private String fechaHoraEjecucion;

  public RequestPrecios(String fechaHoraEjecucion) {
    this.fechaHoraEjecucion = fechaHoraEjecucion;
  }

  public String getFechaHoraEjecucion() {
    return fechaHoraEjecucion;
  }

  public void setFechaHoraEjecucion(String fechaHoraEjecucion) {
    this.fechaHoraEjecucion = fechaHoraEjecucion;
  }
}
