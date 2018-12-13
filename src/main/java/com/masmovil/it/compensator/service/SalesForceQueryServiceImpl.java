package com.masmovil.it.compensator.service;

import io.micronaut.context.annotation.Value;
import io.reactivex.Flowable;

import javax.inject.Singleton;

import com.masmovil.it.compensator.client.salesforce.SalesForceQueryClient;
import com.masmovil.it.compensator.client.salesforce.TicketListSalesForceClient;
import com.masmovil.it.compensator.model.Category;
import com.masmovil.it.compensator.model.Queue;
import com.masmovil.it.compensator.model.Ticket;
import com.masmovil.it.compensator.util.ObjectValueConversor;

@Singleton
public class SalesForceQueryServiceImpl implements SalesForceQueryService {

  protected final SalesForceQueryClient salesForceQueryClient;
  protected final TicketListSalesForceClient ticketListSalesForceClient;

  @Value("${salesforce.categories-query}")
  private String categoriesQuery;

  @Value("${salesforce.queues-query}")
  private String queuesQuery;

  @Value("${salesforce.tickets-querybyclient}")
  private String ticketsQueryByClient;

  /**
   * Constructor.
   * @param salesForceQueryClient Injected salesForceQueryClient.
   * @param ticketListSalesForceClient Injectec ticketListSalesForceClient
   */
  public SalesForceQueryServiceImpl(
      SalesForceQueryClient salesForceQueryClient,
      TicketListSalesForceClient ticketListSalesForceClient) {
    this.salesForceQueryClient = salesForceQueryClient;
    this.ticketListSalesForceClient = ticketListSalesForceClient;
  }

  @Override
  public Flowable<Category> categories() {
    return Flowable.fromIterable(
      ObjectValueConversor.convertValue(
        salesForceQueryClient.<Category>query(categoriesQuery).getRecords(),
        Category.class
      )
    );
  }

  @Override
  public Flowable<Queue> queues() {
    return Flowable.fromIterable(
      ObjectValueConversor.convertValue(
        salesForceQueryClient.<Queue>query(queuesQuery).getRecords(),
        Queue.class
      )      
    );
  }

  @Override
  public Flowable<Ticket> tickets(String clientNumber) {
    return Flowable.fromIterable(
        ticketListSalesForceClient.tickets(
          String.format(ticketsQueryByClient, clientNumber)
        ).getRecords()
    );
  }

}