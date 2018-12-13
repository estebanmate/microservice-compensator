package com.masmovil.it.compensator.service;

public interface UpdatesNotifierService {

  public String getUpdatesStatus();

  public String invalidateSalesforceCache();

  public String refreshSalesforceCache();
}