package com.rushi.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "post")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Integer id;
	private String caption;
	private String image;
	private String video;
	@ManyToOne
	private User user;

	private LocalDateTime createdAT;
	@OneToMany
	private List<User> likeuser = new ArrayList<>();

	@OneToMany
	private List<Comment> commentUser = new ArrayList<>();

	

}
