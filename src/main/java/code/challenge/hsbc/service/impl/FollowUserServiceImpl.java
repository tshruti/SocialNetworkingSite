package code.challenge.hsbc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import code.challenge.hsbc.model.FollowUser;
import code.challenge.hsbc.repository.FollowUserRepository;
import code.challenge.hsbc.service.FollowUserService;

@Service
public class FollowUserServiceImpl implements FollowUserService {
	
	@Autowired
	FollowUserRepository followUserRep;

	@Override
	public FollowUser followUser(FollowUser followUser) {
		return followUserRep.save(followUser);
	}

	@Override
	public boolean doesMappingExists(FollowUser followUser) {
		return followUserRep.existsById(followUser.getFollowUserPK());
	}

}
