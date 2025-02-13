package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Video;

@Repository
public interface UploadDetailsRepository extends JpaRepository<Video, String>{
	public List<Video> findByUserId(int userId); 
}
