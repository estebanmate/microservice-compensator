package com.masmovil.it.compensator.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.masmovil.it.compensator.client.ContentfulClient;
import com.masmovil.it.compensator.client.ContentfulClientStub;
import com.masmovil.it.compensator.model.contentful.Model;

import io.micronaut.context.annotation.Primary;
import io.reactivex.Single;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Primary
public class ContentfulClientStub implements ContentfulClient {

  private static final Logger LOG = LoggerFactory.getLogger(ContentfulClientStub.class);

  private Model model;

  /**
   * Constructor.
   */
  public ContentfulClientStub() {
    ObjectMapper mapper = new ObjectMapper();
    try {
      model = mapper.readValue(
          new File("src/test/resources/contentful.json"),
          Model.class
      );
    } catch (IOException e) {
      LOG.error(e.getMessage());
    }
  }

  public Single<Model> getSubcategoryData(String subcategory) {
    return Single.just(model);
  }

}
