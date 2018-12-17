package com.masmovil.it.compensator.service;

import io.reactivex.Single;

import javax.inject.Singleton;

import com.masmovil.it.compensator.client.tms.TmsClient;
import com.masmovil.it.compensator.model.creditNoteResponse;

@Singleton
public class EventService {

  protected final TmsClient tmsClient;

  /**
   * Constructor.
   * @param tmsClient Injected tmsClient
   */
  public EventService(TmsClient tmsClient) {
    this.tmsClient = tmsClient;
  }
  
  /**
   * Apply a Credit Note to a Subscription.
   * @param id id of the {@link creditNoteResponse}
   * @return the ticket
   */
  public Single<String> creditNote(String id) {
    return tmsClient.creditNote(id);
  }

  /**
   * Apply a FAT to a Subscription.
   * @param id id of the {@link creditNoteResponse}
   * @return the ticket
   */
  public Single<String> fat(String id) {
    return tmsClient.fat(id);
  }

}