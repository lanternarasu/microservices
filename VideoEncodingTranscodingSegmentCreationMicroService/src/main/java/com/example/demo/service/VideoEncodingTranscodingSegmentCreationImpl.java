package com.example.demo.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class VideoEncodingTranscodingSegmentCreationImpl implements VideoEncodingTranscodingSegmentCreationService {

	@Override
	public void doesVideoEncodingTranscodingAndSegments(String videoId)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub

		System.out.println("in doesVideoEncodingTranscodingAndSegments");

		String ffmpeg = "C:\\ffmpeg\\ffmpeg.exe";
		String input = "C:\\Users\\2380256\\git\\repository2\\VideoStreamingPlatform\\src\\main\\resources\\videos\\handleraw\\handler.mp4";
		String[] resolution = { "\"scale=426:240\"", "\"scale=640:360\"", "\"scale=854:480\"", "\"scale=1280:720\"",
				"\"scale=1920:1080\"" };
		String videoEncoder = "libx264";
		String audioEncoder = "aac";
		String compatibilityCheck = "-2";
		String segmentStartingNumber = "0";
		String segmentDuration = "10";
		String includeAllSegments = "0";
		String outputFormat = "hls";
		String baseOutputFile = "C:\\Users\\2380256\\git\\repository2\\VideoStreamingPlatform\\src\\main\\resources\\videos\\server\\";
		String[] outputFile = { baseOutputFile + videoId + "\\240p_output\\240p_output.m3u8",
				baseOutputFile + videoId + "\\360p_output\\360p_output.m3u8",
				baseOutputFile + videoId + "\\480p_output\\480p_output.m3u8",
				baseOutputFile + videoId + "\\720p_output\\720p_output.m3u8",
				baseOutputFile + videoId + "\\1080p_output\\1080p_output.m3u8" };

		List<List<String>> command = new ArrayList<>();

		for (int i = 0; i < 5; i++) {
			command.add(Arrays.asList(ffmpeg, "-i", input, "-vf", resolution[i], "-codec:v", videoEncoder, "-codec:a",
					audioEncoder, "-strict", compatibilityCheck, "-start_number", segmentStartingNumber, "-hls_time",
					segmentDuration, "-hls_list_size", includeAllSegments, "-f", outputFormat, outputFile[i]));
		}

		for (int i = 0; i < command.size(); i++) {
			System.out.println(command.get(i));
		}

		for (int i = 0; i < command.size(); i++) {
			ProcessBuilder builder = new ProcessBuilder(command.get(i));
			builder.redirectErrorStream(true); // Merge error stream with standard output
			Process process = builder.start();

			// Read the output from the command
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}

			// Read the error stream (important for debugging)
			BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
			while ((line = errorReader.readLine()) != null) {
				System.err.println(line);
			}

			int exitCode = process.waitFor();
			System.out.println("FFmpeg prcoess " + i + " exited with code: " + exitCode);

			if (i == 4) {

				File file = new File(input);
				file.delete();
			}
		}

	}

	@Override
	public void createMasterPlaylist(String videoId) throws IOException {
		// TODO Auto-generated method stub
		String basePath = "C:\\Users\\2380256\\git\\repository2\\VideoStreamingPlatform\\src\\main\\resources\\videos\\server\\";
		FileWriter writer = new FileWriter(basePath + videoId + "\\master.m3u8");
		writer.write("#EXTM3U\r\n" + "#EXT-X-STREAM-INF:BANDWIDTH=800000,RESOLUTION=426x240\r\n"
				+ "240p_output/240p_output.m3u8\r\n" + "#EXT-X-STREAM-INF:BANDWIDTH=1400000,RESOLUTION=640x360\r\n"
				+ "360p_output/360p_output.m3u8\r\n" + "#EXT-X-STREAM-INF:BANDWIDTH=2000000,RESOLUTION=854x480\r\n"
				+ "480p_output/480p_output.m3u8\r\n" + "#EXT-X-STREAM-INF:BANDWIDTH=2800000,RESOLUTION=1280x720\r\n"
				+ "720p_output/720p_output.m3u8\r\n" + "#EXT-X-STREAM-INF:BANDWIDTH=5000000,RESOLUTION=1920x1080\r\n"
				+ "1080p_output/1080p_output.m3u8\r\n");
		writer.close();
	}

}
