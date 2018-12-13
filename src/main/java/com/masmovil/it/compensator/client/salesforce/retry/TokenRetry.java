package com.masmovil.it.compensator.client.salesforce.retry;

import io.micronaut.retry.event.RetryEvent;
import io.micronaut.retry.event.RetryEventListener;

import javax.inject.Singleton;

import com.masmovil.it.compensator.service.TokenSalesForceService;

@Singleton
public class TokenRetry implements RetryEventListener {

  private final TokenSalesForceService tokenSalesForceService;

  public TokenRetry(TokenSalesForceService tokenSalesForceService) {
    this.tokenSalesForceService = tokenSalesForceService;
  }

  /**
   * Handle an application event.
   *
   * @param event the event to respond to
   */
  @Override
  public void onApplicationEvent(RetryEvent event) {
    if (event.getThrowable().toString().contains("Session expired or invalid")) {
      tokenSalesForceService.removeToken();
    }
  }
}
