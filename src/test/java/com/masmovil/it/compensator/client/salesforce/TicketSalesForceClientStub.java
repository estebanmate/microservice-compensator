package com.masmovil.it.compensator.client.salesforce;

import com.masmovil.it.compensator.client.salesforce.TicketSalesForceClient;
import com.masmovil.it.compensator.model.Ticket;
import com.masmovil.it.compensator.model.salesforce.Metadata;
import com.masmovil.it.compensator.model.salesforce.SalesForceResponse;
import com.masmovil.it.compensator.service.JsonFileReader;

import io.micronaut.context.annotation.Primary;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.reactivex.Single;

@Primary
public class TicketSalesForceClientStub implements TicketSalesForceClient {

  /**
   * Ticket creation.
   * @param ticket ticket to create.
   * @return SalesForceResponse.
   */
  public Single<SalesForceResponse> create(Ticket ticket) throws HttpClientResponseException {
    if (ticket.getSubject() == null
        || "".equals(ticket.getSubject())
        || ticket.getTkt_PH_Suscripcion__c() == null
        || "".equals(ticket.getTkt_PH_Suscripcion__c())) {
      throw new HttpClientResponseException("Internal server error", HttpResponse.badRequest());
    }
    if (ticket.getId() != null) {
      throw new HttpClientResponseException(
          "The Id field should not be specified in the sobject data.",
          HttpResponse.badRequest("The Id field should not be specified in the sobject data."));
    }
    SalesForceResponse salesForceResponse = new SalesForceResponse();
    salesForceResponse.setId("Random id");
    return Single.just(salesForceResponse);
  }

  /**
   * Get a ticket.
   * @param id id of the ticket.
   * @return ticket.
   */
  public Single<Ticket> ticket(String id) {
    Ticket ticket = new Ticket();
    ticket.setId(id);
    return Single.just(ticket);
  }

  /**
   * Get ticket metadata.
   * @return metadata.
   */
  public Metadata metadata() {
    return JsonFileReader.<Metadata>stringToData(
        Metadata.class,
        "src/test/resources/metadata.json"
    );
  }

}
