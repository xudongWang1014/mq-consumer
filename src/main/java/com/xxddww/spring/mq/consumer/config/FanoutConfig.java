package com.xxddww.spring.mq.consumer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * 广播交换机配置
 * <pre>
 *     绑定了这个交换机的所有队列都收到这个消息
 * </pre>
 */
@Component
public class FanoutConfig {

    /**
     * 邮件队列
     */
    private String FANOUT_EMAIL_QUEUE = "fanout.eamil.queue";
    /**
     * 短信队列
     */
    private String FANOUT_SMS_QUEUE = "fanout.sms.queue";
    /**
     * 短信队列
     */
    private String EXCHANGE_NAME = "fanout.exchange";

    /**
     * 定义交换机
     *
     * @return
     */
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(EXCHANGE_NAME, true, false);
    }
    /**
     * 邮件队列
     *
     * @return
     */
    @Bean
    public Queue fanoutEmailQueue() {
        return new Queue(FANOUT_EMAIL_QUEUE, true, false, false);
    }

    /**
     * 短信队列
     *
     * @return
     */
    @Bean
    public Queue fanoutSMSQueue() {
        return new Queue(FANOUT_SMS_QUEUE, true, false, false);
    }

    /**
     * 邮件队列和交换机绑定+
     */
    @Bean
    Binding bindingExchangeEamil() {
        return BindingBuilder.bind(fanoutEmailQueue()).to(fanoutExchange());
    }

    /**
     * 短信队列和交换机绑定
     */
    @Bean
    Binding bindingExchangeSMS() {
        return BindingBuilder.bind(fanoutSMSQueue()).to(fanoutExchange());
    }


}
