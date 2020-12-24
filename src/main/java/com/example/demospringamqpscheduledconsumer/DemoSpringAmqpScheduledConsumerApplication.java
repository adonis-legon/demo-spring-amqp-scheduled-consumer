package com.example.demospringamqpscheduledconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DemoSpringAmqpScheduledConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoSpringAmqpScheduledConsumerApplication.class, args);
	}

}
