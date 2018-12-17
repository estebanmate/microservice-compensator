package com.masmovil.it.compensator.controller;

import io.micronaut.http.annotation.Controller;

import io.reactivex.Single;

import com.masmovil.it.compensator.api.EventOperations;
import com.masmovil.it.compensator.api.TmsOperations;
import com.masmovil.it.compensator.service.TmsService;

@Controller("/event")
public class EventController implements EventOperations {

  private final TmsService tmsService;

  public EventController(TmsService tmsService) {
    this.tmsService = tmsService;
  }

  /**
   * Apply credit note to subscription
   *
   * @param id id of the ticket.
   * @return A ticket.
   */
  @Override
  public Single<String> creditNote(String id) {
    return tmsService.creditNote(id);
  }

  /**
   * Apply fat to subcription
   *
   * @param id id of the ticket.
   * @return A ticket.
   */
  @Override
  public Single<String> fat(String id) {
    return tmsService.fat(id);
  }

  @Override
  public Single<String> getEvents() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Single<String> pushEvent(String id) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Single<String> createTopic(String idTopic) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Single<String> subscribeTopic(String idTopic) {
    // TODO Auto-generated method stub
    return null;
  }

}
