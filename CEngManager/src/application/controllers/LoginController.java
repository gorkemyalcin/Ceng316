package application.controllers;

import javafx.event.ActionEvent;
import application.Main;
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

import java.util.Optional;

import helpers.*;

public class LoginController {
	
	@FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;
    
    @FXML
	private Label aboutUs;
	
    @FXML
    protected void handleLoginButtonAction(ActionEvent event) {
    	
    	String username = usernameField.getText().toString();
    	String password = passwordField.getText().toString();
    	
        Window owner = loginButton.getScene().getWindow();
        if(usernameField.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", 
                    "Please enter your username");
            return;
        }
      
        if(passwordField.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", 
                    "Please enter a password");
            return;
        }
        
        User loggedUser = ApplicationHelper.verifyUserCredentials(username, password);
        
        if (loggedUser == null) {
        	AlertHelper.showAlert(Alert.AlertType.WARNING, owner, "Credentials Error!", 
                    "The username or password is not correct!");
            return;
        }

        Optional<ButtonType> option = AlertHelper.continueAlert(Alert.AlertType.INFORMATION, owner, "Login Successful!", 
                "Very Warm Welcome " + usernameField.getText() + "!");
        if (!option.get().equals(ButtonType.OK)) {

        	try {
        		FXMLLoader fxmlloader;
        		if (loggedUser.getType().equals(UserType.ADMIN)) {
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
        } else 
        	System.out.println("No way");
    }
    
}
