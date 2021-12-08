package com.um.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.um.exception.UserNotFoundException;
import com.um.model.User;
import com.um.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;

	public User addUser(User user) {
		return userRepository.save(user);
	}

	public User findUserById(Long userId) {
		if (userRepository.existsById(userId)) {
			return userRepository.findById(userId).get();
		}
		throw new UserNotFoundException("User with userId " + userId + " does not Exists");
	}

	public List<User> getAllUserr() {
		return userRepository.findAll();
	}

	public User updateUser(User user) {
		if (userRepository.existsById(user.getUserId())) {
			userRepository.save(user);
		}
		throw new UserNotFoundException("User with userId " + user.getUserId() + " Does not exists");

	}

	public String deleteUser(Long userId) {
		if (userRepository.existsById(userId)) {
			userRepository.deleteById(userId);
			return "User with userId " + userId + " successfully deleted";
		}
		throw new UserNotFoundException("User with userId " + userId + " does not Exists");
	}

	public List<User> addAllUsers(List<User> users) {
		return userRepository.saveAll(users);
	}

}
