package com.Taskmanager.Todo.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Taskmanager.Todo.model.LoginDetails;
import com.Taskmanager.Todo.model.User;




@Service
public interface UserService {
	
	 public Map<String, String> login(LoginDetails loginDetails);
	    public Map<String, String> register(User user);
	   public String forgotPassword(String username, String resetPassword, String confirmResetPassword);
	    
	

}
