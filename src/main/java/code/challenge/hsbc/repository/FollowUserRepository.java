package code.challenge.hsbc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import code.challenge.hsbc.model.FollowUser;
import code.challenge.hsbc.model.FollowUserPK;

@Repository
public interface FollowUserRepository extends JpaRepository<FollowUser,FollowUserPK>{
	
}
