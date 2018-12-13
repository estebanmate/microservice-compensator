package com.masmovil.it.compensator.util;

import io.micronaut.http.HttpRequest;

import java.util.HashMap;
import java.util.Map;

public final class MasMovilUtils {

  public static class Header {
    public static final String xCorrelationId = "X-Correlation-ID";
    public static final String xRequestId = "X-Request-ID";

    public static String generateUniqueId() {
      return String.valueOf(System.currentTimeMillis());
    }
  }

  public static class Log {
    public static final String data = "data";
    public static final String path = "path";
    public static final String methodName = "methodName";
  }

  /**
   * Get Mas movil headers.
   * 
   * @return Map with headers.
   */
  public static Map<CharSequence, CharSequence> getMasMovilHeaders(HttpRequest<?> targetRequest) {
    Map<CharSequence, CharSequence> headers = new HashMap<>();

    headers.put(Header.xCorrelationId, getHeader(targetRequest, Header.xCorrelationId));
    headers.put(Header.xRequestId, getHeader(targetRequest, Header.xRequestId));

    return headers;
  }

  /**
   * Get Log info.
   * 
   * @return Map with log info.
   */
  public static Map<CharSequence, Object> getLogInfo(HttpRequest<?> targetRequest) {
    Map<CharSequence, Object> data = getAllHeaders(targetRequest);
    data.putAll(getRequestData(targetRequest));
    return data;
  }

  /**
   * Get all request headers.
   * 
   * @return Map with headers.
   */
  public static Map<CharSequence, Object> getAllHeaders(HttpRequest<?> targetRequest) {
    Map<CharSequence, Object> headers = new HashMap<>();

    targetRequest.getHeaders().iterator().forEachRemaining(entry -> {
      if (!"Authorization".equals(entry.getKey())) {
        headers.put(entry.getKey(), entry.getValue().get(0));
      }
    });

    return headers;
  }

  /**
   * Get request data
   * 
   * @return Map with request data.
   */
  public static Map<CharSequence, Object> getRequestData(HttpRequest<?> targetRequest) {
    Map<CharSequence, Object> data = new HashMap<>();

    data.put(Log.path, targetRequest.getPath());
    data.put(Log.methodName, targetRequest.getMethod().name());
    if (targetRequest.getBody().isPresent()) {
      data.put(Log.data, targetRequest.getBody().get());
    }

    return data;
  }

  private static String getHeader(HttpRequest<?> targetRequest, String headerName) {
    return targetRequest.getHeaders().get(headerName) != null
      ? targetRequest.getHeaders().get(headerName)
      : Header.generateUniqueId();
  }

}
