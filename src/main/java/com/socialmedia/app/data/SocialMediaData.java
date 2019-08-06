package com.socialmedia.app.data;

import java.util.ArrayList;
import java.util.List;

import com.socialmedia.app.models.User;

public class SocialMediaData {

	private static SocialMediaData socialMediaDataInstance;
	// public TreeMap<User, TreeSet<Post>> userPosts;
	public List<User> users;

	private SocialMediaData() {

		// userPosts = new TreeMap<>();
		users = new ArrayList<>();
	}

	static {
		try {
			socialMediaDataInstance = new SocialMediaData();

		} catch (Exception e) {
			throw new RuntimeException("Exception occured in creating singleton instance");
		}
	}

	public static SocialMediaData getInstance() {
		return socialMediaDataInstance;
	}

}
