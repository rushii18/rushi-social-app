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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // to create table in your database in our case we are using SQL workbench
@Data
@NoArgsConstructor
@AllArgsConstructor
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

	@ManyToMany
	private List<Comment> commnet = new ArrayList<>();

}
