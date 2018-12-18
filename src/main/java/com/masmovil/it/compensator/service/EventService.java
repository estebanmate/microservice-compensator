package com.masmovil.it.compensator.service;

import io.micronaut.context.annotation.Value;
import io.reactivex.Single;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.LinkedBlockingDeque;

import javax.inject.Singleton;

import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutures;
import com.google.api.gax.rpc.ApiException;
import com.google.cloud.pubsub.v1.AckReplyConsumer;
import com.google.cloud.pubsub.v1.MessageReceiver;
import com.google.cloud.pubsub.v1.Publisher;
import com.google.cloud.pubsub.v1.Subscriber;
import com.google.cloud.pubsub.v1.SubscriptionAdminClient;
import com.google.cloud.pubsub.v1.TopicAdminClient;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.ProjectSubscriptionName;
import com.google.pubsub.v1.ProjectTopicName;
import com.google.pubsub.v1.PubsubMessage;
import com.google.pubsub.v1.PushConfig;
import com.google.pubsub.v1.Subscription;

@Singleton
public class EventService {

  @Value("${pubsub.project-id}")
  private String projectId;

  @Value("${pubsub.topic-id}")
  private String topicId;

  @Value("${pubsub.subscription-id}")
  private String subscriptionId;

  /**
   * Constructor.
   */
  public EventService() {
  }

  private static final BlockingQueue<PubsubMessage> messages = new LinkedBlockingDeque<>();

  static class MessageReceiverExample implements MessageReceiver {

    @Override
    public void receiveMessage(PubsubMessage message, AckReplyConsumer consumer) {
      messages.offer(message);
      consumer.ack();
    }
  }

  public Single<String> getEvents() {
    ProjectSubscriptionName subscriptionName = ProjectSubscriptionName.of(projectId, subscriptionId);
    Subscriber subscriber = null;
    try {
      // create a subscriber bound to the asynchronous message receiver
      subscriber = Subscriber.newBuilder(subscriptionName, new MessageReceiverExample()).build();
      subscriber.startAsync().awaitRunning();
      // Continue to listen to messages
      while (true) {
        PubsubMessage message = messages.take();
        System.out.println("Message Id: " + message.getMessageId());
        System.out.println("Data: " + message.getData().toStringUtf8());
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      if (subscriber != null) {
        subscriber.stopAsync();
      }
    }
    return Single.just(String.format("Messages received..."));
  }

  public Single<String> pushEvent(List<String> event) {
    int messageCount = event.size();
    ProjectTopicName topicName = ProjectTopicName.of(projectId, topicId);
    Publisher publisher = null;
    List<ApiFuture<String>> futures = new ArrayList<>();

    try {
      // Create a publisher instance with default settings bound to the topic
      publisher = Publisher.newBuilder(topicName).build();

      for (int i = 0; i < messageCount; i++) {
        String message = "message-" + i + ":[" + event.get(i) + "]";

        // convert message to bytes
        ByteString data = ByteString.copyFromUtf8(message);
        PubsubMessage pubsubMessage = PubsubMessage.newBuilder().setData(data).build();

        // Schedule a message to be published. Messages are automatically batched.
        ApiFuture<String> future = publisher.publish(pubsubMessage);
        futures.add(future);
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      List<String> messageIds;
      try {
        messageIds = ApiFutures.allAsList(futures).get();
        for (String messageId : messageIds) {
          System.out.printf("Event [%s] Pushed.\n", messageId);
        }
      } catch (InterruptedException | ExecutionException e1) {
        e1.printStackTrace();
      }

      if (publisher != null) {
        // When finished with the publisher, shutdown to free up resources.
        try {
          publisher.shutdown();
        } catch (Exception e) {
          e.printStackTrace();
        }
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
      return Single.just("Topic Code: [" + e.getStatusCode().getCode() + "] Topic Retry: [" + e.isRetryable() + "]");
    } catch (IOException e1) {
      e1.printStackTrace();
    }

    return Single.just(String.format("Topic %s:%s created.", topic.getProject(), topic.getTopic()));
  }

  @SuppressWarnings("unused")
  public String subscribeTopic() {
//    return "Project: ["+projectId+"] Topic: : [" + topicId + "] Subscription: [" + subscriptionId + "]";
    System.out.println("Project: ["+projectId+"] Topic: : [" + topicId + "] Subscription: [" + subscriptionId + "]");
    ProjectTopicName topicName = ProjectTopicName.of(projectId, topicId);

    // Create a new subscription
    ProjectSubscriptionName subscriptionName = ProjectSubscriptionName.of(projectId, subscriptionId);
    System.out.println("Topic: : [" + topicName + "] Subscription: [" + subscriptionName + "]");
    try (SubscriptionAdminClient subscriptionAdminClient = SubscriptionAdminClient.create()) {
      // create a pull subscription with default acknowledgement deadline (= 10
      // seconds)
      Subscription subscription = subscriptionAdminClient.createSubscription(subscriptionName, topicName,
          PushConfig.getDefaultInstance(), 0);
    } catch (ApiException e) {
      // example : code = ALREADY_EXISTS(409) implies subscription already exists
      return "Project: ["+projectId+"] Topic: : [" + topicId + "] Subscription: [" + subscriptionId + "]";
      //return "Code: [" + e.getStatusCode().getCode() + "] Retry: [" + e.isRetryable() + "]";
    } catch (IOException e1) {
      e1.printStackTrace();
    }

    return String.format("Subscription %s:%s created.\n", subscriptionName.getProject(),
        subscriptionName.getSubscription());
  }

  public Single<String> unsubscribeTopic() {
    return null;
  }

}