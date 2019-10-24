package com.xxddww.spring.mq.consumer.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource({"classpath:mq.properties"} )
public class MQConfig {
}
