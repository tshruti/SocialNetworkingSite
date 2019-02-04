package code.challenge.hsbc.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import code.challenge.hsbc.model.Tweets;
import code.challenge.hsbc.repository.CustomRepository;
import code.challenge.hsbc.util.DBUtils;

public class CustomRepositoryImpl implements CustomRepository{

	@PersistenceContext
    private EntityManager entityManager;
	
	@Override
	public List<Tweets> findTweetsOfFollowedUsersFromFollowerID(String followerID) {

		Query queryEmployeeByFirstName = entityManager.createQuery(String.format(DBUtils.QUERY_FOR_USER_TIMELINE, followerID));

		List<Tweets> tweets = queryEmployeeByFirstName.getResultList();
		
		return tweets;
	}

}
