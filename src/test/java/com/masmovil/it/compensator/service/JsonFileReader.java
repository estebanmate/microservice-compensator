package com.masmovil.it.compensator.service;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class JsonFileReader {

  private static final Logger LOG = LoggerFactory.getLogger(JsonFileReader.class);

  /**
   * Json deserializer.
   * @param typeParameterClass class to read.
   * @param file Json file.
   * @return T Java object.
   */
  public static <T> T stringToData(Class<T> typeParameterClass, String file) {
    ObjectMapper mapper = new ObjectMapper();
    try {
      return mapper.readValue(new File(file), typeParameterClass);
    } catch (IOException e) {
      LOG.error(e.getMessage());
    }
    return null;
  }

  /**
   * Json list deselializer.
   * 
   * @param typeParameterClass class to read.
   * @param file Json file.
   * @return Java list of objects.
   */
  public static <T> List<T> stringToDataList(Class<T> typeParameterClass, String file) {
    ObjectMapper mapper = new ObjectMapper();
    try {
      return mapper.readValue(new File(file),
          mapper.getTypeFactory().constructCollectionType(List.class, typeParameterClass));
    } catch (IOException e) {
      LOG.error(e.getMessage());
    }
    return null;
  }

}