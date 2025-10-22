package br.edu.ifpe.register.register.configurations;


import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE_CREATED = "ifpe-register-cr";
    public static final String EXCHANGE_UPDATE = "ifpe-register-up";

    public static final String QUEUE_REGISTER_CREATED = "ifpe-register-created";
    public static final String QUEUE_REGISTER_UPDATE = "ifpe-register-update";
    public static final String QUEUE_COURSE_CREATED = "ifpe-course-created";
    public static final String QUEUE_COURSE_UPDATE = "ifpe-course-update";
    public static final String QUEUE_DISCIPLINE_CREATED = "ifpe-discipline-created";
    public static final String QUEUE_DISCIPLINE_UPDATE = "ifpe-discipline-update";
    public static final String QUEUE_SIGMA_REGISTER_CREATED = "sigma-register-created";
    public static final String QUEUE_SIGMA_REGISTER_UPDATE = "sigma-register-update";

    @Bean
    public AmqpAdmin amqpAdmin(org.springframework.amqp.rabbit.connection.ConnectionFactory connectionFactory) {
        return new org.springframework.amqp.rabbit.core.RabbitAdmin(connectionFactory);
    }

    @Bean
    public FanoutExchange exchangeCreated() {
        return new FanoutExchange(EXCHANGE_CREATED);
    }

    @Bean
    public FanoutExchange exchangeUpdate() {
        return new FanoutExchange(EXCHANGE_UPDATE);
    }

    @Bean
    public Queue userCreatedQueue() {
        return QueueBuilder.durable(QUEUE_REGISTER_CREATED).build();
    }

    @Bean
    public Queue userUpdateQueue() {
        return QueueBuilder.durable(QUEUE_REGISTER_UPDATE).build();
    }

    @Bean
    public Queue userSigmaCreatedQueue() {
        return QueueBuilder.durable(QUEUE_SIGMA_REGISTER_CREATED).build();
    }

    @Bean
    public Queue userSigmaUpdateQueue() {
        return QueueBuilder.durable(QUEUE_SIGMA_REGISTER_UPDATE).build();
    }

    @Bean
    public Queue courseCreatedQueue() {
        return QueueBuilder.durable(QUEUE_COURSE_CREATED).build();
    }

    @Bean
    public Queue courseUpdateQueue() {
        return QueueBuilder.durable(QUEUE_COURSE_UPDATE).build();
    }

    @Bean
    public Queue disciplineCreatedQueue() {
        return QueueBuilder.durable(QUEUE_DISCIPLINE_CREATED).build();
    }

    @Bean
    public Queue disciplineUpdateQueue() {
        return QueueBuilder.durable(QUEUE_DISCIPLINE_UPDATE).build();
    }

    @Bean
    public Binding bindingUserSigmaCreated(Queue userSigmaCreatedQueue, FanoutExchange exchangeCreated) {
        return BindingBuilder.bind(userSigmaCreatedQueue).to(exchangeCreated);
    }

    @Bean
    public Binding bindingUserSigmaUpdate(Queue userSigmaUpdateQueue, FanoutExchange exchangeUpdate) {
        return BindingBuilder.bind(userSigmaUpdateQueue).to(exchangeUpdate);
    }

    @Bean
    public Binding bindingUserCreated(Queue userCreatedQueue, FanoutExchange exchangeCreated) {
        return BindingBuilder.bind(userCreatedQueue).to(exchangeCreated);
    }

    @Bean
    public Binding bindingUserUpdate(Queue userUpdateQueue, FanoutExchange exchangeUpdate) {
        return BindingBuilder.bind(userUpdateQueue).to(exchangeUpdate);
    }


    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}
