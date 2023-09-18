package com.gruposalinas.msdprppreciosvtex.model.response;

import lombok.Data;

import java.util.Collections;
import java.util.List;

@Data
public class ResponseProductos {
  private List<Long> skus;

  public ResponseProductos(List<Long> skus) {
    this.skus = Collections.unmodifiableList(skus);
  }

}
