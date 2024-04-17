package com.Taskmanager.Todo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Taskmanager.Todo.config.JwtUtil;
import com.Taskmanager.Todo.model.Task;
import com.Taskmanager.Todo.service.TaskService;



@RestController
@CrossOrigin("*")
@RequestMapping("/v1/task")
public class TaskController {
	
	@Autowired
	TaskService taskService;
	
	@GetMapping("/all")
//	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<List<Task>> getallTasks( )
	{
//		if(JwtUtil.validateToken(token))
//		{
		return ResponseEntity.ok(taskService.getallTasks());
//		}
//		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
//		
	}
	
	@PutMapping("/modify/{id}")
	public ResponseEntity<Task> modifyTask(@RequestBody Task task,@PathVariable Integer id )
	{
//		
		return ResponseEntity.ok(taskService.modifyTask(task,id));

//		
	}
	
	@PostMapping("/create")
//	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<Task> createTask(@RequestBody Task task)
	{
			return ResponseEntity.status(HttpStatus.CREATED).body(taskService.createTask(task));
		//}
		//return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
	}
	
	@DeleteMapping("/delete/{taskId}")
//	@PreAuthorize("hasRole('ROLE_USER')")
	ResponseEntity<List<Task>> deleteTask(@PathVariable Integer taskId)
	{
//		if(JwtUtil.validateToken(token))
//		{
			return ResponseEntity.ok(taskService.deleteTask( taskId));
//		}
//		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
//		
	}
	
	

}
