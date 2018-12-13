package com.masmovil.it.compensator.client.salesforce;

import io.micronaut.cache.annotation.CacheConfig;
import io.micronaut.cache.annotation.Cacheable;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.retry.annotation.Retryable;

import javax.validation.constraints.NotBlank;

import com.masmovil.it.compensator.model.salesforce.SalesForceQueryResponse;
import com.masmovil.it.compensator.repository.redis.RedisKeyGenerator;

@Client("${salesforce.api-url}/${salesforce.version}")
@Retryable(delay = "200ms")
@CacheConfig("salesforcequery")
public interface SalesForceQueryClient {

  @Get("/${salesforce.query}/")
  @Cacheable(keyGenerator = RedisKeyGenerator.class)
  public <T> SalesForceQueryResponse<T> query(@NotBlank @QueryValue String q);

}
