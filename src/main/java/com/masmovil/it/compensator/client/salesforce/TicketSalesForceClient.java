package com.masmovil.it.compensator.client.salesforce;

import com.masmovil.it.compensator.model.Ticket;
import com.masmovil.it.compensator.model.salesforce.Metadata;
import com.masmovil.it.compensator.model.salesforce.SalesForceResponse;
import com.masmovil.it.compensator.repository.redis.RedisKeyGenerator;

import io.micronaut.cache.annotation.CacheConfig;
import io.micronaut.cache.annotation.Cacheable;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.retry.annotation.Retryable;
import io.reactivex.Single;

@Client("${salesforce.api-url}/${salesforce.version}/${salesforce.ticket}")
@Retryable(delay = "200ms")
@CacheConfig("salesforcemetadata")
public interface TicketSalesForceClient {

  @Post
  public Single<SalesForceResponse> create(@Body Ticket ticket);

  @Get("/{id}")
  public Single<Ticket> ticket(String id);

  @Get("/${salesforce.metadata}")
  @Cacheable(keyGenerator = RedisKeyGenerator.class)
  public Metadata metadata();

}
