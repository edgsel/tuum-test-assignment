package com.edgsel.tuumtestassignment.client;

import com.edgsel.tuumtestassignment.mybatis.Account;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RabbitClient {

    private final AmqpTemplate rabbitTemplate;

    @Value("${tuum.rabbitmq.exchange}")
    private String exchange;

    @Value("${tuum.rabbitmq.routingkey}")
    private String routingKey;

    public RabbitClient(AmqpTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(Account account, String action) {
        rabbitTemplate.convertAndSend(exchange, routingKey, account);
        log.info("Sent account with ID {} to RabbitMQ with event '{}'", account.getId(), action);
    }
}
