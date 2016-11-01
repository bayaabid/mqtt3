package com.home.metier;

import org.apache.log4j.Logger;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;

@MessageEndpoint
public class ServiceTest {

	
	@ServiceActivator(inputChannel = "testChannel")
    public void handler(Message<String> message) {
                 System.out.println("!!!!!!!!!!!!!!!!!!!88888" + message.getPayload());
                 
       
    }

}
