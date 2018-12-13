package com.masmovil.it.compensator.util;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.contrib.json.classic.JsonLayout;
import ch.qos.logback.core.util.ContextUtil;

import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

public class CustomJsonLayout extends JsonLayout {

  private static final String HOST = "remoteHost";
  private static final String SEVERITY = "severity";
  
  @Override
  @SuppressWarnings("unchecked")
  protected void addCustomDataToJsonMap(Map<String, Object> map, ILoggingEvent event) {
    switch (event.getLevel().levelInt) {
      case Level.INFO_INT:
        add(SEVERITY, true, "INFO", map);
        break;
      case Level.WARN_INT:
        add(SEVERITY, true, "WARN", map);
        break;
      case Level.DEBUG_INT:
        add(SEVERITY, true, "DEBUG", map);
        break;
      case Level.ERROR_INT:
        add(SEVERITY, true, "ERROR", map);
        break;
      default:
        add(SEVERITY, true, "TRACE", map);
    }
    add(HOST, true, getHostName(), map);

    if (event.getArgumentArray() != null
        && event.getArgumentArray().length > 0
        && event.getArgumentArray()[0] instanceof Map) {
      Map<String,String> headers = (HashMap<String,String>) event.getArgumentArray()[0];
      map.putAll(headers);
    }
  }

  private String getHostName() {
    try {
      return ContextUtil.getLocalHostName();
    } catch (UnknownHostException | SocketException e) {
      return "undefined";
    }
  }
}
