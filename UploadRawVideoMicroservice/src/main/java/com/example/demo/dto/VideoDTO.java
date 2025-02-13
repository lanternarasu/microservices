package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoDTO {
	private String videoId;
	private String videoName;
	private String videoType;
	private String videoDescription;
	private int userId;
}
