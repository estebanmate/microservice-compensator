package com.masmovil.it.compensator.repository;

import io.reactivex.Flowable;
import io.reactivex.Single;

import java.util.ArrayList;

import javax.inject.Singleton;

import com.masmovil.it.compensator.client.ContentfulClient;
import com.masmovil.it.compensator.model.contentful.Group;
import com.masmovil.it.compensator.model.salesforce.Field;
import com.masmovil.it.compensator.service.TicketService;

@Singleton
public class FieldRelationshipRepositoryImpl implements FieldRelationshipRepository {

  private ContentfulClient contentfulClient;
  private TicketService ticketService;

  public FieldRelationshipRepositoryImpl(ContentfulClient contentfulClient,
      TicketService ticketService) {
    this.contentfulClient = contentfulClient;
    this.ticketService = ticketService;
  }

  @Override
  public Flowable<Group> getFields(String subcategoryId) {
    return Single.zip(
        ticketService.metadata().map(metadata -> metadata.getFields()),
        contentfulClient.getSubcategoryData(subcategoryId),
        (metadataFields, model) -> {
          model.getGroups()
              .forEach(group -> {
                group.setFieldsInfo(new ArrayList<>());
                group.getFieldNames().forEach(fieldName -> {
                  Field field = metadataFields.stream()
                      .filter(metadataField -> metadataField.getName().equals(fieldName))
                      .findFirst().orElse(null);
                  if (field != null) {
                    group.getFieldsInfo().add(field);
                  }
                });
              });
          return model;
        }
    ).flatMapPublisher(model -> Flowable.fromIterable(model.getGroups()));
  }

}