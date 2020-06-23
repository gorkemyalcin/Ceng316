package application.controllers;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import models.Course;
import repositories.CourseRepository;
import controllers.CourseController;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import application.Main;
import helpers.AlertHelper;
/**
 * 
 * @author Ben-Malik TCHAMALAM
 *
 */

public class EditCourseController implements Initializable{
	
	private List<String> instructorsList = new ArrayList<String>();
	private List<String> assistantsList = new ArrayList<String>();
	private String code;

	 	@FXML
	    private TextField courseCode, courseName, courseStatus, courseInstructor, courseAssistant;
	    
	    @FXML
	    private Label aboutUs, greetingMessage;
	    
	    @FXML
	    private ComboBox<String> status;
	    
	    private ObservableList<String> statusList = (ObservableList<String>) FXCollections.observableArrayList("Delivered", "Not Delivered");
	    
	    @FXML
	    private FXCollections collection;
	    
	    @FXML
	    private Button addInstructorButton, addAssistantButton, submitButton, previousButton;

	    @FXML
		private void handlePreviousButton(ActionEvent event) {
			Window owner = previousButton.getScene().getWindow();
			Optional<ButtonType> option = AlertHelper.continueAlert(Alert.AlertType.INFORMATION, owner, "Admin Menu", "Are you sure you want to go back?");

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
	    private void handleAddInstructorButton(ActionEvent event) {
	    	Window owner = addInstructorButton.getScene().getWindow();

	    	if (courseCode.getText().isEmpty()) {
		    	AlertHelper.showAlert(AlertType.ERROR, owner, "Course Management", "Do first fill the course code filed!");
		    	return;
	    	} 
	    	
	    	if (courseName.getText().isEmpty()) {
		    	AlertHelper.showAlert(AlertType.ERROR, owner, "Course Management", "Do first fill the course name field!");
		    	return;
	    	}
	    	
	    	if (courseStatus.getText().isEmpty()) {
		    	AlertHelper.showAlert(AlertType.ERROR, owner, "Course Management", "Do first fill the course status field!");
		    	return;
	    	}
	    	
	    	if(courseInstructor.getText().isEmpty()) {
	            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", 
	                    "Please do enter the course assistant");
	            return;
	        } else {
	        	AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Success", "The Instructor " +
	    		    	courseInstructor.getText().toString() + " has been added");
		    	instructorsList.add(courseAssistant.getText().toString());
		    	
	        }
	    }
	    
	    @FXML
	    private void handleAddAssistantButton(ActionEvent event) {
	        Window owner = addAssistantButton.getScene().getWindow();

	        if (courseCode.getText().isEmpty()) {
		    	AlertHelper.showAlert(AlertType.ERROR, owner, "Course Management", "Do first fill the course code filed!");
		    	return;
	    	} 
	    	
	    	if (courseName.getText().isEmpty()) {
		    	AlertHelper.showAlert(AlertType.ERROR, owner, "Course Management", "Do first fill the course name field!");
		    	return;
	    	}
	    	
	    	if (courseStatus.getText().isEmpty()) {
		    	AlertHelper.showAlert(AlertType.ERROR, owner, "Course Management", "Do first fill the course status field!");
		    	return;
	    	}
	    	
	    	if(courseAssistant.getText().isEmpty()) {
	            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", 
	                    "Please do enter the course assistant");
	            return;
	        } else 
		    	assistantsList.add(courseAssistant.getText().toString());
	    		AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Success", "The Assistant " +
			    	courseAssistant.getText().toString() + " has been added");
	    }
	    
	    private boolean delivery() {
	    	if (status.getSelectionModel().toString().equals("Delivered")) 
	    		return true;
	    	return false;
	    }
	    private boolean saveCourse() {
	    	CourseController courseController = new CourseController();
	    	Course course = new Course(courseCode.getText().toString(), courseName.getText().toString(), delivery(),
	    			instructorsList, assistantsList);
	    			
	    	return courseController.addCourse(course);
	    }
	    
	    @FXML
	    private void handleSubmitButton(ActionEvent event) {
	    	Window owner = submitButton.getScene().getWindow();
	    	
	    	if (courseCode.getText().isEmpty()) {
		    	AlertHelper.showAlert(AlertType.ERROR, owner, "Course Management", "Do not leave the course code field empty!");
		    	return;
	    	} 
	    	
	    	if (courseName.getText().isEmpty()) {
		    	AlertHelper.showAlert(AlertType.ERROR, owner, "Course Management", "Do not leave the course name field empty!");
		    	return;
	    	}
	    	
	    	if (status.getSelectionModel().isEmpty()) {
	    		AlertHelper.showAlert(AlertType.ERROR, owner, "Course Management", "Do not leave the course status field empty!");
		    	return;
	    	}
	    	
	    	code = courseCode.getText().toString();
	    
	    	if (doesCourseExist(code)) {
	    		if (saveCourse()) {
	    			AlertHelper.showAlert(AlertType.INFORMATION, owner, "Eddition Success", "The course with the code "
		    				+ courseCode.getText().toString() + " has been editted!");
		    		return;
	    		}
	    		AlertHelper.showAlert(AlertType.ERROR, owner, "Eddition Failure", "There was an error adding "
	    				+ courseCode.getText().toString() + ". Please try again later!");
	    		return;
	    	} else {
	    		AlertHelper.showAlert(AlertType.WARNING, owner, "404 Not Found", "The course with the code " + code + " does not exist!");
	    		return;
	    	}
	    }
	    
	    private boolean doesCourseExist(String code) {
	    	CourseRepository repo = new CourseRepository();
	    	if (repo.selectCourse(code) == null) {
	    		return false;
	    	}
	    	return true;
	    }

		@Override
		public void initialize(URL location, ResourceBundle resources) {
			status.setItems(statusList);
		}
}
