package com.gruposalinas.msdprppreciosvtex.model.request;

import lombok.Data;

@Data
public class Item {
  private long sku;
  private String mensaje;

  public Item(long sku, String mensaje) {
    this.sku = sku;
    this.mensaje = mensaje;
  }
}
