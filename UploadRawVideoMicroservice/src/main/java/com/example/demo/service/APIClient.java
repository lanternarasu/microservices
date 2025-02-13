package com.example.demo.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="VIDEOENCODINGTRANSCODINGSEGMENTCREATIONMICROSERVICE")
public interface APIClient {
	
	@PostMapping("videoETSController/doesVideoETS/{vid}")
	String doesVideoETS(@PathVariable("vid") String videoId);
}
