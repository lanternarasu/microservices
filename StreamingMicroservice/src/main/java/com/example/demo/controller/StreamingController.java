package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exceptions.VideoNotFoundException;
import com.example.demo.service.StreamingAccessService;
import com.example.demo.service.StreamingService;

@RestController
@RequestMapping("/stream")
public class StreamingController {
	
	@Autowired
	StreamingService streamingService;
	
	@Autowired
	StreamingAccessService accessService;
	
    private static final Logger logger = LoggerFactory.getLogger(StreamingController.class);
	
	@GetMapping("/streamVideo")
	public String streamVideo(@RequestParam int userId,@RequestParam String videoId) throws VideoNotFoundException
	{
		logger.info("in streaming controller" + videoId);
//		String[] funcArgs = {videoId};
//		StreamingService.launchApp(funcArgs);
//		return "Streaming";
		boolean doesVideoExists = accessService.checkIfVideoExists(videoId);
		boolean access = accessService.checkIfAllowedToStream(userId,videoId);
		
		if(access) 
		{
		String[] funcArgs = {videoId};
		StreamingService.launchApp(funcArgs);
		return "Streaming";
		}
		return "access denied";
	}

}
