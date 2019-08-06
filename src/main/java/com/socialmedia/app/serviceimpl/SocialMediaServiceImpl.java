package com.socialmedia.app.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.socialmedia.app.data.SocialMediaData;
import com.socialmedia.app.exception.SocialMediaException;
import com.socialmedia.app.models.Post;
import com.socialmedia.app.models.User;
import com.socialmedia.app.service.SocialMediaService;

public class SocialMediaServiceImpl implements SocialMediaService {

	private SocialMediaData socialMediaData;

	public SocialMediaServiceImpl() {
		socialMediaData = SocialMediaData.getInstance();
	}

	public List<Integer> getNewsFeeds(int userId) throws SocialMediaException {
		Optional<User> currentUser = getUserById(userId);
		if (currentUser.isPresent()) {
			List<Integer> followeesIds = socialMediaData.users.stream()
					.filter(user -> user.getFollowedBy().contains(currentUser.get())).map(follower -> follower.getId())
					.collect(Collectors.toList());

			List<Post> posts = new ArrayList<>();
			socialMediaData.users.forEach(user -> {
				if (followeesIds.contains(user.getId()) || user.getId() == userId) {
					posts.addAll(user.getPosts());
				}
			});
			return posts.stream().map(p -> p.getId()).limit(20).collect(Collectors.toList());
		} else {
			throw new SocialMediaException("User does not exists");
		}
	}

	public Post createPost(int userId, int postId, String content) throws SocialMediaException {
		if (content == null)
			throw new SocialMediaException("Post content can not be null");
		Optional<User> user = getUserById(userId);
		if (user.isPresent()) {
			Post newPost = new Post(postId, content, user.get());
			socialMediaData.users.stream().filter(u -> u.getId() == userId).findFirst().get().getPosts().add(newPost);
			return newPost;
		} else {
			throw new SocialMediaException("User does not exists");
		}

	}

	public boolean follow(int followerId, int followeeId) throws SocialMediaException {
		Optional<User> followeeUser = getUserById(followeeId);
		Optional<User> followerUser = getUserById(followerId);

		if (followeeUser.isPresent() && followerUser.isPresent()) {
			followeeUser.get().getFollowedBy().add(followerUser.get());
			return true;
		} else {
			throw new SocialMediaException("User does not exists");
		}
	}

	public boolean unfollow(int followerId, int followeeId) throws SocialMediaException {
		Optional<User> followeeUser = getUserById(followeeId);
		Optional<User> followerUser = getUserById(followerId);

		if (followeeUser.isPresent() && followerUser.isPresent()) {
			followeeUser.get().getFollowedBy().remove(followerUser.get());
			return true;
		} else {
			throw new SocialMediaException("User does not exists");
		}
	}

	/**
	 * 
	 * @param userId
	 * @return {@link Optional<User> }
	 */
	private Optional<User> getUserById(int userId) {
		Optional<User> user = socialMediaData.users.stream().filter(followee -> followee.getId() == userId).findAny();
		return user;
	}

}
