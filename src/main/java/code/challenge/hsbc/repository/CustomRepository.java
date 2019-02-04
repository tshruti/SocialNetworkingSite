package code.challenge.hsbc.repository;

import java.util.List;

import code.challenge.hsbc.model.Tweets;

public interface CustomRepository {

	List<Tweets> findTweetsOfFollowedUsersFromFollowerID(String followerID);
	
}
