package com.rushi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestBody;

import com.rushi.models.Chat;
import com.rushi.models.User;

public interface ChatRepository extends JpaRepository<Chat, Integer> {

	public List<Chat> findByUserId(Integer userid);

	@Query("select c from Chat c where :user Member of c.user And :reqUser Member of c.user")
	public Chat findChatByUserId(@Param("user") User user, @Param("reqUser") User reqUser);

}
