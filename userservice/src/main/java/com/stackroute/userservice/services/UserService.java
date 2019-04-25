package com.stackroute.userservice.services;

import com.stackroute.userservice.domain.User;
import com.stackroute.userservice.exception.UserAlreadyExistsException;
import com.stackroute.userservice.exception.UserNotFoundException;

public interface UserService {

	
	boolean saveUser(User user) throws UserAlreadyExistsException;
	
	User findByUserIdAndPassword(String userId,String password) throws UserNotFoundException;
}
