package com.socialmedia.app.models;

import java.util.Date;

public class Post implements Comparable<Post> {

	private int id;

	private String content;

	private Date createdDate;

	private User user;

	public Post(int id, String content, User user) {
		this.id = id;
		this.content = content;
		this.user = user;
		this.createdDate = new Date();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int compareTo(Post o) {
		return o.createdDate.compareTo(this.createdDate);
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", content=" + content + ", createdDate=" + createdDate + ", user=" + user + "]";
	}

}
