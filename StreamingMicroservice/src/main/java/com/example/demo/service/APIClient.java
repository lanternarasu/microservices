package com.example.demo.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.exceptions.VideoNotFoundException;

@FeignClient(name="UPLOADRAWVIDEOMICROSERVICE")
public interface APIClient {
	
	@GetMapping("uploadraw/checkAccess/{uid}/{vid}")
	boolean checkAccess(@PathVariable("uid") int userId,@PathVariable("vid") String videoId);

	@GetMapping("uploadraw/checkExists/{vid}")
	boolean checkIfVideoExists(@PathVariable("vid") String videoId);
}
