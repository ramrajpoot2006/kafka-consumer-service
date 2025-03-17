package com.ms.kafkapoc.service;


import com.ms.kafkapoc.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class ConsumerService {

  @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id.name}")
  public void consume (String message, Acknowledgment ack) {

    log.info("Received message: " + message);
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
