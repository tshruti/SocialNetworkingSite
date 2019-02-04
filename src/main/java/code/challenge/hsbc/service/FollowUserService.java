package code.challenge.hsbc.service;

import code.challenge.hsbc.model.FollowUser;

public interface FollowUserService {

	FollowUser followUser(FollowUser followUser);

	boolean doesMappingExists(FollowUser followUser);
	
}
