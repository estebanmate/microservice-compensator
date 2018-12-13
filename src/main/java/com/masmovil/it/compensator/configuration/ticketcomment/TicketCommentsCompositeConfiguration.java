package com.masmovil.it.compensator.configuration.ticketcomment;

import java.util.Arrays;

import javax.inject.Singleton;

import com.masmovil.it.compensator.model.salesforce.composite.CompositeRequest;

/**
 * TicketCommentsCompositeConfiguration.
 */
@Singleton
public class TicketCommentsCompositeConfiguration extends CompositeRequest {

  public TicketCommentsCompositeConfiguration(QueryTicketCommentsBean... queries) {
    this.compositeRequest = Arrays.asList(queries);
  }
  
}