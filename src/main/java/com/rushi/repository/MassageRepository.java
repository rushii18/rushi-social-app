package com.rushi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rushi.models.Chat;
import com.rushi.models.Massage;

public interface MassageRepository extends JpaRepository<Massage, Integer> {

	
	public List<Massage> findBychatId(Integer chatid);

}
