package com.arib.NotesAppApi.services;

import com.arib.NotesAppApi.dao.UserDao;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomUserDetailService implements UserDetailsService{

	private UserDao repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return repository.findByEmail(username).orElseThrow(() -> new RuntimeException("No User Exists with this Email ID"));
	}
}