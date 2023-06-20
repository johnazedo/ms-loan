package br.ufrn.imd.microservices.msloan.core.log;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class LogSender {

    @Value("${client.log.queue}")
    private String queue;
    private final RabbitTemplate rabbitTemplate;

    public LogSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(Log log) {
        rabbitTemplate.convertAndSend(queue, log);
    }
}
