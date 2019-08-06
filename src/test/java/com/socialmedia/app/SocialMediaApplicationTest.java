package com.socialmedia.app;

import java.util.List;

import org.junit.BeforeClass;

import com.socialmedia.app.exception.SocialMediaException;
import com.socialmedia.app.models.Post;
import com.socialmedia.app.service.UserService;
import com.socialmedia.app.serviceimpl.UserServiceImpl;

import junit.framework.TestCase;

public class SocialMediaApplicationTest extends TestCase {

	private SocialMediaApplication socialMediaApplication = new SocialMediaApplication();
	private UserService userService = new UserServiceImpl();

	public SocialMediaApplicationTest(String testName) {
		super(testName);
	}

	@BeforeClass
	public void setUp() {
		userService.create();
		userService.create();
	}

	public void testCreatePost_Success() {
		try {
			Post newPost = socialMediaApplication.createPost(1, 1, "Great weather outside");
			assertNotNull(newPost);
		} catch (SocialMediaException e) {
			e.printStackTrace();
		}

	}

	public void testCreatePost_UserDoesNotExists_ThrowsException() {
		try {
			socialMediaApplication.createPost(2, 1, "Great weather outside");
		} catch (SocialMediaException e) {
			assertEquals(e.getMessage(), "User does not exists");
		}

	}

	public void testCreatePost_WithNullContent_ThrowsException() {
		try {
			socialMediaApplication.createPost(2, 1, null);
		} catch (SocialMediaException e) {
			assertEquals(e.getMessage(), "Post content can not be null");
		}

	}

	public void testGetNewsFeeds_Success() {

		try {
			List<Integer> postIds = socialMediaApplication.getNewsFeeds(1);
			assertNotNull(postIds);
		} catch (SocialMediaException e) {
			e.printStackTrace();
		}

	}

	public void testGetNewsFeeds_FetchOnly20Posts() throws InterruptedException {
		try {
			for (int i = 0; i < 25; i++) {
				socialMediaApplication.createPost(1, i, "Great weather outside " + i);
				Thread.sleep(1000);
			}
			List<Integer> postIds = socialMediaApplication.getNewsFeeds(1);
			assertEquals(postIds.size(), 20);

		} catch (SocialMediaException e) {
			e.printStackTrace();
		}

	}

	public void testGetNewsFeeds_UserDoesNotExists_ThrowsException() {

		try {
			socialMediaApplication.getNewsFeeds(1);
		} catch (SocialMediaException e) {
			assertEquals(e.getMessage(), "User does not exists");
		}
	}

	public void testFollow_Success() {

		try {
			boolean isFollowed = socialMediaApplication.follow(1, 2);
			assertTrue(isFollowed);
		} catch (SocialMediaException e) {
			e.printStackTrace();
		}

	}

	public void testFollow_UserDoesNotExists_ThrowsException() {

		try {
			socialMediaApplication.follow(1, 3);
		} catch (SocialMediaException e) {
			assertEquals(e.getMessage(), "User does not exists");
		}

	}

	/*
	 * public void testGetNewsFeeds_FetchFeedsFromFollowee() throws
	 * InterruptedException { try { socialMediaApplication.follow(1, 2); for (int i
	 * = 1; i <= 5; i++) { socialMediaApplication.createPost(2, i,
	 * "Greetings for Today" + i); Thread.sleep(1000); } for (int i = 6; i <= 10;
	 * i++) { socialMediaApplication.createPost(1, i, "Great weather outside" + i);
	 * Thread.sleep(1000); } List<Integer> postIds =
	 * socialMediaApplication.getNewsFeeds(1); Optional<Integer> topPostId =
	 * postIds.stream().max(Integer::compareTo); assertEquals(Integer.valueOf(10),
	 * topPostId.get());
	 * 
	 * } catch (SocialMediaException e) { e.printStackTrace(); }
	 * 
	 * }
	 */

	public void testUnFollow_Success() {

		try {
			boolean isUnFollwed = socialMediaApplication.unfollow(1, 2);
			assertTrue(isUnFollwed);
		} catch (SocialMediaException e) {
			e.printStackTrace();
		}

	}

	public void testUnFollow_UserDoesNotExists_ThrowsException() {

		try {
			socialMediaApplication.unfollow(1, 3);
		} catch (SocialMediaException e) {
			assertEquals(e.getMessage(), "User does not exists");
		}

	}
}
