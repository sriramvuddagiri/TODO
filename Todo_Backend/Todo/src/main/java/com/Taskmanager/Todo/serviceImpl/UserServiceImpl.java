package com.Taskmanager.Todo.serviceImpl;

import java.util.HashMap;
import java.util.Map;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Taskmanager.Todo.config.JwtUtil;
import com.Taskmanager.Todo.exception.UnauthorizedException;
import com.Taskmanager.Todo.exception.UserExistsException;
import com.Taskmanager.Todo.exception.UsernameNotFoundException;
import com.Taskmanager.Todo.model.LoginDetails;
import com.Taskmanager.Todo.model.User;
import com.Taskmanager.Todo.model.UserDetailsDTO;
import com.Taskmanager.Todo.repository.UserRepository;
import com.Taskmanager.Todo.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserDetailService userDetailService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private PasswordEncoder passwordEncoder;
	


	@Override
	public Map<String,String> login(LoginDetails loginDetails)  {
		Map<String, String> result = new HashMap();
		Authentication authenticate = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginDetails.getUsername(), loginDetails.getPassword()));
//		final UserDetails userdetails = userDetailService.loadUserByUsername(loginDetails.getUsername());
		UserDetailsDTO users =  (UserDetailsDTO) authenticate.getPrincipal();
		User findByUsername = userRepository.findByUsername(users.getUsername());
		String token = JwtUtil.generateToken(findByUsername);
		
		result.put("username", users.getUsername());
		result.put("token", token);
		return result;
	}

	@Override
	public Map<String,String> register(User user) {

		User userExist = userRepository.findByUsername(user.getUsername());
		if (userExist == null) {
			try {
				if (user.getPassword().equals(user.getConfirmPassword())) {
					String encodedpassword = passwordEncoder.encode(user.getPassword());
					user.setPassword(encodedpassword);
					userRepository.save(user);
					Map<String, String> result = new HashMap();
					result.put("message", "User Successfully Created");
					result.put("username", user.getUsername());
					return result;

				} else {
					// return "Check the Confirm Password Should match with Password";
					throw new UnauthorizedException("Check the Confirm Password Should match with Password");
				}
			} catch (RuntimeException ex) {

				throw new UnauthorizedException("Some Unknown Error Occured ");
			}

		} else {
			throw new UserExistsException("Username already Exists");
		}
	}

	@Override
	public String forgotPassword(String username, String resetPassword, String confirmResetPassword) {
		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("Username is not found please Check or Register");
		}
		if (resetPassword.equals(confirmResetPassword)) {
			String updateEncodedPassword=passwordEncoder.encode(resetPassword);
	        user.setPassword(updateEncodedPassword);
	        userRepository.save(user);
	        return "Password is reset successfully,Kindly login again ";
			} else {
				throw new UnauthorizedException("Password and confirmPassword should Match");
			}
		

	}

}
