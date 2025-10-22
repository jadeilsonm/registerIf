package br.edu.ifpe.register.register.service.rabbit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class RabbitSend {

    @Autowired
    private final RabbitTemplate rabbitTemplate;

    public RabbitSend(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(Object message, String exchange, String queue) {
        if (exchange != null && queue != null) {
            rabbitTemplate.convertAndSend(exchange, queue, message);
        } else if (exchange != null) {
            rabbitTemplate.convertAndSend(exchange,null, message);
        } else {
            rabbitTemplate.convertAndSend(queue, message);
        }
    }
}
