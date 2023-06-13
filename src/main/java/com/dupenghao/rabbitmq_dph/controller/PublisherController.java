package com.dupenghao.rabbitmq_dph.controller;

import com.dupenghao.conmmon.entity.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by 杜鹏豪 on 2023/6/13.
 */
@RestController
@RequestMapping("/api/Publisher")
@Api(tags = "消息发布者")
public class PublisherController implements ApplicationContextAware {

    private ApplicationContext context;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/publish")
    @ApiOperation("发布消息")
    public Result publish(SendParam sendParam){
        String message = sendParam.getMessage();
        String routingKey = sendParam.getRoutingKey();
        String exchange = sendParam.getExchange();
        rabbitTemplate.convertAndSend(exchange,routingKey,message);
//        rabbitTemplate.convertAndSend(exchange,"dph.direct.routingKey","默认消息");
        return Result.success(null);
    }

    @GetMapping("/showQueues")
    @ApiOperation("查看队列")
    public Result showQueues(){
        Map<String, Queue> queues = context.getBeansOfType(Queue.class);
        return Result.success(queues);
    }

    @GetMapping("/showExchanges")
    @ApiOperation("查看交换机")
    public Result showExchanges(){
        Map<String, Exchange> exchanges = context.getBeansOfType(Exchange.class);
        return Result.success(exchanges);
    }

    @GetMapping("/showBindings")
    @ApiOperation("查看绑定关系")
    public Result showBindings(){
        Map<String, Binding> bindings = context.getBeansOfType(Binding.class);
        return Result.success(bindings);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    @Data
    static class SendParam{
        private String message;
        private String routingKey;
        private String exchange;
    }

}
