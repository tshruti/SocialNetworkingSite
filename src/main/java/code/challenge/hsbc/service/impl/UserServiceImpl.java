package code.challenge.hsbc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import code.challenge.hsbc.model.User;
import code.challenge.hsbc.repository.UserRepository;
import code.challenge.hsbc.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public boolean doesUserExist(String userID) {
		userRepository.existsById(userID);
		return userRepository.existsById(userID);
	}

	@Override
	public void saveUser(String userID) {
		User user = new User();
		user.setID(userID);
		userRepository.save(user);	
	}

}
