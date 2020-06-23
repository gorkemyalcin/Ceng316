package application.controllers;

import java.util.Optional;

import application.Main;
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
import models.Category;
import models.Subscriber;
import controllers.SubscriberController;

public class EditSubscriberController {
	
	@FXML
	private Label greetingMessage, aboutUs;
	
	@FXML
	private TextField subscriberNameField, subscriberEmailField, subscriberCategoryField;
	
	@FXML
	private Button submitButton, previousButton;
	
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
		controller.addSubscriber(subscriber);
		
		AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Successful Edition", name + " as successfully editted!");
		
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
