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
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.stage.Window;
import repositories.CourseRepository;

public class DeleteCourseController {
	@FXML
    private TextField courseCode, courseName, courseStatus, courseInstructor, courseAssistant;
    
    @FXML
    private Label aboutUs, greetingMessage;
    
    @FXML
    private Button addInstructorButton, addAssistantButton, submitButton, previousButton;

    String code;

	
	
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
    private void handleSubmitButton(ActionEvent event) {
    	Window owner = submitButton.getScene().getWindow();
    	
    	if (courseCode.getText().isEmpty()) {
    		AlertHelper.showAlert(AlertType.ERROR, owner, "Form Error!", "Do not leave the course code field empty!");
    		return;
    	}
    	code = courseCode.getText().toString();
    	if (doesCourseExist(code)) {
    		controllers.CourseController controller = new controllers.CourseController();
    		
    		if (controller.deleteCourse(code)) {
    			AlertHelper.showAlert(AlertType.INFORMATION, owner, "Deletion Success", "The course with the code "
        				+ courseCode.getText().toString() + " has been deleted!");
        		return;
    		} else {
    			AlertHelper.showAlert(AlertType.ERROR, owner, "Deletion Failure", "Error deleting a file. Try again later!");
        		return;
    		}
    	    		
    	} else {
    		AlertHelper.showAlert(AlertType.ERROR, owner, "404 Not Found", "There is no course with the code" 
    				+ courseCode.getText().toString() + "!");
    		return;
    	}
    }
    
    
    private boolean doesCourseExist(String code) {
    	CourseRepository repo = new CourseRepository();
    	if (repo.selectCourse(code) != null)
    		return true;
    	return false;
    }
   

}
