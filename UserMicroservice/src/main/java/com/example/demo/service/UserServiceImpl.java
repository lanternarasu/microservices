package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository repository;
	
	@Autowired
	APIClient apiClient;

	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		repository.save(user);
	}

	@Override
	public void deleteUser(int userId) {
		// TODO Auto-generated method stub
		apiClient.deleteUser(userId);
		repository.deleteById(userId);
	}
	
}
