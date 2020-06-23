package application.controllers;

import java.util.Optional;

import application.Main;
import helpers.AlertHelper;
import helpers.ApplicationHelper;
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
import models.User;
import models.UserType;

public class SubscriberController {
	
	private User currentUser = ApplicationHelper.getCurrentUser();

	@FXML
	private Label greetingMessage, aboutUs;
	
	@FXML
	private Button signOutButton, importEmailButton, addSubscriberButton,
				   editSubscriberButton, deleteSubscriberButton, previousButton
				   ;
	
	@FXML
	private void handleSignOutButton(ActionEvent event) {
		Window owner = signOutButton.getScene().getWindow();
		Optional<ButtonType> option = AlertHelper.continueAlert(Alert.AlertType.INFORMATION, owner, "Admin Menu", "Click on \"continue\" to securely log out!");

		if (!option.get().equals(ButtonType.OK)) {
			option = AlertHelper.continueAlert(Alert.AlertType.INFORMATION, owner, "Exit", "You're sucrely logged out! It was nice seeing you " + currentUser.getUsername() + ", Have fun!");
        	Platform.exit();
        } else {
        	System.out.println("No way");
        }
	}
	
	@FXML
	private void handleImportEmailButton(ActionEvent event) {
		Window owner = importEmailButton.getScene().getWindow();
		Optional<ButtonType> option = AlertHelper.continueAlert(Alert.AlertType.INFORMATION, owner, "Admin Menu", "You are about to import a \'.csv\' extended file of emails!");

		if (!option.get().equals(ButtonType.OK)) {
			Stage stage = new Stage();
			ImportEmailController controller = new ImportEmailController();
			controller.start(stage);
		}
	}
	
	@FXML
	private void handleAddSubscriberButton(ActionEvent event) {
		Window owner = addSubscriberButton.getScene().getWindow();
		Optional<ButtonType> option = AlertHelper.continueAlert(Alert.AlertType.INFORMATION, owner, "Admin Menu", "You are about to add a new subscriber to the system!");

		if (!option.get().equals(ButtonType.OK)) {

        	try {
        		FXMLLoader fxmlloader;
            	fxmlloader = new FXMLLoader(Main.class.getResource("/application/AddSubscriber.fxml"));
        		
        		
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
	private void handleEditSubscriberButton(ActionEvent event) {
		Window owner = editSubscriberButton.getScene().getWindow();
		Optional<ButtonType> option = AlertHelper.continueAlert(Alert.AlertType.INFORMATION, owner, "Admin Menu", "You are about to edit a subscriber from the system!");

		if (!option.get().equals(ButtonType.OK)) {

        	try {
        		FXMLLoader fxmlloader;
            	fxmlloader = new FXMLLoader(Main.class.getResource("/application/EditSubscriber.fxml"));
        		
        		
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
	private void handleDeleteSubscriberButton(ActionEvent event) {
		Window owner = deleteSubscriberButton.getScene().getWindow();
		Optional<ButtonType> option = AlertHelper.continueAlert(Alert.AlertType.INFORMATION, owner, "Admin Menu", "You are about to delete a subscriber from the system!");

		if (!option.get().equals(ButtonType.OK)) {

        	try {
        		FXMLLoader fxmlloader;
            	fxmlloader = new FXMLLoader(Main.class.getResource("/application/DeleteSubscriber.fxml"));
        		
        		
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
	private void handlePreviousButton(ActionEvent event) {
		Window owner = previousButton.getScene().getWindow();
		Optional<ButtonType> option = AlertHelper.continueAlert(Alert.AlertType.INFORMATION, owner, "Admin Menu", "You are about to add a new user to system!");

		if (!option.get().equals(ButtonType.OK)) {

        	try {
        		FXMLLoader fxmlloader;
        		if (currentUser.getType().equals(UserType.ADMIN)) {
                	fxmlloader = new FXMLLoader(Main.class.getResource("/application/AdminMenu.fxml"));
        		} else {
                	fxmlloader = new FXMLLoader(Main.class.getResource("/application/CmMenu.fxml"));
        		}        		
        		
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
