package application.controllers;

import java.util.Optional;

import application.Main;
import controllers.EmailController;
import controllers.SubscriberController;

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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

public class DeleteSubscriberController {
	
	@FXML
	private Button submitButton, previousButton;
	
	
	@FXML
	private Label greetingMessage, aboutUs;
	
	@FXML
	private TextField subscriberEmailField;
	
	@FXML
	private void handleSubmitButton(ActionEvent event) {
		EmailController controller = new EmailController();
		SubscriberController subController = new SubscriberController();
		Window owner = submitButton.getScene().getWindow();

		if (subscriberEmailField.getText().isEmpty()) {
			AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error", " Please enter a subscriber email!");
		}
		String email = subscriberEmailField.getText().toString();
		if (!controller.emailChecker(email)) {
			AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error", "Please enter a valid email!");
			return;
		}
		Optional<ButtonType> option = AlertHelper.continueAlert(Alert.AlertType.WARNING, owner, "Admin Menu", "Are you sure you want to delete " + email + "?");
		
		if (!option.get().equals(ButtonType.OK)) {
			if (subController.deleteSubscriber(email)) {
				AlertHelper.showAlert(Alert.AlertType.INFORMATION, owner, "Confirmation", email + " has been deleted!");
				return;
			}
			AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Deletion Failure", " An error occurred while deleting a subscriber");
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

}
