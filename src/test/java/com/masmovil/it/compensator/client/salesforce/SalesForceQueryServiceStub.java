package com.masmovil.it.compensator.client.salesforce;

import com.masmovil.it.compensator.model.Category;
import com.masmovil.it.compensator.model.Queue;
import com.masmovil.it.compensator.model.Ticket;
import com.masmovil.it.compensator.model.salesforce.SalesForceQueryResponse;
import com.masmovil.it.compensator.service.JsonFileReader;
import com.masmovil.it.compensator.service.SalesForceQueryService;

import io.micronaut.context.annotation.Primary;
import io.reactivex.Flowable;

@Primary
public class SalesForceQueryServiceStub implements SalesForceQueryService {

  private SalesForceQueryResponse<Category> categoryReponse;
  private SalesForceQueryResponse<Queue> queueReponse;
  private SalesForceQueryResponse<Ticket> ticketListResponse;

  /**
   * Constructor.
   */
  public SalesForceQueryServiceStub() {
    categoryReponse = new SalesForceQueryResponse<>();
    categoryReponse.setRecords(JsonFileReader.<Category>stringToDataList(
        Category.class,
        "src/test/resources/categories.json"
    ));

    queueReponse = new SalesForceQueryResponse<>();
    queueReponse.setRecords(JsonFileReader.<Queue>stringToDataList(
        Queue.class,
        "src/test/resources/queues.json"
    ));

    ticketListResponse = new SalesForceQueryResponse<>();
    ticketListResponse.setRecords(JsonFileReader.<Ticket>stringToDataList(
        Ticket.class,
        "src/test/resources/tickets.json"
    ));
  }

  @Override
  public Flowable<Category> categories() {
    return Flowable.fromIterable(categoryReponse.getRecords());
  }

  @Override
  public Flowable<Queue> queues() {
    return Flowable.fromIterable(queueReponse.getRecords());
  }

  @Override
  public Flowable<Ticket> tickets(String clientNumber) {
    return Flowable.fromIterable(ticketListResponse.getRecords());
  }

}
