package com.rushi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rushi.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {


	
	@Query("select u from User u where u.FirstName LIKE %:query% OR u.LastName LIKE %:query% OR u.email LIKE %:query%")
	public List<User> searchByUser(@Param("query") String query);

	 public User findByEmail(String email);
}
