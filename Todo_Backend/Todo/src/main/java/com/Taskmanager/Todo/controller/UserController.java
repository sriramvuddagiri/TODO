package com.Taskmanager.Todo.controller;

import java.io.Console;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Taskmanager.Todo.config.JwtUtil;
import com.Taskmanager.Todo.model.LoginDetails;
import com.Taskmanager.Todo.model.User;
import com.Taskmanager.Todo.service.UserService;

@RestController
@CrossOrigin("*")
@RequestMapping("/v1/user")
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("/login")
	public ResponseEntity<Object> login(@RequestBody LoginDetails loginDetails) {
//		if (userService.login(loginDetails) == "login successful") {
//			
//			return ResponseEntity.ok(token);
//		}
//		return (ResponseEntity) ResponseEntity.notFound();
		return ResponseEntity.ok().body(userService.login(loginDetails));

	}

	@PostMapping("/register")
	public ResponseEntity<Object> register(@RequestBody User user) {
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.register(user));
	}

	@PutMapping("/forgotPassword")
	public ResponseEntity<Object> forgotPassword(@RequestBody Map<String,String> object) {
		return ResponseEntity.status(HttpStatus.OK).body(userService.forgotPassword(object.get("username"), object.get("resetPassword"), object.get("confirmResetPassword")));
	}
}
