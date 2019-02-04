package code.challenge.hsbc.service;

public interface UserService {
	
	boolean doesUserExist(String userID);

	void saveUser(String userID);

}
