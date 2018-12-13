package com.masmovil.it.compensator.api;

import io.micronaut.http.MediaType;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Consumes;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Header;
import io.micronaut.http.annotation.Post;
import io.reactivex.Flowable;
import io.reactivex.Single;

import java.util.Optional;

import com.masmovil.it.compensator.model.Category;
import com.masmovil.it.compensator.model.Queue;
import com.masmovil.it.compensator.model.Ticket;
import com.masmovil.it.compensator.model.TicketType;

public interface TicketOperations {

  @Get("/{id}")
  public Single<Ticket> ticket(String id);

  @Post("/")
  @Consumes(MediaType.APPLICATION_JSON)
  public Single<MutableHttpResponse<Ticket>> create(@Body Ticket ticket);

  @Get("/")
  public Flowable<Ticket> tickets(@Header("X-Customer-ID") String clientNumber);

  @Get("/type")
  public Flowable<TicketType> ticketTypes();

  @Get("/type/{id}")
  public Single<TicketType> ticketType(String id);

  @Get("/type/{id}/category")
  public Flowable<Category> categoriesByTicketType(String id);

  @Get("/category")
  public Flowable<Category> categories();

  @Get("/category/{id}")
  public Single<Category> category(String id);

  @Get("/category/{id}/subcategory{?subscriptionType}")
  public Flowable<Category> subcategoriesByCategory(String id, Optional<String> subscriptionType);

  @Get("/subcategory")
  public Flowable<Category> subcategories();

  @Get("/subcategory/{id}")
  public Single<Category> subcategory(String id);

  @Get("/queue")
  public Flowable<Queue> queues();

  @Get("/queue/{id}")
  public Single<Queue> queue(String id);

}
