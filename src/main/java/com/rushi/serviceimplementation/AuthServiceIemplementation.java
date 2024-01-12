package com.rushi.serviceimplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rushi.authservice.AuthService;
import com.rushi.config.JwtProvider;
import com.rushi.models.User;
import com.rushi.repository.UserRepository;
import com.rushi.respones.Authrespons;
import com.rushi.service.UserService;

@Service
public class AuthServiceIemplementation implements AuthService {

	@Autowired
	private UserService userService;
	@Autowired
	private CustomUserDetailService customUserDetailService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Authrespons CreateUser(User user) throws Exception {

		User isuserexist = userRepository.findByEmail(user.getEmail());
		if (isuserexist != null) {
			throw new Exception(" email is exist used by another accout");
		}

		User usercreate = new User();

		usercreate.setFirstName(user.getFirstName());
		usercreate.setLastName(user.getLastName());
		usercreate.setPassword(passwordEncoder.encode(user.getPassword()));

		usercreate.setEmail(user.getEmail());

		User saveuser = userRepository.save(usercreate);

		Authentication authentication = new UsernamePasswordAuthenticationToken(saveuser.getEmail(),
				saveuser.getPassword());

		String token = JwtProvider.generateToken(authentication);

		Authrespons authrespons = new Authrespons(token, "Register Sussess");

		return authrespons;

	}

	@Override
	public Authentication authenticate(String email, String password) {

		UserDetails userDetails = customUserDetailService.loadUserByUsername(email);
		if (userDetails == null) {
			throw new BadCredentialsException("invalid username");

		}

		if (!passwordEncoder.matches(password, userDetails.getPassword())) {

			throw new BadCredentialsException("invalid password");

		}

		return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	}

}
