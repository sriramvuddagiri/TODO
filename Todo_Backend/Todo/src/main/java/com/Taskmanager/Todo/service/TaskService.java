package com.Taskmanager.Todo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Taskmanager.Todo.model.Task;

@Service
public interface TaskService {
	
	public List<Task> getallTasks( );
	public Task modifyTask( Task task ,Integer id);
	public Task createTask(Task task);
	List<Task> deleteTask( Integer taskId);
	

}
 