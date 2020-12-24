package com.example.demospringamqpscheduledconsumer.config;

import com.example.demospringamqpscheduledconsumer.messaging.PostConsumer;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
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
      container.setMessageListener(new MessageListenerAdapter(new PostConsumer()));

      return container;
    }
}
