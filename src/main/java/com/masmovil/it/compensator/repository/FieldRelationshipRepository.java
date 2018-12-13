package com.masmovil.it.compensator.repository;

import com.masmovil.it.compensator.model.contentful.Group;

import io.reactivex.Flowable;

public interface FieldRelationshipRepository {

  public Flowable<Group> getFields(String subcategoryId);
  
}