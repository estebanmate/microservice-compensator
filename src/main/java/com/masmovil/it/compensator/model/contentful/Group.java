package com.masmovil.it.compensator.model.contentful;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.masmovil.it.compensator.model.salesforce.Field;

import java.util.List;

import javax.annotation.Nullable;

import lombok.Data;

@Data
public class Group {

  @Nullable
  private String label;
  @JsonAlias({"fields", "fieldNames"})
  private List<String> fieldNames;
  private List<Field> fieldsInfo;
  
}
