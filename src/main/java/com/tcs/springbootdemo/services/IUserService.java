  
package com.tcs.springbootdemo.services;

import java.util.Optional;

import com.tcs.springbootdemo.entity.User;

public interface IUserService {
	void save(User user);

	Iterable<User> getAllUsers();

	Optional<User> getUser(Integer id);

	void deleteUser(Integer id);
}