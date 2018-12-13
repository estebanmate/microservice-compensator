package com.masmovil.it.compensator.model.salesforce;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.io.Serializable;

import lombok.Data;

@Data
public class TokenSalesForce implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonAlias({"access_token", "accessToken"})
  private String accessToken;
  @JsonAlias({"instance_url", "instanceUrl"})
  private String instanceUrl;
  private String id;
  @JsonAlias({"token_type", "tokenType"})
  private String tokenType;
  @JsonAlias({"issued_at", "issuedAt"})
  private String issuedAt;
  private String signature;

}
