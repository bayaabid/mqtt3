package com.home;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

@SpringBootApplication
@IntegrationComponentScan
public class MqttBockerHomeApplication {

	public static void main(String[] args) {
		SpringApplication.run(MqttBockerHomeApplication.class, args);
		/*ConfigurableApplicationContext context =
        		new SpringApplicationBuilder(MqttBockerHomeApplication.class)
        				.web(false)
        				.run(args);
		TestGateway gateway = context.getBean(TestGateway.class);
        gateway.sendToMqtt("foo");*/
	}

	@Bean
	public MessageChannel outputChannel() {
		return new DirectChannel();
	}

	@Bean
	public MessageChannel testChannel() {
		return new DirectChannel();
	}

	@Bean
	public MessageChannel test1Channel() {
		return new DirectChannel();
	}
	
	
	@Bean
	public MqttPahoClientFactory mqttClientFactory() {
		DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
		factory.setServerURIs("tcp://localhost:1883");

		return factory;
	}

	@Bean
	public MessageProducer inbound() {

		MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter("java",
				mqttClientFactory(), "test", "test1");
		adapter.setCompletionTimeout(10000);
		adapter.setConverter(new DefaultPahoMessageConverter());
		adapter.setQos(1);
		adapter.setOutputChannel(outputChannel());

		return adapter;
	}
	
	/**
	 * Envoie vers MQTT
	 * 
	 * 
	 * 
	 * 
	 * 
	 * */
	
	
	@Bean
    @ServiceActivator(inputChannel = "mqttOutboundChanneltest")
    public MessageHandler mqttOutbound() {
        MqttPahoMessageHandler messageHandler =
                       new MqttPahoMessageHandler("java", mqttClientFactory());
        messageHandler.setAsync(true);
        messageHandler.setDefaultTopic("test");
        return messageHandler;
    }

    @Bean
    public MessageChannel mqttOutboundChanneltest() {
        return new PublishSubscribeChannel();
    }

    /*@MessagingGateway(defaultRequestChannel = "mqttOutboundChanneltest")
    public interface TestGateway {

        void sendToMqtt(String data);

    }*/

	
	/*@Bean
	public MessageChannel mqttOutboundChanneltest(){
		return new PublishSubscribeChannel();
	}
	
	@Bean
    @ServiceActivator(inputChannel = "mqttOutboundChanneltest")
    public MessageHandler mqttOut() {
      MqttPahoMessageHandler messageHandler =
              new MqttPahoMessageHandler("java", mqttClientFactory());
      messageHandler.setAsync(true);
      messageHandler.setDefaultTopic("test");
      messageHandler.setDefaultQos(2);
      return messageHandler;
    }
	
	@MessagingGateway(defaultRequestChannel = "mqttOutboundChanneltest")
	public interface TestGateway {
		

	    void sendToMqtt(String message);


	}*/


}
