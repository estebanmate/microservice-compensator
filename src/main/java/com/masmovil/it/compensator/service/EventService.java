package com.masmovil.it.compensator.service;

import io.micronaut.context.annotation.Value;
import io.reactivex.Single;

import java.io.IOException;

import javax.inject.Singleton;

import com.google.api.gax.rpc.ApiException;
import com.google.cloud.pubsub.v1.TopicAdminClient;
import com.google.pubsub.v1.ProjectTopicName;

@Singleton
public class EventService {

  @Value("${pubsub.project-id}")
  private String projectId;

  /**
   * Constructor.
   */
  public EventService() {
  }

  public Single<String> getEvents() {
    return null;
  }

  public Single<String> pushEvent(String event) {
    return null;
  }

  public Single<String> createTopic(String idTopic) {
    // Create a new topic
    ProjectTopicName topic = ProjectTopicName.of(projectId, idTopic);
    try (TopicAdminClient topicAdminClient = TopicAdminClient.create()) {
      topicAdminClient.createTopic(topic);
    } catch (ApiException e) {
      // example : code = ALREADY_EXISTS(409) implies topic already exists
      System.out.print(e.getStatusCode().getCode());
      System.out.print(e.isRetryable());
    } catch (IOException e1) {
      e1.printStackTrace();
    }

    System.out.printf("Topic %s:%s created.\n", topic.getProject(), topic.getTopic());
    return Single.just(String.format("Topic %s:%s created.", topic.getProject(), topic.getTopic()));
  }

  public Single<String> subscribeTopic(String idTopic) {
    return null;
  }

  public Single<String> unsubscribeTopic(String idTopic) {
    return null;
  }

}