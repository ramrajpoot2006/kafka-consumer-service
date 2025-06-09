package com.ms.kafkapoc.service;


import com.ms.kafkapoc.config.LatchManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
@DirtiesContext
@EmbeddedKafka(
    partitions = 1,
    topics = {"${spring.kafka.topic.name}"},
    brokerProperties = {
        "${zookeeper.session.timeout.ms}", //extended timeout
        "${kafka.broker.listeners}",
        "${kafka.broker.port}"
    })
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ConsumerServiceTest {

  @Autowired
  EmbeddedKafkaBroker embeddedKafkaBroker;

  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;

  @Autowired
  private ConsumerService consumerService;

  @Autowired
  private LatchManager latchManager;

  @Value("${spring.kafka.topic.name}")
  private String topic;


  @BeforeEach
  void setup () {
    // Set required Kafka producer configs
    Map<String, Object> senderProps = KafkaTestUtils.producerProps(embeddedKafkaBroker);
    ProducerFactory<String, String> pf = new DefaultKafkaProducerFactory<>(senderProps);
    kafkaTemplate = new KafkaTemplate<>(pf);
  }


  @Test
  void consumerTest () throws InterruptedException, ExecutionException {

    String message = "Test consumer kafka "+new Date();
    kafkaTemplate.send(topic, message);

    // Wait for listener to consume the message
    //Thread.sleep(2000);
    boolean received = latchManager.latch.await(5, TimeUnit.SECONDS);

    assertTrue(received, "Message was not consumed by ConsumerService.");

  }

}
