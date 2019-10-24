package com.xxddww.spring.mq.consumer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * 主题交换机配置
 * <pre>
 *     消息路由到binding key与routing key相匹配的Queue中
 *  </pre>
 */
@Component
public class TopicConfig {

    /**
     * 邮件队列
     */
    private String TOPIC_EMAIL_QUEUE = "topic.eamil.queue1";
    /**
     * 短信队列
     */
    private String TOPIC_SMS_QUEUE = "topic.sms.queue1";
    /**
     * 交换机
     */
    private String EXCHANGE_NAME = "topic.exchange";

    /**
     * 定义交换机
     * durable="true" rabbitmq重启的时候不需要创建新的交换机
     * autoDelete:当最后一个绑定到 exchange 上的队列被删除后，exchange 没有绑定的队列了，自动删除该 exchange
     */
    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(EXCHANGE_NAME, true, false);
    }

    /**
     * 邮件队列
     * durable="true" 持久化 rabbitmq重启的时候不需要创建新的队列
     * auto-delete 表示消息队列没有在使用时将被自动删除 默认是false
     * exclusive  表示该消息队列是否只在当前connection生效,默认是false,当前连接被断开的时候会自动消失（清除）无论是否设置了持久化
     */
    @Bean
    public Queue topicEmailQueue() {
        return new Queue(TOPIC_EMAIL_QUEUE, true, false, false);
    }

    /**
     * 短信队列
     *
     * @return
     */
    @Bean
    public Queue topicSMSQueue() {
        return new Queue(TOPIC_SMS_QUEUE, true, false, false);
    }

    /**
     * 邮件队列和交换机绑定      参数名称和定义好的方法名称一致
     * <pre>
     *      符号“#”和符号“*”。#匹配0个或多个单词，*匹配一个单词
     *  </pre>
     */
    @Bean
    Binding bindingExchangeEamil1() {
        return BindingBuilder.bind(topicEmailQueue()).to(topicExchange()).with("topic.*");
    }

    /**
     * 短信队列和交换机绑定
     * <pre>
     *      符号“#”和符号“*”。#匹配0个或多个单词，*匹配一个单词
     *  </pre>
     */
    @Bean
    Binding bindingExchangeSMS1() {
        return BindingBuilder.bind(topicSMSQueue()).to(topicExchange()).with("topic.#");
    }


}
