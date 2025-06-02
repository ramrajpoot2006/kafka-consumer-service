package com.ms.kafkapoc.service;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;

import java.util.concurrent.ExecutionException;


@SpringBootTest
@DirtiesContext
@EmbeddedKafka(partitions = 1, brokerProperties = {"listeners=PLAINTEXT://localhost:9092", "port=9092"})
public class ConsumerServiceTest {

  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;

  @Autowired
  private ConsumerService consumerService;


  @Test
  void consumerTest () throws InterruptedException, ExecutionException {

    kafkaTemplate.send("test-topic", "Test consumer kafka");

    // Wait for listener to consume the message
    Thread.sleep(2000); // Prefer awaitility for real tests

    System.out.println("hello");
    //assertTrue(consumerService.);

  }

}
