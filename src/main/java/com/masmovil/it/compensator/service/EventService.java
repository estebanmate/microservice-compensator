package com.masmovil.it.compensator.service;

import io.micronaut.context.annotation.Value;
import io.reactivex.Single;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import com.google.api.core.ApiFuture;
import com.google.api.gax.rpc.ApiException;
import com.google.cloud.pubsub.v1.Publisher;
import com.google.cloud.pubsub.v1.TopicAdminClient;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.ProjectTopicName;
import com.google.pubsub.v1.PubsubMessage;

@Singleton
public class EventService {

  @Value("${pubsub.project-id}")
  private String projectId;

  @Value("${pubsub.topic-id}")
  private String topicId;

  /**
   * Constructor.
   */
  public EventService() {
  }

  public Single<String> getEvents() {
    return null;
  }

  public Single<String> pushEvent(String event) {
    int messageCount = 1;
    ProjectTopicName topicName = ProjectTopicName.of(projectId, topicId);
    Publisher publisher = null;
    List<ApiFuture<String>> futures = new ArrayList<>();

    try {
      // Create a publisher instance with default settings bound to the topic
      publisher = Publisher.newBuilder(topicName).build();

      for (int i = 0; i < messageCount; i++) {
        String message = "message-" + i;

        // convert message to bytes
        ByteString data = ByteString.copyFromUtf8(message);
        PubsubMessage pubsubMessage = PubsubMessage.newBuilder().setData(data).build();

        System.out.printf("Event %s:[%s] Pushed.\n", pubsubMessage.getMessageId(), pubsubMessage.getPublishTime());

        // Schedule a message to be published. Messages are automatically batched.
        ApiFuture<String> future = publisher.publish(pubsubMessage);
        futures.add(future);
      }
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    if (publisher != null) {
      // When finished with the publisher, shutdown to free up resources.
      try {
        publisher.shutdown();
      } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    return Single.just("OK");
  }

  public Single<String> createTopic(String idTopic) {
    // Create a new topic
    ProjectTopicName topic = ProjectTopicName.of(projectId, idTopic);
    try (TopicAdminClient topicAdminClient = TopicAdminClient.create()) {
      topicAdminClient.createTopic(topic);
    } catch (ApiException e) {
      // example : code = ALREADY_EXISTS(409) implies topic already exists
      return Single.just("Code: ["+e.getStatusCode().getCode()+"] Retry: ["+e.isRetryable()+"]");
    } catch (IOException e1) {
      e1.printStackTrace();
    }

    return Single.just(String.format("Topic %s:%s created.", topic.getProject(), topic.getTopic()));
  }

  public Single<String> subscribeTopic(String idTopic) {
    return null;
  }

  public Single<String> unsubscribeTopic(String idTopic) {
    return null;
  }

}