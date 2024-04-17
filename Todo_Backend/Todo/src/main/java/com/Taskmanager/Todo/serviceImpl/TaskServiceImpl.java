package com.Taskmanager.Todo.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.Taskmanager.Todo.config.JwtUtil;
import com.Taskmanager.Todo.model.Task;
import com.Taskmanager.Todo.model.User;
import com.Taskmanager.Todo.repository.TaskRepository;
import com.Taskmanager.Todo.repository.UserRepository;
import com.Taskmanager.Todo.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService{
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	TaskRepository taskRepository;
	
	public Integer getIdFromToken()
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Integer id = (Integer) authentication.getPrincipal();
		return id;
	}
	

	@Override
	public List<Task> getallTasks() {
		return userRepository.findById(getIdFromToken()).get().getTasks();
	}

	@Override
	public Task modifyTask(Task task,Integer taskId) {
		
		Task t=taskRepository.findById(taskId).get();
		t.setTitle(task.getTitle());
		t.setTaskDescription(task.getTaskDescription());
		t.setStatus(task.getStatus());
		t.setDueDate(task.getDueDate());
		taskRepository.save(t);
		return t;
	}

	@Override
	public Task createTask(Task task ) {
		
		User user=userRepository.findById(getIdFromToken()).get();
		task.setUser(user);
		taskRepository.save(task);
		return task;
	}

	@Override
	public List<Task> deleteTask(Integer taskId) {
		taskRepository.deleteById(taskId);
		
		return getallTasks();
	}

}
