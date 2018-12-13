package com.masmovil.it.compensator.model.salesforce.composite;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

/**
 * QueryComposite.
 */
@Data
public class QueryComposite {
  protected String method;
  protected String url;
  protected String referenceId;
  @JsonIgnore
  protected Integer order;
}