package com.home.metier;

import org.springframework.integration.annotation.MessagingGateway;


@MessagingGateway(defaultRequestChannel = "mqttOutboundChanneltest")
public interface TestGateway2 {
	

    void sendToMqtt(String message);


}
