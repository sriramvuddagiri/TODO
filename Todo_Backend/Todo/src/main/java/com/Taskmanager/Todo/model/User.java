package com.Taskmanager.Todo.model;


import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

import javax.validation.constraints.NotNull;
import jakarta.persistence.Transient;
import lombok.Data;

@Entity
@Table(name="users")
@Data
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	
	@Column(unique = true)
	@NotNull
	private String username;
	@Column
	@NotNull
	@Size(min=8, message = "minimum 8 Characters required")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[@$!%*#?&()])(?=\\S+$).{8,}$",message = "password must alteast 8 characters,one Lowercase letter,one Uppercase letter,one numeric value,one special character")
	private String password;
	
	@Transient
    private String confirmPassword;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	
	private List<Task> tasks; 

}
