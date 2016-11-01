package com.home.metier;

import rx.Observable;

import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.Router;
import org.springframework.messaging.Message;
@MessageEndpoint
public class RouterService {
	
	
	@Router(inputChannel = "outputChannel")
	public String route(Message<String> message) {
		
		if(message.getHeaders().get("mqtt_topic").equals("test")){
			System.out.println(message.getHeaders().get("mqtt_topic"));
			
	        return "testChannel";
	        
	        }
			else if (message.getHeaders().get("mqtt_topic").equals("test1")){
				
				return "test1Channel";
			}
		
		return null;
	}

	
	
}
