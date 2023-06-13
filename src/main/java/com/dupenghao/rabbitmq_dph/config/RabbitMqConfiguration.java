package com.dupenghao.rabbitmq_dph.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by 杜鹏豪 on 2023/6/13.
 */
@Configuration
public class RabbitMqConfiguration {

    @Bean
    public FanoutExchange fanoutExchange() {
        FanoutExchange fanoutExchange = new FanoutExchange("dph.fanout.exchange");
        return fanoutExchange;
    }

    @Bean
    public DirectExchange directExchange() {
        DirectExchange directExchange = new DirectExchange("dph.direct.exchange");
        return directExchange;
    }

    @Bean
    public TopicExchange topicExchange() {
        TopicExchange topicExchange = new TopicExchange("dph.topic.exchange");
        return topicExchange;
    }

    @Bean
    public Queue fanoutQueue() {
        Queue queue = new Queue("dph.fanout.queue");
        return queue;
    }

    @Bean
    public Queue directQueue() {
        Queue queue = new Queue("dph.direct.queue");
        return queue;
    }

    @Bean
    public Queue topicQueue1() {
        Queue queue = new Queue("dph.topic.queue1");
        return queue;
    }

    @Bean
    public Queue topicQueue2() {
        Queue queue = new Queue("dph.topic.queue2");
        return queue;
    }

    @Bean
    public Binding fanoutBinding() {
        Binding binding = BindingBuilder.bind(fanoutQueue()).to(fanoutExchange());
        return binding;
    }

    @Bean
    public Binding directBinding() {
        Binding binding = BindingBuilder.bind(directQueue()).to(directExchange()).with("dph.direct.routingKey");
        return binding;
    }

    @Bean
    public Binding topicBinding1() {
        Binding binding = BindingBuilder.bind(topicQueue1()).to(topicExchange()).with("dph.#");
        return binding;
    }

    @Bean
    public Binding topicBinding2() {
        Binding binding = BindingBuilder.bind(topicQueue2()).to(topicExchange()).with("dph.*");
        return binding;
    }

}
