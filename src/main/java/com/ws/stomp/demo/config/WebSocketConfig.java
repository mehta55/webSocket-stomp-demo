package com.ws.stomp.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {

		registry.addEndpoint("/mywebsockets").setAllowedOrigins("*").withSockJS();
		registry.addEndpoint("/mywebsockets").setAllowedOrigins("*");
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
//		config.enableSimpleBroker("/topic/", "/user/queue/");
//		config.setUserDestinationPrefix("/user");

		config.enableStompBrokerRelay("/topic")
			.setRelayHost("localhost")
			.setRelayPort(61613).setClientLogin("guest")
			.setClientPasscode("guest");
		config.setApplicationDestinationPrefixes("/app");
	}

}
