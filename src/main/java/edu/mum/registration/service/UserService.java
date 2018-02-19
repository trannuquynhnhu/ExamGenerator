package edu.mum.registration.service;

import edu.mum.registration.domain.User;

public interface UserService {
	User getUserByUsername(String userName);
	Iterable<User> getAllUsers();
	User saveUser(User user);
	User getUserById(long id);
}
