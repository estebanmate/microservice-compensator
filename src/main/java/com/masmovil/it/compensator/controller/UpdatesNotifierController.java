package com.masmovil.it.compensator.controller;

import io.lettuce.core.RedisCommandExecutionException;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.masmovil.it.compensator.api.UpdatesNotifierOperations;
import com.masmovil.it.compensator.service.UpdatesNotifierService;

@Controller("/updates")
public class UpdatesNotifierController implements UpdatesNotifierOperations {

  private static final Logger LOG = LoggerFactory.getLogger(UpdatesNotifierController.class);

  protected final UpdatesNotifierService updatesNotifierService;

  public UpdatesNotifierController(UpdatesNotifierService updatesNotifierService) {
    this.updatesNotifierService = updatesNotifierService;
  }

  /**
   * Return the last SalasForce cache update Datetime.
   *
   * @return String with the last update Datetime.
   */
  @Override
  public String notifyToFront() {
    return updatesNotifierService.getUpdatesStatus();
  }

  /**
   * Refresh all SalesForce caches.
   */
  @Override
  public String notifyFromSalesforce() {
    updatesNotifierService.invalidateSalesforceCache();
    return updatesNotifierService.refreshSalesforceCache();
  }
  
  /**
   * Catch Redis error when an attemp to remove an empty key is made.
   * 
   * @param request Request made.
   * @param e Redis exception.
   * @return Ok response.
   */
  @Error
  public HttpResponse<String> error(HttpRequest<?> request, RedisCommandExecutionException e) {
    LOG.debug("Redis error trying to delete a non existing key -> "
        + e.getMessage()
        + ". OK returned");
    // If some keys is missing in Redis, we get all of them, invalides all caches,
    // and get all Redis keys again, becouse we don't know what key is missing.
    updatesNotifierService.refreshSalesforceCache();
    updatesNotifierService.invalidateSalesforceCache();
    updatesNotifierService.refreshSalesforceCache();
    return HttpResponse.ok("OK");
  }

}
