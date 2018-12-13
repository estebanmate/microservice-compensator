package com.masmovil.it.compensator.model.salesforce.composite;

import java.util.List;
import lombok.Data;

/**
 * CompositeRequest.
 */
@Data
public class CompositeRequest {

  protected List<? extends QueryComposite> compositeRequest;
  
}