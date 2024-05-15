package com.arib.NotesAppApi.services;

import com.arib.NotesAppApi.dao.UserDao;
import com.arib.NotesAppApi.entities.User;
import com.arib.NotesAppApi.exception.BadCredentialsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService{

	@Autowired
	private UserDao repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return repository.findByEmail(username).orElseThrow(() -> new RuntimeException("No User Exists with this Email ID"));
	}
}