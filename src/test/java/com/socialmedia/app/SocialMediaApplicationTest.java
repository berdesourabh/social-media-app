package com.socialmedia.app;

import java.util.List;

import com.socialmedia.app.exception.SocialMediaException;
import com.socialmedia.app.models.Post;

import junit.framework.TestCase;

public class SocialMediaApplicationTest extends TestCase {

	private SocialMediaApplication socialMediaApplication = new SocialMediaApplication();

	public SocialMediaApplicationTest(String testName) {
		super(testName);
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
				socialMediaApplication.createPost(1, i, "Great weather outside");
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
