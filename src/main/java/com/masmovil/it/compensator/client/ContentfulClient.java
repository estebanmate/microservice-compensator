package com.masmovil.it.compensator.client;

import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.http.client.annotation.Client;
import io.reactivex.Single;

import javax.validation.constraints.NotBlank;

import com.masmovil.it.compensator.model.contentful.Model;

@Client("${contentful.client.id}")
public interface ContentfulClient {

  @Get("/${contentful.method.content}/${contentful.model.ticketing}")
  public Single<Model> getSubcategoryData(@NotBlank @QueryValue String subcategory);

}
