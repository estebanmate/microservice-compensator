package com.masmovil.it.compensator.model.salesforce;

import java.io.Serializable;

import lombok.Data;

@Data
public class RecordTypeInfos implements Serializable {
  private static final long serialVersionUID = 1L;

  private String recordTypeId;
  private String developerName;

}
