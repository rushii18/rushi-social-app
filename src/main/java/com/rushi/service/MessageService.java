package com.rushi.service;

import java.util.List;

import com.rushi.models.Chat;
import com.rushi.models.Message;
import com.rushi.models.User;

public interface MessageService {

	public Message CreateMassage(User user, Integer chatid, Message reqMassage ) throws Exception;

	public List<Message> findchatMassege(Integer chat) throws Exception;

}
