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

import com.masmovil.it.compensator.model.Category
import com.masmovil.it.compensator.model.Queue
import com.masmovil.it.compensator.model.Ticket
import com.masmovil.it.compensator.model.TicketType

import spock.lang.*

class TicketControllerSpec extends Specification {

  @Shared @AutoCleanup EmbeddedServer embeddedServer =
	  ApplicationContext.run(EmbeddedServer, ["redis.uri":"redis://localhost:${SocketUtils.findAvailableTcpPort()}"])

  @Shared @AutoCleanup RxHttpClient client = RxHttpClient.create(embeddedServer.URL)

  // TICKET CREATION
  void "Create a ticket"() {
    given: 'A new ticket'
    Ticket ticket = new Ticket()
    ticket.subject = 'Test ticket'
    ticket.tkt_PH_Suscripcion__c = 'Test ticket'
    when: 'Ticket creation is called'
    HttpResponse<Ticket> response = client
      .toBlocking()
      .exchange(POST('/ticket', ticket), Ticket.class)
	  then: 'A ticket is created and 201 is returned'
    response.body.get().id != null;
    response.status == HttpStatus.CREATED;
  }

  void "Ticket with id"() {
    given: 'A ticket with an id'
    Ticket ticket = new Ticket()
    ticket.id = 'An id'
    ticket.subject = 'Test ticket'
    ticket.tkt_PH_Suscripcion__c = 'Test ticket'
    when: 'Ticket creation is called'
    client
      .toBlocking()
      .exchange(POST('/ticket', ticket), Ticket.class)

    then: 'A 400 response is returned'
    def e = thrown(HttpClientResponseException)
    e.response.status == HttpStatus.BAD_REQUEST
  }

  void "Ticket with validation errors"() {
    given: 'A ticket with an id'
    Ticket ticket = new Ticket()
    when: 'Ticket creation is called'
    client
      .toBlocking()
      .exchange(POST('/ticket', ticket), Ticket.class)

    then: 'A 400 response is returned'
    def e = thrown(HttpClientResponseException)
    e.response.status == HttpStatus.BAD_REQUEST
  }


  // TICKET TYPE GET
  void "Get ticket types"() {
    when: "Get ticket type called"
	  then:
    client
      .toBlocking()
      .retrieve(GET('/ticket/type'), List.class)
      .get(0)
      .asType(TicketType.class)
      .recordTypeId != null;
  }


  // CATEGORIES AND SUBCATEGORIES
  void "Get all categories by ticket type"() {
    when: "Get category by ticket type called"
	  then:
    client
      .toBlocking()
      .retrieve(GET('/ticket/type/0121j0000004PXNAA2/category'), List.class)
      .get(0)
      .asType(Category.class)
      .id != null;
  }

  void "Get all categories"() {
    when: "Get category called"
	  then:
    client
      .toBlocking()
      .retrieve(GET('/ticket/category'), List.class)
      .get(0)
      .asType(Category.class)
      .id != null;
  }

  void "Get a category"() {
    when: "Get category with id called"
	  then:
    client
      .toBlocking()
      .retrieve(GET('/ticket/category/a6o1j0000008OVOAA2'), Category.class)
      .id != null;
  }

  void "Get all subcategories of a category"() {
    when: "Get subcategory called"
	  then:
    client
      .toBlocking()
      .retrieve(GET('/ticket/category/a6o1j0000008OVOAA2/subcategory'), List.class)
      .get(0)
      .asType(Category.class)
	    .id != null;
  }

  void "Get all mobile subcategories of a category"() {
    when: "Get subcategory called"
	  then:
    client
      .toBlocking()
      .retrieve(GET('/ticket/category/a6o1j0000008OVOAA2/subcategory?subscriptionType=mobile'), List.class)
      .get(0)
      .asType(Category.class)
	    .id != null;
  }

  void "Get all landline subcategories of a category"() {
    when: "Get subcategory called"
	  then:
    client
      .toBlocking()
      .retrieve(GET('/ticket/category/a6o1j0000008OVOAA2/subcategory?subscriptionType=landline'), List.class)
      .get(0)
      .asType(Category.class)
	    .getId() != null;
  }

  void "Get all subcategories"() {
    when: "Get subcategory called"
	  then:
    client
      .toBlocking()
      .retrieve(GET('/ticket/subcategory'),  List.class)
      .get(0)
      .asType(Category.class)
	    .id != null;
  }

  void "Get a subcategory"() {
    when: "Get subcategory with id called"
	  then:
    client
      .toBlocking()
      .retrieve(GET('/ticket/subcategory/a6o1j0000008OVPAA2'), Category.class)
      .getId() != null;
  }
  
  void "Get all queues"() {
	  when: "Get queue called"
		then:
	  client
		.toBlocking()
		.retrieve(GET('/ticket/queue'), List.class)
		.get(0)
		.asType(Queue.class)
		.id != null;
	}
  
	void "Get a queue"() {
	  when: "Get queue with id called"
		then:
	  client
		.toBlocking()
		.retrieve(GET('/ticket/queue/00G58000000xs38EAA'), Queue.class)
		.id != null;
	}
  

}