package com.rushi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.rushi.models.Chat;
import com.rushi.models.User;
import com.rushi.request.CreateChatRequest;
import com.rushi.service.ChatService;
import com.rushi.service.UserService;

@RestController
public class ChatController {
	@Autowired
	private ChatService chatService;

	@Autowired
	private UserService userService;

	@PostMapping("/api/chat")
	public Chat Createchat(@RequestHeader("Authorization") String jwt, @RequestBody CreateChatRequest req)
			throws Exception {

		User user = userService.findUserfromJwt(jwt);
		User requser = userService.findUserByid(req.getUserid());

		Chat chat = chatService.createchat(user, requser);

		return chat;
	}

	@GetMapping("/api/allchat")
	public List<Chat> getAllchat(@RequestHeader("Authorization") String jwt) {

		User user = userService.findUserfromJwt(jwt);

		List<Chat> chat = chatService.findUserChat(user.getId());

		return chat;
	}
	
	@GetMapping("/api/chatid/{chatid}")
	public Chat findchatById(@PathVariable Integer chatid) throws Exception {
	
		Chat chat = chatService.findChatByid(chatid);
		
		return chat;
	}

}
