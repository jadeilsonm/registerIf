package br.edu.ifpe.register.register.service.rabbit;

import jakarta.annotation.PostConstruct;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConnectionTest {
    private final ConnectionFactory connectionFactory;

    public RabbitMQConnectionTest(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @PostConstruct
    public void testConnection() {
        try {
            connectionFactory.createConnection().close();
            System.out.println("✅ Conexão com RabbitMQ estabelecida com sucesso!");
        } catch (Exception e) {
            System.err.println("❌ Falha ao conectar com RabbitMQ: " + e.getMessage());
            throw new IllegalStateException("Não foi possível conectar ao RabbitMQ", e);
        }
    }
}
