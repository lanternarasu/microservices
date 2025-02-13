package com.example.demo.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Video;
import com.example.demo.exceptions.VideoNotFoundException;
import com.example.demo.service.DatabaseHandlerService;
import com.example.demo.service.UploadRawVideoService;
import com.example.demo.service.UploadRawVideoServiceImpl;

@RestController
@RequestMapping("/uploadraw")
public class UploadRawVideoController {

	@Autowired
	UploadRawVideoServiceImpl serviceImpl;
	
	@Autowired
	DatabaseHandlerService handlerService;
	
    private static final Logger logger = LoggerFactory.getLogger(UploadRawVideoController.class);

	@PostMapping("/uploadRawVideo")
	public String uploadRawVideo(@RequestParam("file") MultipartFile multipartFile, @RequestParam int userId,
			@RequestParam String videoId, @RequestParam String videoName, @RequestParam String videoDescription,
			@RequestParam String videoType) {
		Video video = new Video();
		video.setVideoId(videoId);
		video.setVideoName(videoName);
		video.setVideoDescription(videoDescription);
		video.setVideoType(videoType);
		video.setUserId(userId);
		if (!multipartFile.isEmpty()) {
			try {
				byte[] bytes = multipartFile.getBytes();
				serviceImpl.createDirectory(videoId);
				String filePathUCV = serviceImpl.getUncompressedVideo(bytes);
				logger.info("UCV done :"+filePathUCV);
				handlerService.saveToDB(video);
				String str = serviceImpl.doesVideoEncodingTranscodingAndSegments(filePathUCV, videoId);
				return "File and metadata uploaded successfully!";
			} catch (Exception e) {
				return "Video Transcoding going on";
			}
		} else {
			return "File is empty!";
		}
	}
	
	@GetMapping("/checkAccess/{uid}/{vid}")
	public boolean checkAccess(@PathVariable("uid") int userId,@PathVariable("vid") String videoId ) throws VideoNotFoundException {
		boolean isAccess = handlerService.checkAccess(userId, videoId);
		return isAccess;
	}
	
	@GetMapping("/checkExists/{vid}") 
	boolean checkIfVideoExists(@PathVariable("vid") String videoId) {
		boolean isExists = handlerService.checkVideoExists(videoId);
		return isExists;
	}
	
	@DeleteMapping("/deleteUser/{uid}")
	public void deleteUser(@PathVariable("uid") int userId) throws IOException {
		handlerService.deleteUser(userId);
	}
	
	@DeleteMapping("/deleteVideo/{vid}")
	public void deleteVideo(@PathVariable("vid") String videoId) throws IOException{
		handlerService.deleteVideo(videoId);
	}
}
