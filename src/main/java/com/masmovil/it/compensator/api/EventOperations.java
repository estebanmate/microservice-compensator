package com.masmovil.it.compensator.api;

import java.util.List;

import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.reactivex.Single;

public interface EventOperations {

  @Get("/")
  public Single<String> getEvents();

  @Post("/{event}")
  public Single<String> pushEvent(List<String> event);

  @Post("/topic/{idTopic}")
  public Single<String> createTopic(String idTopic);

  @Post("/topic")
  public String subscribeTopic();

  @Post("/topic/unsubscription")
  public Single<String> unsubscribeTopic();

}
