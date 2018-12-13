package com.masmovil.it.compensator.client.salesforce;

import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.retry.annotation.Retryable;

import javax.validation.constraints.NotBlank;

import com.masmovil.it.compensator.model.Ticket;
import com.masmovil.it.compensator.model.salesforce.SalesForceQueryResponse;

@Client("${salesforce.api-url}/${salesforce.version}")
@Retryable(delay = "200ms")
public interface TicketListSalesForceClient {

  @Get("/${salesforce.query}/")
  public SalesForceQueryResponse<Ticket> tickets(@NotBlank @QueryValue String q);

}
