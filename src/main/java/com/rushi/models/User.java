package com.rushi.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity// to create table in your database in our case we are using SQL workbench
@Table(name = "users") // to create table name
public class User {

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id // make unique Identifiers to table
	private int id;
	// We can also use @column to make column name
	private String FirstName;
	private String LastName;
	private String email;
	private String Password;
	private String contactNo;
	private String gender;
	private List<Integer> followers = new ArrayList<>();

	private List<Integer> following = new ArrayList<>();

	@ManyToMany
	private List<Post> savepost = new ArrayList<>();

	public User() {

	}

	public User(int id, String firstName, String lastName, String email, String password, String contactNo,
			String gender, List<Integer> followers, List<Integer> following) {
		super();
		this.id = id;
		FirstName = firstName;
		LastName = lastName;
		this.email = email;
		Password = password;
		this.contactNo = contactNo;
		this.gender = gender;
		this.followers = followers;
		this.following = following;
	}

	public List<Post> getSavepost() {
		return savepost;
	}

	public void setSavepost(List<Post> savepost) {
		this.savepost = savepost;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) { this.gender = gender;
	}

	public List<Integer> getFollowers() {
		return followers;
	}

	public void setFollowers(List<Integer> followers) {
		this.followers = followers;
	}

	public List<Integer> getFollowing() {
		return following;
	}

	public void setFollowing(List<Integer> following) {
		this.following = following;
	}

}
