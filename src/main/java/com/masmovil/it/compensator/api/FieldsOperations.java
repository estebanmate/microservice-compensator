package com.masmovil.it.compensator.api;

import com.masmovil.it.compensator.model.contentful.Group;

import io.micronaut.http.annotation.Get;
import io.reactivex.Flowable;

public interface FieldsOperations {

  @Get("/subcategory/{id}")
  public Flowable<Group> fieldsSubcategory(String id);

}
