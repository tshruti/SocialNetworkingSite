package code.challenge.hsbc.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import code.challenge.hsbc.exceptions.InvalidInputException;
import code.challenge.hsbc.model.FollowUser;
import code.challenge.hsbc.model.Tweets;
import code.challenge.hsbc.service.FollowUserService;
import code.challenge.hsbc.service.TweetService;
import code.challenge.hsbc.service.UserService;
import code.challenge.hsbc.util.UserMessage;

@RestController
@RequestMapping("/socialNetworkingSite")
public class SocialNetworkingSiteController {

	@Autowired
	UserService userService;

	@Autowired
	TweetService tweetService;

	@Autowired
	FollowUserService followUserService;

	@PostMapping("/post")
	public String postToWall(@RequestBody @Valid Tweets tweet) {
		String userID = tweet.getUserID();
		if (!userService.doesUserExist(userID)) {
			userService.saveUser(userID);
		}

		tweetService.saveTweet(tweet);
		return String.format(UserMessage.TWEET_SUCCESS);
	}

	@GetMapping("/wall/{userID}")
	public List<Tweets> getWallOfUser(@PathVariable(value = "userID") String userID) throws Exception {
		if (!userService.doesUserExist(userID)) {
			throw new InvalidInputException(UserMessage.USER_DOES_NOT_EXIST);
		}
		return tweetService.getWall(userID);
	}

	@PostMapping("/follow")
	public String followUser(@RequestBody FollowUser followUser) throws Exception {
		String follwerID = followUser.getFollowUserPK().getFollowerID();
		String followedUserID = followUser.getFollowUserPK().getFollowedID();
		if (!(userService.doesUserExist(follwerID) && userService.doesUserExist(followedUserID))) {
			throw new InvalidInputException(UserMessage.USER_DOES_NOT_EXIST);
		}
		if (followUserService.doesMappingExists(followUser)) {
			throw new InvalidInputException(UserMessage.FOLLOW_MAPPING_ALREADY_EXISTS);
		}
		followUserService.followUser(followUser);

		return String.format(UserMessage.FOLLOW_USER_SUCCESS, follwerID, followedUserID);
	}

	@GetMapping("/timeline/{userID}")
	public List<Tweets> getTimeline(@PathVariable(value = "userID") String userID) throws Exception {
		if (!userService.doesUserExist(userID)) {
			throw new InvalidInputException(UserMessage.USER_DOES_NOT_EXIST);
		}
		return tweetService.getTweetsOfFollowedUsersFromFollowerID(userID);
	}

}
