package com.masmovil.it.compensator.api;

import com.fasterxml.jackson.core.JsonProcessingException;

import io.micronaut.http.annotation.Get;

public interface UpdatesNotifierOperations {

  @Get("/status")
  public String notifyToFront();

  @Get("/salesforce")
  public String notifyFromSalesforce() throws JsonProcessingException;

}
