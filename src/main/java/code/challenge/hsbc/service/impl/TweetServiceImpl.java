package code.challenge.hsbc.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import code.challenge.hsbc.model.Tweets;
import code.challenge.hsbc.repository.TweetRepository;
import code.challenge.hsbc.service.TweetService;

@Service
public class TweetServiceImpl implements TweetService {

	@Autowired
	TweetRepository tweetRepository;
	
	@Override
	public Tweets saveTweet(@Valid Tweets tweet) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		tweet.setDateTime(LocalDateTime.parse(LocalDateTime.now().format(formatter), formatter));
		return tweetRepository.save(tweet);
	}

	@Override
	public List<Tweets> getWall(String userID) {
		return tweetRepository.findByUserIDOrderByDateTimeDesc(userID);
	}

	@Override
	public List<Tweets> getTweetsOfFollowedUsersFromFollowerID(String userID) {
		List<Tweets> followedUserTweets = tweetRepository.findTweetsOfFollowedUsersFromFollowerID(userID);
		return followedUserTweets;
	}
}
