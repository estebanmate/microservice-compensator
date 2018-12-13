package com.masmovil.it.compensator.client.salesforce;

import com.masmovil.it.compensator.client.salesforce.TokenSalesForceClient;
import com.masmovil.it.compensator.model.salesforce.TokenSalesForce;

import io.micronaut.context.annotation.Primary;

@Primary
public class TokenSalesForceClientStub implements TokenSalesForceClient {

  /**
   * Get a token.
   * @return token.
   */
  public TokenSalesForce getToken() {
    TokenSalesForce token = new TokenSalesForce();
    token.setAccessToken("token");
    return token;
  }
}
