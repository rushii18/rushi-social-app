package com.rushi.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.rushi.models.Message;
import com.rushi.models.User;
import com.rushi.service.MessageService;
import com.rushi.service.UserService;

@RestController
public class MessageController {

	@Autowired
	private MessageService messageService;

	@Autowired
	private UserService userService;

	@PostMapping(value = "/api/massage/chat/{chatid}", consumes = "application/json", produces = "application/json")
	public Message CreateMassage(@RequestHeader("Authorization") String jwt, @PathVariable Integer chatid,
			@RequestBody Message message) throws Exception {

		User user = userService.findUserfromJwt(jwt);

		Message MassageCreate = messageService.CreateMassage(user, chatid, message);

		return MassageCreate;
	}

	@GetMapping("/api/getallmassage/{chatid}")
	public List<Message> getallMassage(@RequestHeader("Authorization") String jwt, @PathVariable Integer chatid)
			throws Exception {
		User user = userService.findUserfromJwt(jwt);

		List<Message> GetAllMassage = messageService.findchatMassege(chatid);

		return GetAllMassage;

	}

}
