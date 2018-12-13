package com.masmovil.it.compensator.model.salesforce.composite;

import com.masmovil.it.compensator.model.salesforce.SalesForceQueryResponse;

import lombok.Data;

/**
 * BodyCompositeResponse.
 */
@Data
public class BodyCompositeResponse {

  private SalesForceQueryResponse<?> body;
  
}