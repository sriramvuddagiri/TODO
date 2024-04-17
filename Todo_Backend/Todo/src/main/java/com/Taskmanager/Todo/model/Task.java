package com.Taskmanager.Todo.model;

import java.util.Date;

import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Task {
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer taskId;
	
	@Column
	private String title;
	
	@Column
	@Size(min = 10 ,max = 2000, message =" minimum 10 characters are required")
	private String taskDescription;
	
	@Column
	private Date dueDate;
	@Column
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "user_id",referencedColumnName="userId")
	private User user;
	

}
