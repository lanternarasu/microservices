package com.example.demo.service;

import java.io.IOException;

public interface VideoEncodingTranscodingSegmentCreationService {
	 public void doesVideoEncodingTranscodingAndSegments(String videoId) throws IOException, InterruptedException;
	 public void createMasterPlaylist(String videoId) throws IOException;
}
