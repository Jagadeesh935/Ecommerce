package com.jk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jk.model.User;
import com.jk.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;

	public User saveUser(User user) {
		return userRepo.save(user);
	}

	public boolean login(String username, String password) {
		User returneduser = userRepo.getByUsername(username);
		if (returneduser != null) {
			if (username.equals(returneduser.getUsername())) {
				if (password.equals(returneduser.getPassword())) {
					return true;
				}
			}
		}

		return false;
	}

	public User getUserByUsername(String username) {
		// TODO Auto-generated method stub
		return userRepo.getByUsername(username);
	}

	public User getUserById(Long id) {
		// TODO Auto-generated method stub
		return userRepo.getById(id);
	}

}
