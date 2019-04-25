package com.stackroute.favouriteservice.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Player {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	
	private int pid;
	
	private String userId;
	
	private String fullName;
	private String name;
	
	
	
	

	public Player() {
		super();
	}
	

	public Player(int id, int pid, String userId, String fullName, String name) {
		super();
		this.id = id;
		this.pid = pid;
		this.userId = userId;
		this.fullName = fullName;
		this.name = name;
	}


	public String getFullName() {
		return fullName;
	}


	public void setFullName(String fullName) {
		this.fullName = fullName;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Player [id=" + id + ", pid=" + pid + ", userId=" + userId + "]";
	}
	
	
	
	
	
	

}
