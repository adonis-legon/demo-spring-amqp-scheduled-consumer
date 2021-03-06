package com.example.demospringamqpscheduledconsumer.config;

import com.example.demospringamqpscheduledconsumer.messaging.PostMessageListener;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "app.post.queue")
public class PostQueueConfig {
    private String exchange;
    private String routingKey;
    private String queue;
    private String consumerSchedule;
    private String consumerConcurrency;
    private String consumerPrefetchCount;

    @Bean
    Queue queue() {
      return new Queue(queue, true);
    }
  
    @Bean
    TopicExchange exchange() {
      return new TopicExchange(exchange);
    }
  
    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
      return BindingBuilder.bind(queue).to(exchange).with(routingKey);
    }

    @Bean("consumer")
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory) {
      SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
      
      container.setQueueNames(queue);
      container.setAutoStartup(false);
      container.setConcurrency(consumerConcurrency);
      container.setPrefetchCount(Integer.parseInt(consumerPrefetchCount));
      container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
      container.setMessageListener(new PostMessageListener());

      return container;
    }
}
