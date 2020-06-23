package application.controllers;

import java.util.Optional;

import application.Main;
import controllers.UserController;
import helpers.AlertHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import models.User;
import models.UserType;

public class NewUserController {
	
	@FXML
	private Button newuserButton, previousButton;

	@FXML
	private Label greetingMessage, aboutUs;
	
	@FXML
	private TextField newUserName;
	@FXML
	private PasswordField newUserPassword;
	
	@FXML
	private void handleNewUserButton(ActionEvent event) {
		Window owner = newuserButton.getScene().getWindow();
		if (newUserName.getText().isEmpty()) {
			AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", 
                    "Please enter a username for the new user!");
			return;
		}
		
		if (newUserPassword.getText().isEmpty()) {
			AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", 
                    "Please enter a passwor for the new user!");
			return;
		}
		String username = newUserName.getText().toString();
		String password = newUserPassword.getText().toString();{
			
		}
		
		if (isUserAlreadyExist(username)) {
			AlertHelper.showAlert(Alert.AlertType.WARNING, owner, "Form", 
                    username + " already exist in the system!");
			return;
		}
		
		User newUser = new User(1,username, password, UserType.CM);
		UserController userController = new UserController();
		boolean success = userController.addNewContentManager(newUser);
		if (success) {
			AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Success!", 
                    username + " has been added to the system!");
			return;
		} else {
			AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error!", 
                    "An error occurred while adding a new user. Try again later");
			return;
		}
	} 
	
	@FXML
	private void handlePreviousButton(ActionEvent event) {
		Window owner = previousButton.getScene().getWindow();
		Optional<ButtonType> option = AlertHelper.continueAlert(Alert.AlertType.INFORMATION, owner, "Admin Menu", "Are you sure you want to go back?");

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
	
	private boolean isUserAlreadyExist(String username) {
		repositories.UserRepository repo = new repositories.UserRepository();
		if (repo.selectUser(username) != null)
			return true;
		return false;
	}
	
}
