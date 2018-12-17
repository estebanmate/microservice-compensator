package com.masmovil.it.compensator.api;

import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.reactivex.Single;

public interface EventOperations {

  @Get("/")
  public Single<String> getEvents();

  @Post("/{event}")
  public Single<String> pushEvent(String event);

  @Post("/topic/{idTopic}")
  public Single<String> createTopic(String idTopic);

  @Post("/topic/subscription/{idTopic}")
  public Single<String> subscribeTopic(String idTopic);

  @Post("/topic/unsubscription/{idTopic}")
  public Single<String> unsubscribeTopic(String idTopic);

}
