package com.rushi.respones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import com.rushi.models.Message;

public class RealTimeChat {

	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;

	@MessageMapping("/chat/{groupid}")
	public Message sendToUser(@Payload Message message, @DestinationVariable String groupid) {

		simpMessagingTemplate.convertAndSendToUser(groupid, "/private", message);

		return message;
	}

}
