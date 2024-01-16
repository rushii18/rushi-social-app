package com.rushi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rushi.models.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
