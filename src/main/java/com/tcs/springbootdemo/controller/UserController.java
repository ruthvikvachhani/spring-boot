package com.tcs.springbootdemo.controller;

import java.util.Optional;

import javax.validation.Valid;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.tcs.springbootdemo.entity.User;
import com.tcs.springbootdemo.exception.UserNotFoundException;
import com.tcs.springbootdemo.services.IUserService;

@RestController
@RequestMapping("/user")
public class UserController { //spring bean, act as request receiver
	@Autowired  //DI
	IUserService userService;
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	
	@GetMapping()
	private Iterable<User> getUsers() {
		return userService.getAllUsers();
	}
	@GetMapping("/{id}")
	private Optional<User> getUser(@PathVariable("id") Integer id ) {
		return userService.getUser(id);
	}
	@ExceptionHandler(value = { UserNotFoundException.class, IllegalStateException.class })
	public ResponseEntity<User> exception(UserNotFoundException userNotFoundException) {
		return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
	}
	
	
	@PostMapping()
	private void saveUser(@RequestBody @Valid User user) {
		try {
			userService.save(user);
		} catch (Exception e) {
			logger.error(e.getCause().toString());
		}
		logger.debug(user.getFirstName());
		
	}
	@PutMapping("/{id}")
	private void updateUser(@RequestBody User user,@PathVariable("id") Integer id ) {
		userService.update(user,id);
		System.out.println(user.getFirstName());
	}
	
	@DeleteMapping("/{id}")
	private void deleteUser(@PathVariable("id") Integer id)
	{
		userService.deleteUser(id);
	}
}