package com.masmovil.it.compensator.model.salesforce.composite;

import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;

import javax.inject.Singleton;

import com.masmovil.it.compensator.configuration.ticketcomment.TicketCommentsCompositeConfiguration;

@Factory
public class TicketCommentsCompositeFactory {
  
  private TicketCommentsCompositeConfiguration compositeTicketCommentsConfiguration;

  public TicketCommentsCompositeFactory(
      TicketCommentsCompositeConfiguration compositeTicketCommentsConfiguration) {
    this.compositeTicketCommentsConfiguration = compositeTicketCommentsConfiguration;
  }

  /** 
   * Get an object to obatin the commens of a ticket through a composite request.
   * @param id id of the {@link com.masmovil.it.compensator.model.Ticket}
   * @return {@link CompositeRequest} composite request
  */
  @Bean
  @Singleton
  public CompositeRequest getCompositeByTicket(String id) {
    CompositeRequest compositeRequest = 
        new CompositeRequest();
    compositeRequest.setCompositeRequest(
        compositeTicketCommentsConfiguration.getCompositeRequest());

    compositeRequest.getCompositeRequest().sort(
        (q1, q2) -> q1.getOrder().compareTo(q2.getOrder())
    );

    compositeRequest.getCompositeRequest().get(0).setUrl(
        String.format(
          compositeRequest.getCompositeRequest().get(0).getUrl(),
          id
        )
    );
    return compositeRequest;
  }

}