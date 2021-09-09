package com.ws.stomp.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class MyController {

	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;

	@MessageMapping("/news")
	@SendTo("/topic/news")
	public String broadcastNews(@Payload String message) {
		System.out.println("message from client: " + message);
		return message;
	}

	@MessageMapping("/fetch")
	public void fetchOffers(@Payload String msg, @Header("sessionId") String sessionId) {
//			@Header("simpSessionId") String sessionId) {

		System.out.println("/fetch hit, session Id: " + sessionId);
		System.out.println("message: " + msg);
		msg = sessionId + " said " + msg;
		simpMessagingTemplate.convertAndSend("/topic/" + sessionId, msg);
	}

}
