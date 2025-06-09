package com.ms.kafkapoc.service;


import com.ms.kafkapoc.config.LatchManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;


@Service
@Slf4j
public class ConsumerService {

  private final LatchManager latchManager;


  public ConsumerService (LatchManager latchManager) {

    this.latchManager = latchManager;
  }


  //@KafkaListener(topics = "test-topic", groupId = "test-group-id")
  @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id.name}")
  public void consume (String message, Acknowledgment ack) {

    latchManager.latch.countDown();
    log.info("Received kafka message: " + message);

    ack.acknowledge();
  }

/*
  @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id.name}")
  public void consume (Customer customer, Acknowledgment ack) {

    log.info("Received message: " + customer.toString());
    ack.acknowledge();
  }

 */

}
