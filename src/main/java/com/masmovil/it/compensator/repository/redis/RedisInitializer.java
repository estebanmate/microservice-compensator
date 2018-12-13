package com.masmovil.it.compensator.repository.redis;

import io.micronaut.context.event.ApplicationEventListener;
import io.micronaut.runtime.server.event.ServerStartupEvent;

import javax.inject.Singleton;

import com.masmovil.it.compensator.service.UpdatesNotifierService;

@Singleton
public class RedisInitializer implements ApplicationEventListener<ServerStartupEvent> {

  private UpdatesNotifierService updatesNotifierService;

  public RedisInitializer(UpdatesNotifierService updatesNotifierService) {
    this.updatesNotifierService = updatesNotifierService;
  }
  
  @Override
  public void onApplicationEvent(ServerStartupEvent event) {
    updatesNotifierService.refreshSalesforceCache();
  }

}