package code.challenge.hsbc;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import code.challenge.hsbc.model.Tweets;
import code.challenge.hsbc.repository.TweetRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class IntegrationTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private TweetRepository tweetRepository;

	@Test
	public void testFindByUserIDOrderByDateTimeDesc() {
		// given
		Tweets tweet = new Tweets();
		tweet.setUserID("shruti.talewad");
		tweet.setDescription("test0");
		entityManager.persist(tweet);
		entityManager.flush();

		// when
		List<Tweets> tweets = tweetRepository.findByUserIDOrderByDateTimeDesc(tweet.getUserID());

		// then
		assertThat(tweets.size()).isEqualTo(1);
	}

}
