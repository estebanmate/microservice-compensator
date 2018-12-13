package com.masmovil.it.compensator.model.salesforce;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class Field implements Serializable {
  private static final long serialVersionUID = 1L;

  private String name;
  private String label;
  private String type;
  @JsonAlias("picklistValues")
  private List<Option> options;

}
