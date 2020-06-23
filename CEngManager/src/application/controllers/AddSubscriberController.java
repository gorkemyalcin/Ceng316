package application.controllers;

import java.util.Optional;

import controllers.SubscriberController;
import helpers.AlertHelper;
import helpers.ApplicationHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Window;
import models.Category;
import models.Subscriber;

public class AddSubscriberController {
	@FXML
	private Label greetingMessage, aboutUs;
	
	@FXML
	private TextField subscriberNameField, subscriberEmailField, subscriberCategoryField;
	
	@FXML
	private Button submitButton, previousButton;
		
	@FXML
	private void handlePreviousButton(ActionEvent event) {
		Window owner = previousButton.getScene().getWindow();
		Optional<ButtonType> option = AlertHelper.continueAlert(Alert.AlertType.INFORMATION, owner, "Subscriber View", "Are you sure you want to go back?");

		if (!option.get().equals(ButtonType.OK)) {

        	String page = "Subscriber.fxml";
        	
        	ApplicationHelper.goBack(page);
        } else {
        	System.out.println("No way");
        }
	}
	
	@FXML
	private void handleSubmitButton(ActionEvent event) {

		Window owner = submitButton.getScene().getWindow();
		
		if (subscriberNameField.getText().isEmpty()) {
			AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Subscriber Form Error!", 
                    "Please enter a name for the new subscriber!");
			return;
		}
		
		if (subscriberEmailField.getText().isEmpty()) {
			AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Subscriber Form Error!", 
                    "Please enter an email for the new subscriber!");
			return;
		}
		
		if (subscriberCategoryField.getText().isEmpty()) {
			AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Subscriber Form Error!", 
                    "Please enter a category for the new subscriber!");
			return;
		}
		
		SubscriberController controller = new SubscriberController();
		String name = subscriberNameField.getText().toString();
		String email = subscriberEmailField.getText().toString();
		
		Subscriber subscriber = new Subscriber(name, email, Category.ASSISTANT);
		if (controller.addSubscriber(subscriber)) {
			AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Successful Edition", name + "was successfully added as a subscriber!");
		} else {
			AlertHelper.showAlert(Alert.AlertType.WARNING, owner, "Failure Editing", "Error adding a subscriber! Try again later");
		}
	}
}
