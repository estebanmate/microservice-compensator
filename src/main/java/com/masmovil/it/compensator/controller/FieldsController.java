package com.masmovil.it.compensator.controller;

import com.masmovil.it.compensator.api.FieldsOperations;
import com.masmovil.it.compensator.model.contentful.Group;
import com.masmovil.it.compensator.repository.FieldRelationshipRepository;

import io.micronaut.http.annotation.Controller;
import io.reactivex.Flowable;

@Controller("/fields")
public class FieldsController implements FieldsOperations {

  private FieldRelationshipRepository fieldRelationshipRepository;

  /**
   * Constructor.
   * @param fieldRelationshipRepository injected fieldRelationshipRepository
   */
  public FieldsController(FieldRelationshipRepository fieldRelationshipRepository) {
    this.fieldRelationshipRepository = fieldRelationshipRepository;
  }

  /**
   * Get fields of step 2 by subcategory id.
   *
   * @param id id of the subcategory.
   * @return Field.
   */
  @Override
  public Flowable<Group> fieldsSubcategory(String id) {
    return fieldRelationshipRepository.getFields(id);
  }

}
