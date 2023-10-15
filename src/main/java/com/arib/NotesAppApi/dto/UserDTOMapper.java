package com.arib.NotesAppApi.dto;

import java.util.function.Function;

import org.springframework.stereotype.Service;

import com.arib.NotesAppApi.entities.User;

@Service
public class UserDTOMapper implements Function<User, UserDTO>{

	@Override
	public UserDTO apply(User user) {
		return new UserDTO(
				user.getId(),
				user.getFirstName(),
				user.getLastName(),
				user.getEmail()
//				,user.getAuthorities().stream()
//				.map(GrantedAuthority::getAuthority)
//				.collect(Collectors.toList())
				);
	}

}