package com.masmovil.it.compensator.model.salesforce;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data 
public class SalesForceQueryResponse<T> implements Serializable {

  private static final long serialVersionUID = 1L;

  private Integer totalSize;
  private Boolean done;
  private List<T> records;

}
