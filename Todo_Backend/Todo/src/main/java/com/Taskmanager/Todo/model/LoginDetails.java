package com.Taskmanager.Todo.model;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class LoginDetails {
	
	@NotBlank(message="userName should not be empty")
	private String username;
	
	@NotBlank(message = "Password should not be empty")
	private String password;
	

}
