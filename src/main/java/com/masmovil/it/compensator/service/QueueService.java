package com.masmovil.it.compensator.service;

import io.reactivex.Flowable;
import io.reactivex.Single;

import javax.inject.Singleton;

import com.masmovil.it.compensator.model.Queue;

@Singleton
public class QueueService {

  protected final SalesForceQueryService salesForceQueryService;

  /**
   * Constructor.
   * @param salesForceQueryService Injected salesForceQueryService
   */
  public QueueService(SalesForceQueryService salesForceQueryService) {
    this.salesForceQueryService = salesForceQueryService;
  }

  public Flowable<Queue> getQueues() {
    return salesForceQueryService.queues();
  }

  public Single<Queue> queue(String id) {
    return salesForceQueryService.queues()
      .filter(queue -> id.equals(queue.getId())).firstOrError();
  }

}