package com.arib.NotesAppApi.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.arib.NotesAppApi.entities.User;

public interface UserDao extends JpaRepository<User, Integer>{
	
	Optional<User> findByEmail(String email);

}
