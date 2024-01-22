package com.rushi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rushi.models.Post;
import com.rushi.models.Reels;

public interface ReelsRepository extends JpaRepository<Reels, Integer> {

	List<Reels> findByUserId(Integer userid);
}
