package com.jk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jk.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	User getByUsername(String username);
	
}
