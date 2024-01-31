package com.rushi.serviceimplementation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.rushi.models.Chat;
import com.rushi.models.Message;
import com.rushi.models.User;
import com.rushi.repository.ChatRepository;
import com.rushi.repository.MessageRepository;
import com.rushi.request.KafkaConstants;
import com.rushi.service.ChatService;
import com.rushi.service.MessageService;
import com.rushi.service.UserService;

@Service
public class MessageServiceImplementation implements MessageService {
	@Autowired
	private MessageRepository messageRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private ChatService chatService;

	@Autowired
	private ChatRepository chatRepository;

	@Autowired
	private KafkaTemplate<String, Message> kafkaTemplate;

	@Override
	public Message CreateMassage(User user, Integer chatid, Message reqMessage) throws Exception {

		Chat chat = chatService.findChatByid(chatid);

		Message message = new Message();
		message.setChat(chat);

		message.setContent(reqMessage.getContent());
		message.setImage(reqMessage.getImage());

		String name = (user.getFirstName());
		message.setUsername(name);
		message.setTime(LocalDateTime.now());

		chat.getMessage().add(message);
		chatRepository.save(chat);

		try {
			kafkaTemplate.send("message-topic", message).get();
		} catch (InterruptedException | ExecutionException e) {

			e.printStackTrace();
		}
		return messageRepository.save(message);

	}

	@Override
	public List<Message> findchatMassege(Integer chatid) throws Exception {

		Chat chatcreate = chatService.findChatByid(chatid);

		List<Message> list = messageRepository.findBychatId(chatid);

		return list;
	}

}
