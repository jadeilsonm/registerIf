package br.edu.ifpe.register.register;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit
@SpringBootApplication
public class RegisterApplication {
	public static void main(String[] args) {
        SpringApplication.run(RegisterApplication.class, args);
	}
}
