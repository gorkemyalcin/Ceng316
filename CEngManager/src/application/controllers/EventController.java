package application.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import application.Main;
import helpers.AlertHelper;
import helpers.ApplicationHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import javafx.stage.Window;
import models.Category;

public class EventController {

	@FXML
	private TextField eventTitle;
	
	@FXML
	private ToggleGroup subscriberType, subscriberType2;
	
	@FXML
	private RadioButton all, instructors, assistants, first, second, third, forth;
	
	@FXML
	private Button previousButton, emailEventButton;
	
	@FXML
	private Label greetingMessage, aboutUs;
	
	
	
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
	
	@FXML
	private void handleEmailEventButton(ActionEvent event) {
		Window owner = emailEventButton.getScene().getWindow();
		
		if (eventTitle.getText().isEmpty()) {
			AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Event Management", "Event Title should not be left empty!");
			return;
		}
		
		
		//RadioButton option2 = (RadioButton) subscriberType2.getSelectedToggle();
		List<Category> categories = new ArrayList<>();
		categories.add(Category.THIRD_GRADE);
		
		String title = eventTitle.getText().toString();
		List<String> titles = new ArrayList<>();
		titles.add(title);

		controllers.EventController controller = new controllers.EventController();
		controller.sendEventsToSubscribers(categories, title);

			AlertHelper.continueAlert(Alert.AlertType.INFORMATION, owner, "Event Management", "Event emailed Successfully!");
			return;
		
	}
	
}
