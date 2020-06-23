package controllers;

import models.User;
import models.UserType;
import repositories.UserRepository;

public class UserController {
	
	private UserRepository userRepository;
	private User loggedUser;
	
	public UserController() {
		this.userRepository = new UserRepository();
		this.loggedUser = null;
	}
	
	public boolean checkIfUserHasNoNullFields(User user) {
		boolean result = false;
		if(user.getPassword() != null && user.getUsername() != null) {
			result = true;
		}
		return result;
	}
	

	public boolean login(User user) {
		boolean result = false;
		if(checkIfUserHasNoNullFields(user)) {
			User realUser = userRepository.selectUser(user.getUsername());
			if(user.getPassword().equals(realUser.getPassword()) && loggedUser == null) {
				loggedUser = user;
				result = true;
			}
		}
		return result;
	}
	
	public boolean addNewContentManager(User user) {
		boolean result = false;
		if(checkIfUserHasNoNullFields(user)) {
			user.setType(UserType.CM);
			userRepository.insertUser(user);
			result = true;
		}
		return result;
	}
	
	public boolean logout(String username) {
		boolean result = false;
		if(loggedUser != null) {
			loggedUser = null;
			result = true;
		}
		return result;
	}
	
}
