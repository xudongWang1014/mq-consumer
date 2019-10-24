package com.xxddww.spring.mq.consumer.listener;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;


@Component
public class ConsumerListener {

    @RabbitListener(queues = "topic.eamil.queue1")
    @RabbitHandler
    public void process(String content, Channel channel, Message message) throws IOException {
        System.out.println("ConsumerListener收到  : " + content + "收到时间" + new Date());
        try {
            //告诉服务器收到这条消息 已经被我消费了 可以在队列删掉 这样以后就不会再发了 否则消息服务器以为这条消息没处理掉 后续还会在发
            //第二个参数是消息的标识，false只确认当前一个消息收到，true确认所有consumer获得的消息
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            System.out.println("receiver success");
        } catch (IOException e) {
            e.printStackTrace();
            //这里如果捕获了异常，则代表消息正常消费，不会重试
            //如果没有重试机制，可以选择重回队列处理，或者发到异常队列
           // channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            System.out.println("receiver fail");
        }
    }

    @RabbitListener(queues = "topic.sms.queue1")
    @RabbitHandler
    public void process1(String content, Channel channel, Message message) throws Exception {
        System.out.println("【topic.sms.queue1消费者】content : " + content + "，exchange：" + message.getMessageProperties().getReceivedExchange()
                           + "，routeKey：" + message.getMessageProperties().getReceivedRoutingKey());
            //告诉服务器收到这条消息 已经被我消费了 可以在队列删掉 这样以后就不会再发了 否则消息服务器以为这条消息没处理掉 后续还会在发
            //第二个参数是消息的标识，false只确认当前一个消息收到，true确认所有consumer获得的消息
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            System.out.println("receiver success");
    }

    @RabbitListener(queues = "dev-demo")
    @RabbitHandler
    public void process12(String content, Channel channel, Message message) throws Exception {
        System.out.println("【dev-demo消费者】content : " + content + "，exchange：" + message.getMessageProperties().getReceivedExchange()
                           + "，routeKey：" + message.getMessageProperties().getReceivedRoutingKey());
        //告诉服务器收到这条消息 已经被我消费了 可以在队列删掉 这样以后就不会再发了 否则消息服务器以为这条消息没处理掉 后续还会在发
        //第二个参数是消息的标识，false只确认当前一个消息收到，true确认所有consumer获得的消息
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        System.out.println("receiver success");
    }
}