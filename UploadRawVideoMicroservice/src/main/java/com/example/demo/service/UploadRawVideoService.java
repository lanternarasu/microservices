package com.example.demo.service;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.http.ResponseEntity;

import com.example.demo.dto.VideoDTO;
import com.example.demo.entity.Video;

public interface UploadRawVideoService {
	 public String createDirectory(String videoId);
	 public String getUncompressedVideo(byte[] video) throws IOException;
	 public String writeVideoIntoRaw(byte[] video) throws FileNotFoundException, IOException;
	 public String doesVideoEncodingTranscodingAndSegments(String filePathUCV,String videoId) throws IOException, InterruptedException;
	 public void saveToDB(Video video);
}
