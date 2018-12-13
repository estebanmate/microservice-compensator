package com.masmovil.it.compensator.controller

import io.micronaut.context.ApplicationContext
import io.micronaut.core.io.socket.SocketUtils
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.*
import io.micronaut.runtime.server.EmbeddedServer
import io.reactivex.Flowable

import java.time.LocalDateTime;
import java.util.List

import com.masmovil.it.compensator.model.Category

import spock.lang.*

import static io.micronaut.http.HttpRequest.*;

class UpdatesNotifierControllerSpec extends Specification {

	@Shared @AutoCleanup EmbeddedServer embeddedServer =
	  ApplicationContext.run(EmbeddedServer, ["redis.uri":"redis://localhost:${SocketUtils.findAvailableTcpPort()}"])

	@Shared @AutoCleanup RxHttpClient client = RxHttpClient.create(embeddedServer.URL)

  void "Get Updates Status"() {
    given:
    String date = client
      .toBlocking()
      .retrieve(GET('/updates/status'), String.class);
    expect:
    LocalDateTime.parse(date) != null;
  }
  
  void "Notify Updates from Salesforce"() {
    when: "Salesforce Updates Notification called"
    String response = client
	    .toBlocking()
      .retrieve(GET('/updates/salesforce'));
    then:
	  response == "OK";
  }
  
}