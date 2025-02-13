package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exceptions.VideoNotFoundException;

@Service
public class StreamingAccessService {
	
	@Autowired
	APIClient apiClient;
	
	public boolean checkIfAllowedToStream(int userId,String videoId){
		boolean access = apiClient.checkAccess(userId,videoId);
		return access;
	}
	
	public boolean checkIfVideoExists(String videoId) throws VideoNotFoundException {
		boolean isExists = apiClient.checkIfVideoExists(videoId);
		if(isExists) {
			System.out.println("returning true");
			return true;
		}
		else {
			System.out.println("video with given id does not exist");
			throw new VideoNotFoundException("video with given id does not exist");}
	}
}