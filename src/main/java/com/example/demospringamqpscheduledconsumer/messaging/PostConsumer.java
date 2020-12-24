package com.example.demospringamqpscheduledconsumer.messaging;

import com.example.demospringamqpscheduledconsumer.config.PostQueueConfig;

import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class PostConsumer {
	private Boolean isConsuming;

	@Autowired
	@Qualifier("consumer")
	SimpleMessageListenerContainer consumer;

	@Autowired
	PostQueueConfig postQueueConfig;

	public PostConsumer() {
		isConsuming = false;
	}

	public void handleMessage(String message) {
		System.out.println("Message consumed: " + message);
	}

	@Scheduled(cron = "#{postQueueConfig.getConsumerSchedule()}")
	public void toggleConsume() {
		isConsuming = !isConsuming;

		if (isConsuming) {
			consumer.start();
			System.out.println("Started consumer...");
		} else{
			consumer.stop();
			System.out.println("Stopped consumer");
		}
	}
}