package com.rushi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rushi.authservice.AuthService;
import com.rushi.config.JwtProvider;
import com.rushi.models.User;
import com.rushi.repository.UserRepository;
import com.rushi.request.LoginRequest;
import com.rushi.respones.Authrespons;
import com.rushi.service.UserService;
import com.rushi.serviceimplementation.CustomUserDetailService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	private UserService userService;
	@Autowired
	private CustomUserDetailService customUserDetailService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AuthService authService;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	@PostMapping("/signup")
	public Authrespons CreateUser(@RequestBody User user) throws Exception {

		  Authrespons auth = authService.CreateUser(user);

		return auth;
	}

	@PostMapping("/signin")
	public Authrespons Signin(@RequestBody LoginRequest loginrequest) {

	//	Authentication authentication = authenticate(loginrequest.getEmail(), loginrequest.getPassword());
		
		Authentication authentication = authService.authenticate(loginrequest.getEmail(), loginrequest.getPassword());
		
		

		String token = JwtProvider.generateToken(authentication);

		Authrespons authrespons = new Authrespons(token, "login Sussess");

		return authrespons;
 
	}

//	private Authentication authenticate(String email, String password) {
//
//		UserDetails userDetails = customUserDetailService.loadUserByUsername(email);
//		if (userDetails == null) {
//			throw new BadCredentialsException("invalid username");
//
//		}
//
//		if (!passwordEncoder.matches(password, userDetails.getPassword())) {
//
//			throw new BadCredentialsException("invalid password");
//
//		}
//
//		return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//	}

}
