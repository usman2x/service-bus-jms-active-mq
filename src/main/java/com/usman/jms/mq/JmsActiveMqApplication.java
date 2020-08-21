package com.usman.jms.mq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.core.JmsTemplate;

import java.time.LocalDateTime;

@SpringBootApplication
public class JmsActiveMqApplication implements CommandLineRunner {

	private final Sender sender;

	public JmsActiveMqApplication(Sender sender) {
		this.sender = sender;
	}

	public static void main(String[] args) {
        SpringApplication.run(JmsActiveMqApplication.class, args);
    }

	@Override
	public void run(String... args) throws Exception {
		sender.sendMessage("jms-queue", "Hello JMS ".concat(LocalDateTime.now().toString()));
	}
}
