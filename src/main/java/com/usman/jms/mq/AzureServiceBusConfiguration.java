package com.usman.jms.mq;

import com.microsoft.azure.servicebus.primitives.ConnectionStringBuilder;
import org.amqphub.spring.boot.jms.autoconfigure.AMQP10JMSConnectionFactoryFactory;
import org.amqphub.spring.boot.jms.autoconfigure.AMQP10JMSProperties;
import org.apache.qpid.jms.JmsConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.core.JmsTemplate;

@Profile("dev")
@Configuration
public class AzureServiceBusConfiguration {

    private final ConnectionStringBuilder connectionStringBuilder;

    public AzureServiceBusConfiguration(
            @Value("${spring.jms.servicebus.connection-string}") String connectionString) {
        connectionStringBuilder = new ConnectionStringBuilder(connectionString);
    }

    @Bean
    public JmsTemplate jmsTemplate() {
        return new JmsTemplate(jmsConnectionFactory());
    }

    @Bean
    public JmsConnectionFactory jmsConnectionFactory() {
        AMQP10JMSProperties properties = new AMQP10JMSProperties();
        properties.setRemoteUrl(amqpRemoteUrl());
        properties.setUsername(amqpUsername());
        properties.setPassword(amqpPassword());
        return new AMQP10JMSConnectionFactoryFactory(properties)
                .createConnectionFactory(JmsConnectionFactory.class);
    }

    private String amqpRemoteUrl() {
        return "amqps://" + connectionStringBuilder.getEndpoint().getHost();
    }

    private String amqpUsername() {
        return connectionStringBuilder.getSasKeyName();
    }

    private String amqpPassword() {
        return connectionStringBuilder.getSasKey();
    }
}