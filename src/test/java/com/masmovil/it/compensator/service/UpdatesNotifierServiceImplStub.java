package com.masmovil.it.compensator.service;

import io.micronaut.context.annotation.Primary;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import javax.inject.Singleton;

import com.masmovil.it.compensator.service.UpdatesNotifierService;

@Singleton
@Primary
public class UpdatesNotifierServiceImplStub implements UpdatesNotifierService {
  private LocalDateTime updateTime;

  /**
   * Constructor.
   */
  public UpdatesNotifierServiceImplStub() {
    updateTime = LocalDateTime.ofInstant(
      Instant.now(),
      ZoneId.systemDefault());
  }

  @Override
  public String getUpdatesStatus() {
    return updateTime.toString();
  }

  @Override
  public String invalidateSalesforceCache() {
    return "OK";
  }

  @Override
  public String refreshSalesforceCache() {
    return "OK";
  }
}
