package com.home.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.home.metier.TestGateway;
import com.home.metier.TestGateway2;




@RestController
public class Controller {
	

	
   @Autowired
   TestGateway gateway;
   
   @Autowired
   TestGateway2 gateway2;
	@RequestMapping("/light")
	public String light(){
		//gateway1.sendToMqtt("Patron");
		//gateway.sendToMqtt("king");
		//ser.send();
		gateway.sendToMqtt("gateway");
		gateway.sendToMqtt("tttttttttt");
		gateway2.sendToMqtt("kais");
		gateway2.sendToMqtt("chaabane");
	//System.out.println("*******************");
		return "envoyer";
	}

	
}
