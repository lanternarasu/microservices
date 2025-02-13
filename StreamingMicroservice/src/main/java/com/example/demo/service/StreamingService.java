package com.example.demo.service;

import java.io.File;
import java.net.URI;
import java.net.URL;

import org.springframework.stereotype.Service;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

@Service
public class StreamingService extends Application{
	
	private String videoId;
	
    private static String[] args;
  
	
	public String getVideoId() {
		return videoId;
	}

	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}

	public static void launchApp(String[] funcArgs) 
    {
		args = funcArgs;
        Application.launch(funcArgs);
    }
     
    @Override
    public void start(Stage stage) 
    {
    	
        // Create a Media
    	String baseUrl = "http://localhost:8080//";
        Media media = new Media(baseUrl+args[0]+"//master.m3u8");
         
        // Create a Media Player
        final MediaPlayer player = new MediaPlayer(media);
        player.setOnError(() -> {
            System.out.println("Error: " + player.getError().getMessage());
        });
        // Automatically begin the playback
        player.setAutoPlay(true);
         
        // Create a 400X300 MediaView
        MediaView mediaView = new MediaView(player);
        double screenWidth = Screen.getPrimary().getBounds().getWidth();
        double screenHeight = Screen.getPrimary().getBounds().getHeight();

        // Set MediaView to screen size
        mediaView.setFitWidth(screenWidth);
        mediaView.setFitHeight(screenHeight);

        StackPane root = new StackPane(mediaView);
        Scene scene = new Scene(root, screenWidth, screenHeight);
        // Create the Scene
        // Add the scene to the Stage
        stage.setScene(scene);
        // Set the title of the Stage
        stage.setTitle("A simple Media Example");
        // Display the Stage
        stage.show();       
    }  
}
