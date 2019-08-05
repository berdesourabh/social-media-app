package com.socialmedia.app;

import java.util.List;

import com.socialmedia.app.exception.SocialMediaException;
import com.socialmedia.app.models.Post;
import com.socialmedia.app.service.SocialMediaService;
import com.socialmedia.app.service.UserService;
import com.socialmedia.app.serviceimpl.SocialMediaServiceImpl;
import com.socialmedia.app.serviceimpl.UserServiceImpl;

/**
 * 
 * Social Media Application Driver class
 *
 */
public class SocialMediaApplication {

	private SocialMediaService socialMediaService;
	private UserService userService;

	public SocialMediaApplication() {
		socialMediaService = new SocialMediaServiceImpl();
		userService = new UserServiceImpl();

		// Create 2 default users
		userService.create();
		userService.create();

	}

	/**
	 * 
	 * @param userId
	 * @param postId
	 * @param content
	 * @return {@link Post}
	 * @throws SocialMediaException
	 */
	public Post createPost(int userId, int postId, String content) throws SocialMediaException {
		Post newPost = socialMediaService.createPost(userId, postId, content);
		return newPost;
	}

	/**
	 * 
	 * @param userId
	 * @return {@link List<Integer>}
	 * @throws SocialMediaException
	 */
	public List<Integer> getNewsFeeds(int userId) throws SocialMediaException {
		List<Integer> postIds = socialMediaService.getNewsFeeds(userId);
		return postIds;
	}

	/**
	 * 
	 * @param followerId
	 * @param followeeId
	 * @return boolean
	 * @throws SocialMediaException
	 */
	public boolean follow(int followerId, int followeeId) throws SocialMediaException {
		boolean isFollowed = socialMediaService.follow(followerId, followeeId);
		return isFollowed;
	}

	/**
	 * 
	 * @param followerId
	 * @param followeeId
	 * @return boolean
	 * @throws SocialMediaException
	 */
	public boolean unfollow(int followerId, int followeeId) throws SocialMediaException {
		boolean isUnFollowed = socialMediaService.unfollow(followerId, followeeId);
		return isUnFollowed;
	}
}
