package com.masmovil.it.compensator.api;

import io.micronaut.http.annotation.Post;
import io.reactivex.Single;

public interface TmsOperations {

  @Post("/credit_note/{id}")
  public Single<String> creditNote(String id);

  @Post("/fat/{id}")
  public Single<String> fat(String id);

}
