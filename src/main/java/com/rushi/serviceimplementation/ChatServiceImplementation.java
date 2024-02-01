package com.rushi.serviceimplementation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import com.rushi.models.Chat;
import com.rushi.models.User;
import com.rushi.repository.ChatRepository;
import com.rushi.service.ChatService;
import com.rushi.service.UserService;

@Service
public class ChatServiceImplementation implements ChatService {

	@Autowired
	private ChatRepository chatRepository;

	@Autowired
	private UserService userService;

	@Override
	public Chat createchat(User user, User reqUser) {

		Chat isexist = chatRepository.findChatByUserId(user, reqUser);

		if (isexist != null) {
			return isexist;
		}
		Chat chat = new Chat();
		chat.getUser().add(user);
		chat.getUser().add(reqUser);

		return chatRepository.save(chat);
	}

	@Override
	public Chat findChatByid(Integer chatid) throws Exception {
		Optional<Chat> chat = chatRepository.findById(chatid);
		
		if (chat.isPresent()) {
			return chat.get();
		}
		throw new Exception("chat not found with id " + chatid);

	}

	@Override
	public List<Chat> findUserChat(Integer userid) {

		return chatRepository.findByUserId(userid);

	}

	@Override
	public String deletechat(Integer chatid) {
		
		 chatRepository.deleteById(chatid);;
		
		return "chatid is delete "+ chatid ;
	}

}
