package code.challenge.hsbc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.google.gson.Gson;

import code.challenge.hsbc.controller.SocialNetworkingSiteController;
import code.challenge.hsbc.model.FollowUser;
import code.challenge.hsbc.model.FollowUserPK;
import code.challenge.hsbc.model.Tweets;
import code.challenge.hsbc.model.User;
import code.challenge.hsbc.service.FollowUserService;
import code.challenge.hsbc.service.TweetService;
import code.challenge.hsbc.service.UserService;

@RunWith(SpringRunner.class)
@WebMvcTest(SocialNetworkingSiteController.class)
public class SocialNetworkingSiteTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	TweetService tweetService;

	@MockBean
	UserService userService;

	@MockBean
	FollowUserService followUserService;

	public static User testUser1 = new User();
	public static User testUser2 = new User();
	public static User testUser3 = new User();

	public static Tweets tweet1FromUser1 = new Tweets();
	public static Tweets tweet2FromUser1 = new Tweets();
	public static Tweets tweet1FromUser2 = new Tweets();

	public static FollowUser user1FollowsUser2 = new FollowUser();
	public static FollowUser user2FollowsUser3 = new FollowUser();

	@BeforeClass
	public static void setup() {
		// User data setup
		testUser1.setID("user1");
		testUser2.setID("user2");
		testUser3.setID("user3");

		// Tweets data setup
		tweet1FromUser1.setUserID("user1");
		tweet1FromUser1.setDescription("My first tweet");
		tweet2FromUser1.setUserID("user2");
		tweet2FromUser1.setDescription("My second tweet");
		tweet1FromUser2.setUserID("user1");
		tweet1FromUser2.setDescription("Good morning all!!");

		// Follow user data setup
		FollowUserPK candidateKey = new FollowUserPK();
		candidateKey.setFollowerID("user1");
		candidateKey.setFollowedID("user2");

		user1FollowsUser2.setFollowUserPK(candidateKey);

		FollowUserPK candidateKey2 = new FollowUserPK();
		candidateKey2.setFollowerID("user2");
		candidateKey2.setFollowedID("user3");

		user2FollowsUser3.setFollowUserPK(candidateKey2);
	}

	@Test
	public void testPostOnWallAPI() throws Exception {

		given(userService.doesUserExist(tweet1FromUser1.getUserID())).willReturn(true);
		given(tweetService.saveTweet(tweet1FromUser1)).willReturn(tweet1FromUser1);

		Gson gson = new Gson();
		String json = gson.toJson(tweet1FromUser1);
		mvc.perform(post("/socialNetworkingSite/post").content(json).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void testGetWallAPI() throws Exception {

		List<Tweets> tweets = new ArrayList<>();
		tweets.add(tweet1FromUser1);
		tweets.add(tweet2FromUser1);

		given(userService.doesUserExist(tweet1FromUser1.getUserID())).willReturn(true);
		given(tweetService.getWall(testUser1.getID())).willReturn(tweets);

		mvc.perform(get("/socialNetworkingSite/wall/user1")).andExpect(status().isOk());
	}

	@Test
	public void testGetWallAPIWhenUserDoesNotExist() throws Exception {

		List<Tweets> tweets = new ArrayList<>();
		tweets.add(tweet1FromUser1);
		tweets.add(tweet2FromUser1);

		given(userService.doesUserExist(tweet1FromUser1.getUserID())).willReturn(false);

		mvc.perform(get("/socialNetworkingSite/wall/user1")).andExpect(status().is4xxClientError());
	}

	@Test
	public void testFollowUserAPI() throws Exception {

		given(userService.doesUserExist(testUser1.getID())).willReturn(true);
		given(userService.doesUserExist(testUser2.getID())).willReturn(true);
		given(followUserService.doesMappingExists(user1FollowsUser2)).willReturn(false);

		Gson gson = new Gson();
		String content = gson.toJson(user1FollowsUser2);

		mvc.perform(post("/socialNetworkingSite/follow").content(content).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void testFollowUserAPIWhenUserDoesNotExist() throws Exception {

		given(userService.doesUserExist(testUser1.getID())).willReturn(false);
		given(userService.doesUserExist(testUser2.getID())).willReturn(true);
		given(followUserService.doesMappingExists(user1FollowsUser2)).willReturn(false);

		Gson gson = new Gson();
		String content = gson.toJson(user1FollowsUser2);

		mvc.perform(post("/socialNetworkingSite/follow").content(content).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is4xxClientError());
	}

	@Test
	public void testGetTimelineAPI() throws Exception {

		given(userService.doesUserExist(tweet1FromUser1.getUserID())).willReturn(true);

		mvc.perform(get("/socialNetworkingSite/timeline/user1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void testGetTimelineAPIWhenUserDoesNotExist() throws Exception {

		given(userService.doesUserExist(tweet1FromUser1.getUserID())).willReturn(false);

		mvc.perform(get("/socialNetworkingSite/timeline/user1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is4xxClientError());
	}

}
