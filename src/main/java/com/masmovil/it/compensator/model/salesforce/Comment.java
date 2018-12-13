package com.masmovil.it.compensator.model.salesforce;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * Comment.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Comment {

  @JsonAlias({"id", "Id"})
  private String id;
  @JsonAlias({"body", "Body"})
  private String body;
  @JsonAlias({"createdDate", "CreatedDate"})
  private String createdDate;
  @JsonAlias({"lastModifiedDate", "LastModifiedDate"})
  private String lastModifiedDate;
  @JsonAlias({"parentId", "ParentId"})
  private String parentId;
  @JsonAlias({"visibility", "Visibility"})
  private String visibility;

}