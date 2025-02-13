package com.example.demo.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.dto.VideoDTO;
import com.example.demo.entity.Video;
import com.example.demo.repository.UploadDetailsRepository;

import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFmpegExecutor;
import net.bramp.ffmpeg.builder.FFmpegBuilder;

@Service
public class UploadRawVideoServiceImpl{

	@Autowired
	APIClient client;

	

	public String createDirectory(String videoId) {
		// TODO Auto-generated method stub
		String basePath = "C:\\Users\\2380256\\git\\repository2\\VideoStreamingPlatform\\src\\main\\resources\\videos\\server\\"
				+ videoId;

		boolean isBaseDirCreated = new File(basePath).mkdir();
		if (isBaseDirCreated) {

			String[] output_versions_folder = new String[5];

			output_versions_folder[0] = "//240p_output";
			output_versions_folder[1] = "//360p_output";
			output_versions_folder[2] = "//480p_output";
			output_versions_folder[3] = "//720p_output";
			output_versions_folder[4] = "//1080p_output";

			for (int i = 0; i < output_versions_folder.length; i++) {
				new File(basePath + output_versions_folder[i]).mkdir();
			}

			return "Directory created success";

		} else {
			return "Directory not created!! Upload Failed!!!";
		}
	}

	public String getUncompressedVideo(byte[] video) throws IOException {
		// TODO Auto-generated method stub

		String filePath = writeVideoIntoRaw(video);

		String filePath2 = "C:\\Users\\2380256\\git\\repository2\\VideoStreamingPlatform\\src\\main\\resources\\videos\\handleraw\\handler.mp4";
		String ffmpegPath = "C:\\ffmpeg\\ffmpeg.exe";

		FFmpeg fFmpeg = new FFmpeg(ffmpegPath);

		FFmpegBuilder builder = new FFmpegBuilder();

		builder.addInput(filePath).addOutput(filePath2);

		FFmpegExecutor executor = new FFmpegExecutor(fFmpeg);

		executor.createJob(builder).run();

		File file = new File(filePath);
		file.delete();

		return filePath2;
	}

	public String writeVideoIntoRaw(byte[] video) throws FileNotFoundException, IOException {
		String filepath = "C:\\Users\\2380256\\git\\repository2\\VideoStreamingPlatform\\src\\main\\resources\\videos\\handleraw\\handler.raw";
		try (FileOutputStream fileOutputStream = new FileOutputStream(filepath)) {
			fileOutputStream.write(video);
		}
		return filepath;
	}

	public String doesVideoEncodingTranscodingAndSegments(String filePathUCV, String videoId)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		System.out.println("inside raw doesVideoEncodingTranscodingAndSegments");
		String res = client.doesVideoETS(videoId);
		return res;
	}

	
}
