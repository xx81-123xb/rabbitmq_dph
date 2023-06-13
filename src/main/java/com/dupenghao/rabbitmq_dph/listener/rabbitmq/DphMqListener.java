package com.dupenghao.rabbitmq_dph.listener.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by 杜鹏豪 on 2023/6/13.
 */
@Component
@Slf4j
public class DphMqListener {

    @RabbitListener(queues = {"dph.fanout.queue"})
    public void receive(String message){
        log.info("fanout接收到消息："+message);
    }
    @RabbitListener(queues = {"dph.direct.queue"})
    public void receive2(String message){
        log.info("direct接收到消息："+message);
    }

    @RabbitListener(queues = {"dph.topic.queue1"})
    public void receive3(String message){
        log.info("topic1接收到消息："+message);
    }

    @RabbitListener(queues = {"dph.topic.queue2"})
    public void receive4(String message){
        log.info("topic2接收到消息："+message);
    }

}
