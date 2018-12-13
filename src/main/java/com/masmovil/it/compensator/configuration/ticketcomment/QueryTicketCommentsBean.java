package com.masmovil.it.compensator.configuration.ticketcomment;

import com.masmovil.it.compensator.model.salesforce.composite.QueryComposite;

import io.micronaut.context.annotation.EachBean;

@EachBean(QueryTicketCommentsConfiguration.class)
public class QueryTicketCommentsBean extends QueryComposite {

  /**
   * Constructor.
   * @param queryConfiguration queryConfiguration.
   */
  public QueryTicketCommentsBean(QueryTicketCommentsConfiguration queryConfiguration) {
    this.method = queryConfiguration.getMethod();
    this.url = queryConfiguration.getUrl();
    this.referenceId = queryConfiguration.getReferenceId();
    this.order = queryConfiguration.getOrder();
  }

}