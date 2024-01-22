package com.rushi.serviceimplementation;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rushi.models.Chat;
import com.rushi.models.Massage;
import com.rushi.models.User;
import com.rushi.repository.ChatRepository;
import com.rushi.repository.MassageRepository;
import com.rushi.service.ChatService;
import com.rushi.service.MassageService;
import com.rushi.service.UserService;

@Service
public class MassageServiceImplementation implements MassageService {
	@Autowired
	private MassageRepository massageRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private ChatService chatService;

	@Autowired
	private ChatRepository chatRepository;

	@Override
	public Massage CreateMassage(User user, Integer chatid, Massage reqMassage) throws Exception {

		Chat chat = chatService.findChatByid(chatid);

		Massage massage = new Massage();
		massage.setChat(chat);
		massage.setContent(reqMassage.getContent());
		massage.setImage(reqMassage.getImage());
		massage.setUser(user);
		massage.setTime(LocalDateTime.now());

		chat.getMassage().add(massage);
		chatRepository.save(chat);

		return massageRepository.save(massage);

	}

	@Override
	public List<Massage> findchatMassege(Integer chatid) throws Exception {

		Chat chatcreate = chatService.findChatByid(chatid);

		List<Massage> list = massageRepository.findBychatId(chatid);

		return list;
	}

}
