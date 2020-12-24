package com.example.demospringamqpscheduledconsumer.business;

import java.util.UUID;

import com.example.demospringamqpscheduledconsumer.config.PostQueueConfig;
import com.example.demospringamqpscheduledconsumer.model.Post;
import com.example.demospringamqpscheduledconsumer.util.JsonConverter;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    PostQueueConfig postQueueConfig;

    public Post create(String content) throws RuntimeException {
        try {
            Post post = new Post(UUID.randomUUID().toString(), content);
            rabbitTemplate.convertAndSend(postQueueConfig.getExchange(), postQueueConfig.getRoutingKey(), JsonConverter.toString(post));

            return post;
        } catch (Exception e) {
            throw new RuntimeException("Error creating post. Message: " + e.getMessage(), e);
        }
    }
}
