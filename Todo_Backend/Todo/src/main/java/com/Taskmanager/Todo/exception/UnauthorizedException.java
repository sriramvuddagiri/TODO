package com.Taskmanager.Todo.exception;

public class UnauthorizedException extends RuntimeException {
	public UnauthorizedException(String message) {
        super(message);
    }

}
