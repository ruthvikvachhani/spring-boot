  
package com.tcs.springbootdemo.services;

import java.util.Optional;

import com.tcs.springbootdemo.entity.User;

public interface IUserService {
	void save(User user) throws Exception;

	Iterable<User> getAllUsers();

	Optional<User> getUser(Integer id);

	void deleteUser(Integer id);

	void update(User user, Integer id);
}