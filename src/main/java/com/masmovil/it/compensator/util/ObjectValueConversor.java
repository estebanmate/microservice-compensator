package com.masmovil.it.compensator.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public final class ObjectValueConversor {

  private static ObjectMapper objectMapper = new ObjectMapper();

  /**
   * Parse LinkedMap to a List of type Objects.
   * @param value The value to convert.
   * @param type The type of the value to convert.
   * @return A list of Objects.
   */
  public static <T> List<T> convertValue(List<?> value, Class<T> type) {
    return objectMapper.convertValue(
      value,
      objectMapper.getTypeFactory().constructCollectionType(List.class, type)
    );
  }

}
