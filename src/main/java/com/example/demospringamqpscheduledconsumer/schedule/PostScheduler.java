package com.example.demospringamqpscheduledconsumer.schedule;

import com.example.demospringamqpscheduledconsumer.config.PostQueueConfig;

import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class PostScheduler {
    private Boolean isConsuming;

	@Autowired
	@Qualifier("consumer")
    SimpleMessageListenerContainer consumer;
    
	@Autowired
    PostQueueConfig postQueueConfig;
    
    public PostScheduler() {
        isConsuming = false;
    }

	@Scheduled(cron = "#{postQueueConfig.getConsumerSchedule()}")
	public synchronized void toggleConsume() {
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
