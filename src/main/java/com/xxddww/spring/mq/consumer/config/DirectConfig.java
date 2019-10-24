package com.xxddww.spring.mq.consumer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * 直连模式
 * <Pre>
 * 消息路由到那些binding key与routing key完全匹配的Queue中
 * </Pre>
 */
@Component
public class DirectConfig {

    public static final String DIRECT_EXCHANGE = "direct.exchange";

    public static final String DIRECT_QUEUE1 = "direct.queue1";
    public static final String DIRECT_QUEUE2 = "direct.queue2";

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(DIRECT_EXCHANGE, true, false);
    }

    /**
     * direct模式
     * 消息中的路由键（routing key）如果和 Binding 中的 binding key 一致， 交换器就将消息发到对应的队列中。路由键与队列名完全匹配
     */
    @Bean
    public Queue directQueue1() {
        return new Queue(DIRECT_QUEUE1, true, false, false);
    }

    @Bean
    public Binding directBinding1() {
        return BindingBuilder.bind(directQueue1()).to(directExchange()).with("direct.pwl");
    }
    @Bean
    public Queue directQueue2() {
        return new Queue(DIRECT_QUEUE2, true, false, false);
    }

    @Bean
    public Binding directBinding2() {
        return BindingBuilder.bind(directQueue2()).to(directExchange()).with("direct.pw2");
    }
}
