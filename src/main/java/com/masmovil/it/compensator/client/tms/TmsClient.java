package com.masmovil.it.compensator.client.tms;

import io.micronaut.http.annotation.Post;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.retry.annotation.Retryable;
import io.reactivex.Single;

@Client("${tms.api-url}/${tms.version}/${tms.entity}")
@Retryable(delay = "200ms")
public interface TmsClient {

  @Post("/${tms.endpoint-creditnote}")
  public Single<String> creditNote(String id);

  @Post("/${tms.endpoint-fat}")
  public Single<String> fat(String id);

}
