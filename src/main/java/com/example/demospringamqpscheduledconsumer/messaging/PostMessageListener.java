package com.example.demospringamqpscheduledconsumer.messaging;

import com.rabbitmq.client.Channel;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;

public class PostMessageListener implements ChannelAwareMessageListener {

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        try {
            // simulate workload...
            Thread.sleep(1000L);
            System.out.println(String.format("Message consumed: %s, from thread %d", new String(message.getBody()),
                    Thread.currentThread().getId()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

}
