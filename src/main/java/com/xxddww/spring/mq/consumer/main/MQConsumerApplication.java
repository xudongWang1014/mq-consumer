package com.xxddww.spring.mq.consumer.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.xxddww.spring"})
public class MQConsumerApplication {

    private final static Logger logger = LoggerFactory.getLogger(MQConsumerApplication.class);

    public static void main(String[] args) {

        SpringApplication.run(MQConsumerApplication.class, args);

        logger.info("【MQConsumerApplication】启动完成");

    }

}
