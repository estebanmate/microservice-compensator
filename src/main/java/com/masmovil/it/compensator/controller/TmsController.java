package com.masmovil.it.compensator.controller;

import io.micronaut.http.annotation.Controller;

import io.reactivex.Single;

import com.masmovil.it.compensator.api.TmsOperations;
import com.masmovil.it.compensator.service.TmsService;

@Controller("/revenue")
public class TmsController implements TmsOperations {

  private final TmsService tmsService;

  public TmsController(TmsService tmsService) {
    this.tmsService = tmsService;
  }

  /**
   * Apply credit note to subscription
   *
   * @param id id of the ticket.
   * @return A ticket.
   */
  @Override
  public Single<String> creditNote(String id) {
    return tmsService.creditNote(id);
  }

  /**
   * Apply fat to subcription
   *
   * @param id id of the ticket.
   * @return A ticket.
   */
  @Override
  public Single<String> fat(String id) {
    return tmsService.fat(id);
  }

}
