package com.masmovil.it.compensator.controller

import io.micronaut.context.ApplicationContext
import io.micronaut.core.io.socket.SocketUtils
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.*
import io.micronaut.http.client.exceptions.HttpClientResponseException
import io.micronaut.runtime.server.EmbeddedServer
import io.reactivex.Flowable

import static io.micronaut.http.HttpRequest.*

import java.util.List

import com.masmovil.it.compensator.model.contentful.Group

import spock.lang.*

class FieldsControllerSpec extends Specification {

  @Shared @AutoCleanup EmbeddedServer embeddedServer =
	  ApplicationContext.run(EmbeddedServer, ["redis.uri":"redis://localhost:${SocketUtils.findAvailableTcpPort()}"])

  @Shared @AutoCleanup RxHttpClient client = RxHttpClient.create(embeddedServer.URL)

  void "Get fields of a subcategory"() {
    when: 'fields is called'
	  then: 'fields are retrieved'
    client
      .toBlocking()
      .retrieve(GET('/fields/subcategory/SUB0011'), List.class)
      .get(0)
      .asType(Group.class)
      .fieldsInfo.size > 0;
  }
  
}