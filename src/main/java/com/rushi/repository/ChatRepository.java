package com.rushi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.rushi.models.Chat;

public interface ChatRepository extends JpaRepository<Chat, Integer> {
	
	public List<Chat> findByUserId(Integer userid);
	
//	public Chat findChatByUserId(@Param ("user"));

}
