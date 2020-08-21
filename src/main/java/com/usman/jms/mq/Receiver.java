package com.usman.jms.mq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

    @JmsListener(destination = "jms-queue")
    public void receiveMessage(String message) {
        System.out.println("Message received: " + message);
    }
}
