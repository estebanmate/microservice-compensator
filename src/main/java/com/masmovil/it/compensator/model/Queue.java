package com.masmovil.it.compensator.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Queue implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonAlias({ "id", "Id" })
  private String id;

  @JsonAlias({ "name", "Name" })
  private String name;

  @JsonAlias({ "uniqueId", "DeveloperName" })
  private String uniqueId;

}
