package com.jk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jk.model.User;
import com.jk.repository.UserRepository;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserService implements UserDetailsService {

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

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.getByUsername(username);
		if (user == null) throw new UsernameNotFoundException("User not found");
		Collection<GrantedAuthority> authority = new ArrayList<>();
		for (String role : user.getRoles().split(",")){
			authority.add(new SimpleGrantedAuthority(role));
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(), authority);
	}
}
