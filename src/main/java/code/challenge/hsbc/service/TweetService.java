package code.challenge.hsbc.service;

import java.util.List;

import javax.validation.Valid;

import code.challenge.hsbc.model.Tweets;

public interface TweetService {

	Tweets saveTweet(@Valid Tweets tweet);

	List<Tweets> getWall(String userID);
	
	List<Tweets> getTweetsOfFollowedUsersFromFollowerID(String userID);
}
