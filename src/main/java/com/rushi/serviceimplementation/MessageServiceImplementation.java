package com.rushi.serviceimplementation;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rushi.models.Chat;
import com.rushi.models.Message;
import com.rushi.models.User;
import com.rushi.repository.ChatRepository;
import com.rushi.repository.MessageRepository;
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

	@Override
	public Message CreateMassage(User user, Integer chatid, Message reqMessage) throws Exception {

		Chat chat = chatService.findChatByid(chatid);

		Message message = new Message();
		message.setChat(chat);
		message.setContent(reqMessage.getContent());
		message.setImage(reqMessage.getImage());
		message.setUser(user);
		message.setTime(LocalDateTime.now());

		chat.getMessage().add(message);
		chatRepository.save(chat);

		return messageRepository.save(message);

	}

	@Override
	public List<Message> findchatMassege(Integer chatid) throws Exception {

		Chat chatcreate = chatService.findChatByid(chatid);

		List<Message> list = messageRepository.findBychatId(chatid);

		return list;
	}

}
