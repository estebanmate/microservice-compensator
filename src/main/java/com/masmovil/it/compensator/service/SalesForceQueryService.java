package com.masmovil.it.compensator.service;

import com.masmovil.it.compensator.model.Category;
import com.masmovil.it.compensator.model.Queue;
import com.masmovil.it.compensator.model.Ticket;

import io.reactivex.Flowable;

public interface SalesForceQueryService {
  
  public Flowable<Category> categories();

  public Flowable<Queue> queues();

  public Flowable<Ticket> tickets(String clientNumber);

}