package com.masmovil.it.compensator.controller

import io.micronaut.context.ApplicationContext
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.*
import io.micronaut.http.client.exceptions.HttpClientResponseException
import io.micronaut.runtime.server.EmbeddedServer

import static io.micronaut.http.HttpRequest.*

import spock.lang.*

class TmsControllerSpec extends Specification {

  @Shared @AutoCleanup EmbeddedServer embeddedServer =
	  ApplicationContext.run(EmbeddedServer)

  @Shared @AutoCleanup RxHttpClient client = RxHttpClient.create(embeddedServer.URL)

	void "Apply a Credit Note to a Subscription"() {
	  when: "Apply a Credit Note called"
    HttpResponse<String> response = client
        .toBlocking()
        .exchange(POST('/revenue/subscriptions-apply-credit-note/00G58000000xs38EAA',''), String.class)
    then:
    response.body.get() == "OK";
	}

    void "Apply a FAT to a Subscription"() {
    when: "Apply a FAT called"
    HttpResponse<String> response = client
        .toBlocking()
    .exchange(POST('/revenue/subscriptions-apply-fat/00G58000000xs38EAA',''), String.class)
    then:
    response.body.get() == "OK";
  }


}