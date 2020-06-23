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

public class CourseController {
	
	private String loggedUsername;
	@FXML
	private Button editButton, addButton, deleteButton, signOutButton, previousButton;
	
	@FXML
	private Label aboutUs, greetingMessage;
	
	@FXML
	private void handleEditButton(ActionEvent event) {
		Window owner = editButton.getScene().getWindow();
		Optional<ButtonType> option = AlertHelper.continueAlert(Alert.AlertType.INFORMATION, owner, "Course View", "You are about to edit a course!");

		if (!option.get().equals(ButtonType.OK)) {

        	try {
        		FXMLLoader fxmlloader;
            	fxmlloader = new FXMLLoader(Main.class.getResource("/application/EditCourse.fxml"));
        		
        		
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
	private void handleAddButton(ActionEvent event) {
		Window owner = editButton.getScene().getWindow();
		Optional<ButtonType> option = AlertHelper.continueAlert(Alert.AlertType.INFORMATION, owner, "Course View", "You are about to add a new course to the system!");

		if (!option.get().equals(ButtonType.OK)) {

        	try {
        		FXMLLoader fxmlloader;
            	fxmlloader = new FXMLLoader(Main.class.getResource("/application/AddCourse.fxml"));
        		
        		
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
	private void handleDeleteButton(ActionEvent event) {
		Window owner = deleteButton.getScene().getWindow();
		Optional<ButtonType> option = AlertHelper.continueAlert(Alert.AlertType.INFORMATION, owner, "Course View", "You are about to add a new course to the system!");

		if (!option.get().equals(ButtonType.OK)) {

        	try {
        		FXMLLoader fxmlloader;
            	fxmlloader = new FXMLLoader(Main.class.getResource("/application/DeleteCourse.fxml"));
        		
        		
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
	private void handleSignOutButton(ActionEvent event) {
		Window owner = signOutButton.getScene().getWindow();
		Optional<ButtonType> option = AlertHelper.continueAlert(Alert.AlertType.INFORMATION, owner, "IZTECH Ceng Manager", "Click on \"continue\" to securely log out!");

		if (!option.get().equals(ButtonType.OK)) {
			option = AlertHelper.continueAlert(Alert.AlertType.INFORMATION, owner, "Exit", "You're sucrely logged out! It was nice seeing you " + loggedUsername + ", Have fun!");
        	Platform.exit();
        } else {
        	System.out.println("No way");
        }
	}
	
	@FXML
	private void handlePreviousButton(ActionEvent event) {
		Window owner = previousButton.getScene().getWindow();
		Optional<ButtonType> option = AlertHelper.continueAlert(Alert.AlertType.INFORMATION, owner, "Confirmation", "Are you sure you want to go back?");

		if (!option.get().equals(ButtonType.OK)) {

        	try {
        		FXMLLoader fxmlloader;
            	fxmlloader = new FXMLLoader(Main.class.getResource("/application/AdminMenu.fxml"));
        		
        		
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
