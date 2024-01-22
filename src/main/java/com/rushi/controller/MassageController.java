package com.rushi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.rushi.models.Massage;
import com.rushi.models.User;
import com.rushi.service.MassageService;
import com.rushi.service.UserService;

@RestController
public class MassageController {

	@Autowired
	private MassageService massageService;

	@Autowired
	private UserService userService;

	@PostMapping("/api/massage/chat/{chatid}")
	public Massage CreateMassage(@RequestHeader("Authorization") String jwt, @PathVariable Integer chatid,
			@RequestBody Massage massage) throws Exception {

		User user = userService.findUserfromJwt(jwt);

		Massage massageCreate = massageService.CreateMassage(user, chatid, massage);

		return massageCreate;
	}

	@GetMapping("/api/getallmassage/{chatid}")
	public List<Massage> getallMassage(@RequestHeader("Authorization") String jwt, @PathVariable Integer chatid)
			throws Exception {
		User user = userService.findUserfromJwt(jwt);

		List<Massage> getallmassage = massageService.findchatMassege(chatid);

		return getallmassage;

	}

}
