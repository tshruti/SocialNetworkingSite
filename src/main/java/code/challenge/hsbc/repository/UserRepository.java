package code.challenge.hsbc.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import code.challenge.hsbc.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,String>{

	
	
}
