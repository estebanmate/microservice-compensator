package com.masmovil.it.compensator.service;

import io.micronaut.cache.annotation.CacheConfig;
import io.micronaut.cache.annotation.CacheInvalidate;

import javax.inject.Singleton;

import com.masmovil.it.compensator.client.salesforce.TokenSalesForceClient;

@Singleton
@CacheConfig("salesforcetoken")
public class TokenSalesForceService {
  protected final TokenSalesForceClient tokenSalesForceClient;

  public TokenSalesForceService(TokenSalesForceClient tokenSalesForceClient) {
    this.tokenSalesForceClient = tokenSalesForceClient;
  }

  /**
   * Get a SalesForce token.
   * @return SalesForce token.
   */
  public String getToken() {
    return tokenSalesForceClient.getToken().getAccessToken();
  }

  @CacheInvalidate(all = true)
  public String removeToken() {
    return "ok";
  }

}