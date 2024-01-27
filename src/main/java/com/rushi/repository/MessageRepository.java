package com.rushi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rushi.models.Chat;
import com.rushi.models.Message;

public interface MessageRepository extends JpaRepository<Message, Integer> {

	
	public List<Message> findBychatId(Integer chatid);

}
