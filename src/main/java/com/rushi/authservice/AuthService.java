package com.rushi.authservice;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.rushi.models.User;
import com.rushi.respones.Authrespons;

public interface AuthService {

	public Authrespons CreateUser(User user) throws Exception;

	public Authentication authenticate(String email, String password);

}
