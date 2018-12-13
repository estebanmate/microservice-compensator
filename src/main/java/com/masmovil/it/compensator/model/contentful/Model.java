package com.masmovil.it.compensator.model.contentful;

import java.util.List;

import lombok.Data;

@Data
public class Model {
  private String subcategory;
  private String subcategoryLabel;
  private List<Group> groups;
}
