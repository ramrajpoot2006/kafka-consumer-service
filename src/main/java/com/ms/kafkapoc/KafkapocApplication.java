package com.ms.kafkapoc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;


@SpringBootApplication
@EnableKafka
public class KafkapocApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkapocApplication.class, args);
	}

}
