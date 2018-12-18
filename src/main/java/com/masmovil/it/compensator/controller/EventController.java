package com.masmovil.it.compensator.controller;

import io.micronaut.http.annotation.Controller;

import io.reactivex.Single;

import java.util.List;

import com.masmovil.it.compensator.api.EventOperations;
import com.masmovil.it.compensator.service.EventService;

@Controller("/event")
public class EventController implements EventOperations {

  private final EventService eventService;

  public EventController(EventService eventService) {
    this.eventService = eventService;
  }

  @Override
  public Single<String> getEvents() {
    return eventService.getEvents();
  }

  @Override
  public Single<String> pushEvent(List<String> event) {
    return eventService.pushEvent(event);
  }

  @Override
  public Single<String> createTopic(String idTopic) {
    return eventService.createTopic(idTopic);
  }

  @Override
  public Single<String> subscribeTopic() {
    return eventService.subscribeTopic();
  }

  @Override
  public Single<String> unsubscribeTopic() {
    return eventService.unsubscribeTopic();
  }

}
