package br.edu.ifpe.register.register.service.rabbit;

import br.edu.ifpe.register.register.configurations.RabbitConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;


@Component
public class RegisterSend {

    private final RabbitTemplate rabbitTemplate;

    public RegisterSend(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(Object message) {
        rabbitTemplate.convertAndSend(RabbitConfig.QUEUE_NAME, message);
    }
}
