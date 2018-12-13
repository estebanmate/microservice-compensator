package com.masmovil.it.compensator.controller;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Error;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.http.hateos.JsonError;

import io.reactivex.Flowable;
import io.reactivex.Single;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.Optional;

import com.masmovil.it.compensator.api.TicketOperations;
import com.masmovil.it.compensator.model.Category;
import com.masmovil.it.compensator.model.Queue;
import com.masmovil.it.compensator.model.Ticket;
import com.masmovil.it.compensator.model.TicketType;
import com.masmovil.it.compensator.service.TicketService;

@Controller("/ticket")
public class TicketController implements TicketOperations {

  private final TicketService ticketService;

  public TicketController(TicketService ticketService) {
    this.ticketService = ticketService;
  }

  /**
   * Get a ticket with id.
   *
   * @param id id of the ticket.
   * @return A ticket.
   */
  @Override
  public Single<Ticket> ticket(String id) {
    return ticketService.ticket(id);
  }

  /**
   * Create a new ticket.
   * 
   * @param ticket ticket to ceate.
   * @return Created ticket.
   */
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Created ticket"),
      @ApiResponse(responseCode = "400", description = "Bad request")
  })
  @Override
  public Single<MutableHttpResponse<Ticket>> create(Ticket ticket) {
    return ticketService.create(ticket).map(res -> HttpResponse.created(res));
  }

  /**
   * Get all ticket types.
   *
   * @return TicketType.
   */
  @Override
  public Flowable<TicketType> ticketTypes() {
    return ticketService.ticketTypes();
  }

  /**
   * Get a ticket types by id.
   *
   * @param id id of the TicketType.
   * @return TicketType.
   */
  @Override
  public Single<TicketType> ticketType(String id) {
    return ticketService.ticketType(id);
  }

  /**
   * Get a ticket list by Client.
   *
   * @param clientNumber Number of the Client.
   * @return List Of Tickets.
   */
  @Override
  public Flowable<Ticket> tickets(String clientNumber) {
    return ticketService.tickets(clientNumber);
  }

  /**
   * Get a category list by ticket type id.
   *
   * @param id id of the TicketType.
   * @return Category.
   */
  @Override
  public Flowable<Category> categoriesByTicketType(String id) {
    return ticketService.categoriesByTicketType(id);
  }

  /**
   * Get all categories.
   *
   * @return Category.
   */
  @Override
  public Flowable<Category> categories() {
    return ticketService.categories();
  }

  /**
   * Get a category by id.
   *
   * @param id id of the category.
   * @return Category.
   */
  @Override
  public Single<Category> category(String id) {
    return ticketService.category(id);
  }

  /**
   * Get a subcategory list by category id and subscriptionType.
   *
   * @param id               id of the category.
   * @param subscriptionType mobile, landline or empty.
   * @return Category.
   */
  @Override
  public Flowable<Category> subcategoriesByCategory(
      String id,
      @QueryValue
      @Parameter(description = "subscritpion type: mobile or landline", required = false)
      Optional<String> subscriptionType) {
    return ticketService.subcategoriesByCategory(id, subscriptionType);
  }

  /**
   * Get all subcategories.
   *
   * @return Category.
   */
  @Override
  public Flowable<Category> subcategories() {
    return ticketService.subcategories();
  }

  /**
   * Get a subcategory by id.
   *
   * @param id id of the subcategory.
   * @return Category.
   */
  @Override
  public Single<Category> subcategory(String id) {
    return ticketService.subcategory(id);
  }

  /**
   * Get all Queues.
   *
   * @return A List with all the queues.
   */
  @Override
  public Flowable<Queue> queues() {
    return ticketService.getQueues();
  }

  /**
   * Get a queue by id.
   *
   * @param id id of the queue.
   * @return Queue.
   */
  @Override
  public Single<Queue> queue(String id) {
    return ticketService.queue(id);
  }

  @Error
  public HttpResponse<JsonError> error(HttpRequest<?> request, HttpClientResponseException e) {
    return HttpResponse.badRequest(new JsonError(e.getMessage()));
  }

}
