package com.rushi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rushi.models.Post;
import com.rushi.models.Reels;

public interface ReelsRepository extends JpaRepository<Reels, Integer>{

//	public List<Reels> findReelsByUserid(Integer userid);
	
	
	@Query("select r from Post r where r.user.id=:userid")
	List<Post> findReelsByUserid(Integer userid);
}


