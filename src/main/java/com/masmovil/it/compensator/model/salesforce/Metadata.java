package com.masmovil.it.compensator.model.salesforce;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class Metadata implements Serializable {
  private static final long serialVersionUID = 1L;

  private List<Field> fields;
  private List<RecordTypeInfos> recordTypeInfos;

}
