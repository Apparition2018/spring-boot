package com.ljh.config;

import com.ljh.constant.MQ;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMQConfig
 *
 * @author Arsenal
 * @since 2023/5/12 16:09
 */
@Configuration
public class RabbitMQConfig {

    @Bean(MQ.Queue.Order3.name)
    public Queue orderQueue3() {
        return new Queue(MQ.Queue.Order3.name, BooleanUtils.toBoolean(MQ.Queue.Order3.durable));
    }

    @Bean(MQ.Exchange.Order3.name)
    public Exchange orderExchange3() {
        return ExchangeBuilder.topicExchange(MQ.Exchange.Order3.name).build();
    }

    @Bean
    public Binding binding3(@Qualifier(MQ.Queue.Order3.name) Queue queue,
                            @Qualifier(MQ.Exchange.Order3.name) Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(MQ.Bindings.KEY).noargs();
    }
}
