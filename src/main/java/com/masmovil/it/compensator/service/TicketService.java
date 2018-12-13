package com.masmovil.it.compensator.service;

import io.reactivex.Flowable;
import io.reactivex.Single;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.inject.Singleton;

import com.masmovil.it.compensator.client.salesforce.CompositeSalesForceClient;
import com.masmovil.it.compensator.client.salesforce.TicketSalesForceClient;
import com.masmovil.it.compensator.model.Category;
import com.masmovil.it.compensator.model.Queue;
import com.masmovil.it.compensator.model.Ticket;
import com.masmovil.it.compensator.model.TicketType;
import com.masmovil.it.compensator.model.salesforce.Comment;
import com.masmovil.it.compensator.model.salesforce.Metadata;
import com.masmovil.it.compensator.model.salesforce.composite.TicketCommentsCompositeFactory;
import com.masmovil.it.compensator.util.ObjectValueConversor;

@Singleton
public class TicketService {

  protected final TicketSalesForceClient ticketSalesForceClient;
  protected final SalesForceQueryService salesForceQueryService;
  protected final CategoryService categoryService;
  protected final QueueService queueService;
  protected final CompositeSalesForceClient compositeSalesForceClient;
  protected final TicketCommentsCompositeFactory ticketCommentsCompositeFactory;

  /**
   * Constructor.
   * @param ticketSalesForceClient Injected ticketSalesForceClient
   * @param salesForceQueryService Injected salesForceQueryService
   * @param categoryService Injected categoryService
   * @param queueService Injected queueService
   * @param compositeSalesForceClient Injected compositeSalesForceClient
   * @param ticketCommentsCompositeFactory Injected ticketCommentsCompositeFactory
   */
  public TicketService(TicketSalesForceClient ticketSalesForceClient,
      SalesForceQueryService salesForceQueryService,
      CategoryService categoryService,
      QueueService queueService,
      CompositeSalesForceClient compositeSalesForceClient,
      TicketCommentsCompositeFactory ticketCommentsCompositeFactory) {
    this.ticketSalesForceClient = ticketSalesForceClient;
    this.salesForceQueryService = salesForceQueryService;
    this.categoryService = categoryService;
    this.queueService = queueService;
    this.compositeSalesForceClient = compositeSalesForceClient;
    this.ticketCommentsCompositeFactory = ticketCommentsCompositeFactory;
  }
  
  public Flowable<Ticket> tickets(String clientNumber) {
    return salesForceQueryService.tickets(clientNumber);
  }

  /**
   * Get a {@link Ticket} and the {@link Comment} fo the ticket.
   * @param id id of the {@link Ticket}
   * @return the ticket
   */
  public Single<Ticket> ticket(String id) {
    return ticketSalesForceClient.ticket(id)
      .zipWith(
        getComments(id),
        (ticket, comments) -> {
          ticket.setComments(comments);
          return ticket;
        }
      );
  }

  /**
   * Get the comments of a ticket.
   * @param ticketId id of a {@link Ticket}
   * @return String string
   */
  public Single<List<Comment>> getComments(String ticketId) {
    return 
        compositeSalesForceClient.comments(
          ticketCommentsCompositeFactory.getCompositeByTicket(ticketId)
        )
        .map(res -> 
          ObjectValueConversor.convertValue(
            res.getCompositeResponse().get(1).getBody().getRecords(),
            Comment.class)
        );
  }

  /**
   * Creates a ticket.
   * @param ticket ticket to create.
   * @return Created ticket.
   */
  public Single<Ticket> create(Ticket ticket) {
    return ticketSalesForceClient
      .create(ticket)
      .map(res -> {
        ticket.setId(res.getId());
        return ticket;
      });
  }

  /**
   * Get the ticket metadata.
   * @return Metadata
   */
  public Single<Metadata> metadata() {
    return Single.just(ticketSalesForceClient.metadata());
  }

  /**
   * Get ticket types.
   * @return Ticket types.
   */
  public Flowable<TicketType> ticketTypes() {
    return categoryService.getCategories()
            .map(cat -> new TicketType(cat.getCaseType(), null, cat.getServiceProviderName()))
            .distinct()
            .toList()
            .zipWith(
                metadata(),
                (ticketTypes, metadata) ->
                  metadata.getRecordTypeInfos().stream()
                    .filter(recordType ->
                      ticketTypes.contains(
                        new TicketType(recordType.getDeveloperName(), null, null)
                      )
                    )
                    .map(recordType ->
                        new TicketType(
                            recordType.getDeveloperName(),
                            recordType.getRecordTypeId(),
                            ticketTypes.stream()
                              .filter(ticketType ->
                                ticketType.equals(
                                  new TicketType(recordType.getDeveloperName(), null, null)
                                )
                              )
                              .findFirst()
                              .get()
                              .getName()
                        )
                    )
                    .collect(Collectors.toList())
            )
            .toFlowable()
            .flatMap(ticketTypes -> Flowable.fromIterable(ticketTypes));
  }

  /**
   * Get a ticket type by id.
   * @param id Id of the ticket type.
   * @return A ticket type.
   */
  public Single<TicketType> ticketType(String id) {
    return ticketTypes()
      .filter(ticket -> id.equals(ticket.getRecordTypeId()))
      .singleOrError();
  }

  /**
   * Get a list of categories by ticket type id.
   * @param id Id of the ticket type.
   * @return Categories.
   */
  public Flowable<Category> categoriesByTicketType(String id) {
    return Flowable.combineLatest(
      ticketType(id).toFlowable(),
      categoryService.getCategories(),
      (ticketType, category) -> {
        if (category.getCaseType().equals(ticketType.getDeveloperName())) {
          return category;
        }
        return new Category();
      }
    )
    .onErrorResumeNext(Flowable.empty())
    .filter(cat -> cat.getId() != null);
  }

  public Flowable<Category> categories() {
    return categoryService.getCategories();
  }

  public Single<Category> category(String id) {
    return categoryService.category(id);
  }

  public Flowable<Category> subcategoriesByCategory(String id, Optional<String> subscriptionType) {
    return categoryService.getSubcategoryList(id, subscriptionType);
  }

  public Single<Category> subcategory(String id) {
    return categoryService.subcategory(id, Optional.empty());
  }

  public Flowable<Category> subcategories() {
    return categoryService.getAllSubcategoryList(Optional.empty());
  }

  public Flowable<Queue> getQueues() {
    return queueService.getQueues();
  }

  public Single<Queue> queue(String id) {
    return queueService.queue(id);
  }

}