package com.example.demo.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Video;
import com.example.demo.repository.UploadDetailsRepository;

@Service
public class DatabaseHandlerService {
	@Autowired
	UploadDetailsRepository repository;

	public void saveToDB(Video video) {
		repository.save(video);
		System.out.println("video saved");
	}

	public boolean checkAccess(int userId, String videoId) {

		boolean isAccess = false;
		Optional<Video> optional = repository.findById(videoId);
		Video video = null;
		if (optional != null) {
			video = optional.get();
		}
		if (video.getVideoType().equalsIgnoreCase("public")) {
			isAccess = true;
		} else {
			if (video.getUserId() == userId)
				isAccess = true;
		}
		return isAccess;
	}

	public boolean checkVideoExists(String videoId) {
		boolean isExists = false;
		Optional<Video> optional = repository.findById(videoId);
		if (optional.isPresent()) {
			isExists = true;
		} else
			isExists = false;
		return isExists;
	}

	public void deleteUser(int userId) throws IOException {
		// TODO Auto-generated method stub
		List<Video> video = repository.findByUserId(userId);
		File file = null;
		String basepath = "C:\\Users\\2380256\\git\\repository2\\VideoStreamingPlatform\\src\\main\\resources\\videos\\server\\";
		for(Video video1 : video) {
			System.out.println(video1.getVideoId());
			repository.deleteById(video1.getVideoId());
			System.out.println(basepath+video1.getVideoId());
			FileUtils.forceDelete(new File(basepath+video1.getVideoId()));
		}
	}
	
	public void deleteVideo(String videoId) throws IOException{
		String basepath = "C:\\Users\\2380256\\git\\repository2\\VideoStreamingPlatform\\src\\main\\resources\\videos\\server\\";
		FileUtils.forceDelete(new File(basepath+videoId));
	}

}
