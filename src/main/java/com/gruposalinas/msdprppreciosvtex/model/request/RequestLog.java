package com.gruposalinas.msdprppreciosvtex.model.request;

import lombok.Data;

import java.util.List;

@Data
public class RequestLog {

  private List<Item> items;

  public RequestLog(List<Item> items) {
    this.items = items;
  }
}
