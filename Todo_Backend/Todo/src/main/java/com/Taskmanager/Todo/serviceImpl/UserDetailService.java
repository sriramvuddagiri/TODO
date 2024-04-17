package com.Taskmanager.Todo.serviceImpl;


import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Taskmanager.Todo.model.User;
import com.Taskmanager.Todo.model.UserDetailsDTO;
import com.Taskmanager.Todo.repository.UserRepository;

@Service
public class UserDetailService implements UserDetailsService  {

	@Autowired 
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		return new UserDetailsDTO(user);
//		return org.springframework.security.core.userdetails.User("","",new ArrayList<>()) ;
//return	org.springframework.security.core.userdetails.User("", null, new ArrayList<>());
//		return ;
	}
	
	
}
