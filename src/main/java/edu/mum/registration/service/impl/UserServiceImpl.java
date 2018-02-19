package edu.mum.registration.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import edu.mum.registration.domain.User;
import edu.mum.registration.repository.UserRepository;
import edu.mum.registration.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository userRepository;
	
	@Autowired
 	BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public User getUserByUsername(String userName) {		
		return userRepository.findUserByUsername(userName);
	}
	
	@Override
	public Iterable<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public User saveUser(User user) {
		String encodedPassword = passwordEncoder.encode(user.getUserCredentials().getPassword());
		user.getUserCredentials().setPassword(encodedPassword);  		
		return userRepository.save(user);
	}
	
	@Override
	public User getUserById(long userId) {
		return userRepository.findOne(userId);
	}	
}
