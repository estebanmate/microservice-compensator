package com.masmovil.it.compensator.client.salesforce;

import com.masmovil.it.compensator.client.tms.TmsClient;

import io.micronaut.context.annotation.Primary;
import io.reactivex.Single;

@Primary
public class TmsClientStub implements TmsClient {

  public Single<String> creditNote(String id) {
    return Single.just("OK");
  }

  public Single<String> fat(String id) {
    return Single.just("OK");
  }

}
