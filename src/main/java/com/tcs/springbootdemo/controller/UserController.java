package com.tcs.springbootdemo.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.springbootdemo.entity.User;
import com.tcs.springbootdemo.exception.UserNotFoundException;
import com.tcs.springbootdemo.services.IUserService;

@RestController
public class UserController { //spring bean, act as request receiver
	@Autowired  //DI
	IUserService userService;
	
	
	@GetMapping("/user")
	private Iterable<User> getUsers() {
		return userService.getAllUsers();
	}
	@GetMapping("/user/{id}")
	private Optional<User> getUser(@PathVariable("id") Integer id ) {
		return userService.getUser(id);
	}
	@ExceptionHandler(value = { UserNotFoundException.class, IllegalStateException.class })
	public ResponseEntity<User> exception(UserNotFoundException userNotFoundException) {
		return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
	}
	
	@Transactional
	@PostMapping("/user")
	private void saveUser(@RequestBody @Valid User user) {
		userService.save(user);
		System.out.println(user.getFirstName());
		throw new RuntimeException();
	}
	@PutMapping("/user/{id}")
	private void updateUser(@RequestBody User user,@PathVariable("id") Integer id ) {
		userService.update(user,id);
		System.out.println(user.getFirstName());
	}
	
	@DeleteMapping("/user/{id}")
	private void deleteUser(@PathVariable("id") Integer id)
	{
		userService.deleteUser(id);
	}
}