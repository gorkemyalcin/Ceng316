package application.controllers;

import java.util.Optional;

import application.Main;
import helpers.AlertHelper;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.Window;

public class AdminController {
	
	@FXML
	private Label aboutUs, greetingMessage;
	
	private String loggedUsername;
	
	@FXML
	private Button signOutButton, courseButton, eventButton, subscriberButton,  newUserButton;
	
	@FXML
	private void handleSignOutButton(ActionEvent event) {
		Window owner = signOutButton.getScene().getWindow();
		Optional<ButtonType> option = AlertHelper.continueAlert(Alert.AlertType.INFORMATION, owner, "Admin Menu", "Click on \"continue\" to securely log out!");

		if (!option.get().equals(ButtonType.OK)) {
			option = AlertHelper.continueAlert(Alert.AlertType.INFORMATION, owner, "Exit", "You're sucrely logged out! It was nice seeing you " + loggedUsername + ", Have fun!");
        	Platform.exit();
        } else {
        	System.out.println("No way");
        }
	}
	
	@FXML
	private void handleCourseButton(ActionEvent event) {
		Window owner = courseButton.getScene().getWindow();
		Optional<ButtonType> option = AlertHelper.continueAlert(Alert.AlertType.INFORMATION, owner, "Admin Menu", "You are about to enter to Course Management!");

		 if (!option.get().equals(ButtonType.OK)) {

	        	try {
	        		FXMLLoader fxmlloader;
	            	fxmlloader = new FXMLLoader(Main.class.getResource("/application/Course.fxml"));
	        		
	        		
	        		Parent root1 = (Parent) fxmlloader.load();
	        		Stage stage = new Stage();
	        		stage.setTitle("IZTECH Ceng Manager");
	        		Scene scene = new Scene(root1);
	                scene.getStylesheets().add(Main.class.getResource("assets/application.css").toExternalForm());
	        		stage.setScene(scene);
	        		stage.show();
	        		
	        	} catch (Exception e) {
	        		
	        	}
	        } else {
	        	System.out.println("No way");
	        }
	}
	
	@FXML
	private void handleEventButton(ActionEvent event) {
		Window owner = eventButton.getScene().getWindow();
		Optional<ButtonType> option = AlertHelper.continueAlert(Alert.AlertType.INFORMATION, owner, "Admin Menu", "You are about to enter to Event Management!");

		if (!option.get().equals(ButtonType.OK)) {

        	try {
        		FXMLLoader fxmlloader;
            	fxmlloader = new FXMLLoader(Main.class.getResource("/application/Event.fxml"));
        		
        		
        		Parent root1 = (Parent) fxmlloader.load();
        		Stage stage = new Stage();
        		stage.setTitle("IZTECH Ceng Manager");
        		Scene scene = new Scene(root1);
                scene.getStylesheets().add(Main.class.getResource("assets/application.css").toExternalForm());
        		stage.setScene(scene);
        		stage.show();
        		
        	} catch (Exception e) {
        		
        	}
        } else {
        	System.out.println("No way");
        }
	}
	
	@FXML
	private void handleSubscriberButton(ActionEvent event) {
		Window owner = subscriberButton.getScene().getWindow();
		Optional<ButtonType> option = AlertHelper.continueAlert(Alert.AlertType.INFORMATION, owner, "Admin Menu", "You are about to enter to Subscribers Management!");

		if (!option.get().equals(ButtonType.OK)) {

        	try {
        		FXMLLoader fxmlloader;
            	fxmlloader = new FXMLLoader(Main.class.getResource("/application/Subscriber.fxml"));
        		
        		
        		Parent root1 = (Parent) fxmlloader.load();
        		Stage stage = new Stage();
        		stage.setTitle("IZTECH Ceng Manager");
        		Scene scene = new Scene(root1);
                scene.getStylesheets().add(Main.class.getResource("assets/application.css").toExternalForm());
        		stage.setScene(scene);
        		stage.show();
        		
        	} catch (Exception e) {
        		
        	}
        } else {
        	System.out.println("No way");
        }
	}
	
	@FXML
	private void handleNewUserButton(ActionEvent event) {
		Window owner = newUserButton.getScene().getWindow();
		Optional<ButtonType> option = AlertHelper.continueAlert(Alert.AlertType.INFORMATION, owner, "Admin Menu", "You are about to add a new user to system!");

		if (!option.get().equals(ButtonType.OK)) {

        	try {
        		FXMLLoader fxmlloader;
            	fxmlloader = new FXMLLoader(Main.class.getResource("/application/NewUser.fxml"));
        		
        		
        		Parent root1 = (Parent) fxmlloader.load();
        		Stage stage = new Stage();
        		stage.setTitle("IZTECH Ceng Manager");
        		Scene scene = new Scene(root1);
                scene.getStylesheets().add(Main.class.getResource("assets/application.css").toExternalForm());
        		stage.setScene(scene);
        		stage.show();
        		
        	} catch (Exception e) {
        		
        	}
        } else {
        	System.out.println("No way");
        }

	}
	
	
	

}
