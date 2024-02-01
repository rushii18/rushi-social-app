package com.rushi.request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import com.rushi.models.Message;

@Component
public class MessageListener {

	@KafkaListener(topics = "message-topic", groupId = "1")
	public void listenGroupFoo(String message) {
	    System.out.println("Received Message in group foo: " + message);
	}

}
