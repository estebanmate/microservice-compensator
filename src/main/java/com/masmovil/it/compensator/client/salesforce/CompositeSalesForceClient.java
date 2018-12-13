package com.masmovil.it.compensator.client.salesforce;

import com.masmovil.it.compensator.model.salesforce.composite.CompositeRequest;
import com.masmovil.it.compensator.model.salesforce.composite.CompositeResponse;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Consumes;
import io.micronaut.http.annotation.Post;

import io.micronaut.http.client.annotation.Client;
import io.micronaut.retry.annotation.Retryable;
import io.reactivex.Single;


@Client("${salesforce.api-url}/${salesforce.version}/${salesforce.composite}")
@Retryable(delay = "200ms")
public interface CompositeSalesForceClient {

  @Post
  @Consumes(MediaType.APPLICATION_JSON)
  public Single<CompositeResponse> comments(@Body CompositeRequest compositeRequest);

}
