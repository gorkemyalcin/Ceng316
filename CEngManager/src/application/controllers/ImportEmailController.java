package application.controllers;

import java.io.File;

import controllers.EmailController;
import helpers.AlertHelper;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ImportEmailController {


  
  public void start(Stage primaryStage) {
	   primaryStage.setTitle("IZTECH Ceng Manager");

       FileChooser fileChooser = new FileChooser();

       fileChooser.getExtensionFilters().addAll(
    		    new FileChooser.ExtensionFilter("CSV Files", "*.csv")
    		);
       Button button = new Button("Click Here");
       button.setOnAction(e -> {
           File selectedFile = fileChooser.showOpenDialog(primaryStage);
           EmailController controller = new EmailController();
           controller.saveEmails(selectedFile);
           
           if (selectedFile.exists()) {
               AlertHelper.continueAlert(Alert.AlertType.INFORMATION, button.getScene().getWindow(), "Confirmation", "The file was successfully imported");
           } else {
               AlertHelper.continueAlert(Alert.AlertType.ERROR, button.getScene().getWindow(), "Failure", "Error importing the emails file.");
           }
       });
       
     
       Label label = new Label("Only .csv extended files are allowed!"); 


       VBox vBox = new VBox(button, label);
       Scene scene = new Scene(vBox, 760, 500);
       scene.getStylesheets().add(
               this.getClass().getResource("/application/assets/file.css").toExternalForm()
       );
       
       primaryStage.setScene(scene);
       primaryStage.show();
  }
}