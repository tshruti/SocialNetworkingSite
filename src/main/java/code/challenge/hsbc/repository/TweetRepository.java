package code.challenge.hsbc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import code.challenge.hsbc.model.Tweets;

@Repository
public interface TweetRepository extends JpaRepository<Tweets,Long>, CustomRepository{

	List<Tweets> findByUserIDOrderByDateTimeDesc(String userID);
	
}
