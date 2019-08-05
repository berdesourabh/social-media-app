package com.socialmedia.app.serviceimpl;

import com.socialmedia.app.data.SocialMediaData;
import com.socialmedia.app.models.User;
import com.socialmedia.app.service.UserService;

public class UserServiceImpl implements UserService {
	private SocialMediaData socialMediaData;

	public UserServiceImpl() {
		socialMediaData = SocialMediaData.getInstance();
	}

	@Override
	public void create() {
		int currentId = 0;
		if (!socialMediaData.users.isEmpty()) {
			currentId = socialMediaData.users.stream().map(user -> user.getId()).max(Integer::compare).get();
		}
		socialMediaData.users.add(new User(++currentId));

	}

}
