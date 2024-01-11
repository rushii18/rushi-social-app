package com.rushi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rushi.config.JwtProvider;
import com.rushi.models.User;
import com.rushi.repository.UserRepository;
import com.rushi.respones.Authrespons;
import com.rushi.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping("/signup ")
	public Authrespons CreateUser(@RequestBody User user) throws Exception {
   
		User isuserexist = userRepository.findByEmail(user.getEmail());
		if(isuserexist!=null) {
			throw new Exception(" email is exist used by another accout");
		}
		
		User usercreate = new User();

		usercreate.setFirstName(user.getFirstName());
		usercreate.setLastName(user.getLastName());
		usercreate.setPassword(passwordEncoder.encode(user.getPassword()));
		usercreate.setContactNo(user.getContactNo());
		usercreate.setEmail(user.getEmail());

		User saveuser = userRepository.save(usercreate);
		
		Authentication authentication = new UsernamePasswordAuthenticationToken(saveuser.getEmail(), saveuser.getPassword() );
		
		String token = JwtProvider.generateToken(authentication);

		Authrespons authrespons = new Authrespons(token , "Register Sussess");
		
		return authrespons;
	}

}
