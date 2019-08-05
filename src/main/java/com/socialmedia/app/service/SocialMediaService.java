package com.socialmedia.app.service;

import java.util.List;

import com.socialmedia.app.exception.SocialMediaException;
import com.socialmedia.app.models.Post;

/**
 * Social Media Service Interface
 */
public interface SocialMediaService {

	/**
     * Get News Feeds
     *
     * @param { int } User Id userId
     */
	List<Integer> getNewsFeeds(int userId) throws SocialMediaException;

	/**
     * Get News Feeds
     *
     * @param { int } User Id userId
     * @param { int } Post Id postId
     * @param { String } Content content
     */
	Post createPost(int userId, int postId, String content) throws SocialMediaException;

	/**
     * Get News Feeds
     *
     * @param { int } Follower Id followerId
     * @param { int } Followee Id followeeId
     * 
     */
	boolean follow(int followerId, int followeeId) throws SocialMediaException;

	/**
     * Get News Feeds
     *
     * @param { int } Follower Id followerId
     * @param { int } Followee Id followeeId
     */
	boolean unfollow(int followerId, int followeeId) throws SocialMediaException;

}
