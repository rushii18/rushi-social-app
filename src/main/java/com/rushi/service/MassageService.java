package com.rushi.service;

import java.util.List;

import com.rushi.models.Chat;
import com.rushi.models.Massage;
import com.rushi.models.User;

public interface MassageService {

	public Massage CreateMassage(User user, Integer chatid, Massage reqMassage) throws Exception;

	public List<Massage> findchatMassege(Integer chat) throws Exception;

}
