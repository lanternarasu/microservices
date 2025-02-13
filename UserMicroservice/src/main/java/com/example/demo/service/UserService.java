package com.example.demo.service;

import com.example.demo.entity.User;

public interface UserService {
	public void addUser(User user);

	public void deleteUser(int userId);
}
