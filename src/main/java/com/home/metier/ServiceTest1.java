package com.home.metier;

import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;

@MessageEndpoint
public class ServiceTest1 {
	
		@ServiceActivator(inputChannel = "test1Channel")
    public void handler(Message<String> message) {
                 System.out.println("!!!!!!!!!!!!!!!!!!!" + message.getPayload());
          
       
    }

}
