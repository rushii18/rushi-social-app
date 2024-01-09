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

@Entity
@Table(name = "post")
public class Post {

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Integer id;
	private String caption;
	private String image;
	private String viedo;
	@ManyToOne
	private User user;

	private LocalDateTime createdAT;
	@OneToMany
	private List<User> likeuser = new ArrayList<>();

	public Post() {

	}

	public Post(Integer id, String caption, String image, String viedo, User user, LocalDateTime createdAT,
			List<User> likeuser) {
		super();
		this.id = id;
		this.caption = caption;
		this.image = image;
		this.viedo = viedo;
		this.user = user;
		this.createdAT = createdAT;
		this.likeuser = likeuser;
	}

	public List<User> getLikeuser() {
		return likeuser;
	}

	public void setLikeuser(List<User> likeuser) {
		this.likeuser = likeuser;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getViedo() {
		return viedo;
	}

	public void setViedo(String viedo) {
		this.viedo = viedo;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LocalDateTime getCreatedAT() {
		return createdAT;
	}

	public void setCreatedAT(LocalDateTime createdAT) {
		this.createdAT = createdAT;
	}

}
