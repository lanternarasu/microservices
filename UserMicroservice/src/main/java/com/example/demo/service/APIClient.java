package com.example.demo.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "UPLOADRAWVIDEOMICROSERVICE")
public interface APIClient {
	@DeleteMapping("/uploadraw/deleteUser/{uid}")
	public void deleteUser(@PathVariable("uid")int userId);
}
