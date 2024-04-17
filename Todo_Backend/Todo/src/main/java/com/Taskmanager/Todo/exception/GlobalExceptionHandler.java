package com.Taskmanager.Todo.exception;

import java.util.Map;

import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;
import java.util.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(UnauthorizedException.class)
	public ResponseEntity<Object> handleUnauthorizedExceptions(UnauthorizedException ex) {
		Map<String, String> result = new HashMap<>();
		result.put("message", ex.getMessage());
		result.put("Date&Time", LocalDateTime.now().toString());
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);

	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(UserExistsException.class)
	public ResponseEntity<Object> handleUserExistsExceptions(UserExistsException ex) {
		Map<String, String> result = new HashMap<>();
		result.put("message", ex.getMessage());
		result.put("Date&Time", LocalDateTime.now().toString());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleExceptions(Exception ex) {
		Map<String, String> result = new HashMap<>();
		result.put("message", ex.getMessage());
		result.put("Date&Time", LocalDateTime.now().toString());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);

	}
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<Object> handleUsernameNotFoundException(UsernameNotFoundException ex) {
		Map<String, String> result = new HashMap<>();
		result.put("message", ex.getMessage());
		result.put("Date&Time", LocalDateTime.now().toString());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
	}

}
