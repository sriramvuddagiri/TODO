package com.Taskmanager.Todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Taskmanager.Todo.model.User;


@Repository
public interface UserRepository extends JpaRepository<User,Integer>{
	
	public User findByUsername(String username);
	

}
