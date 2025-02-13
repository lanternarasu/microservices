package com.example.demo.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.VideoEncodingTranscodingSegmentCreationService;

@RestController
@RequestMapping("/videoETSController")
public class VideoEncodingTranscodingSegmentCreationController {

	@Autowired
	VideoEncodingTranscodingSegmentCreationService creationService;

	@PostMapping("/doesVideoETS/{vid}")
	public String doesVideoEncodingTranscodingAndSegments(@PathVariable("vid") String videoId)
			throws IOException, InterruptedException {
		System.out.println("inside VideoEncodingTranscodingSegmentCreationController");
		creationService.doesVideoEncodingTranscodingAndSegments(videoId);
		creationService.createMasterPlaylist(videoId);
		return "done here";
	}
}
