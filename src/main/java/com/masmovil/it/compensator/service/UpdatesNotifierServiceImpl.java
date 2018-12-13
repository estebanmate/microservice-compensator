package com.masmovil.it.compensator.service;

import io.micronaut.cache.annotation.CacheConfig;
import io.micronaut.cache.annotation.CacheInvalidate;
import io.micronaut.cache.annotation.Cacheable;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import javax.inject.Singleton;

import com.masmovil.it.compensator.repository.redis.RedisKeyGenerator;

@Singleton
@CacheConfig(cacheNames = { "updatestatus" })
public class UpdatesNotifierServiceImpl implements UpdatesNotifierService {

  private SalesForceQueryService salesForceQueryService;
  private TicketService ticketService;

  public UpdatesNotifierServiceImpl(SalesForceQueryService salesForceQueryService,
      TicketService ticketService) {
    this.salesForceQueryService = salesForceQueryService;
    this.ticketService = ticketService;
  }

  @Override
  @Cacheable(keyGenerator = RedisKeyGenerator.class)
  public String getUpdatesStatus() {
    return LocalDateTime.ofInstant(
              Instant.now(),
              ZoneId.systemDefault()).toString();
  }

  @Override
  @CacheInvalidate(cacheNames = 
      {
        "updatestatus",
        "salesforcequery",
        "salesforcemetadata"
      },
      all = true)
  /**
   * Invalidates all SalesForce caches.
   * 
   * @return OK
   */
  public String invalidateSalesforceCache() {
    return "OK";
  }
  
  @Override
  /**
   * Refresh all SalesForce caches.
   * 
   * @return OK
   */
  public String refreshSalesforceCache() {
    this.salesForceQueryService.categories().subscribe();
    this.salesForceQueryService.queues().subscribe();
    this.ticketService.metadata().subscribe();
    getUpdatesStatus();
    return "OK";
  }

}