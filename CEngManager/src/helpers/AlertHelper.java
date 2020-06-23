package helpers;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Window;

/**
 * 
 * @author Ben-Malik TCHAMALAM
 *
 */
public class AlertHelper {
	
	 public static Optional<ButtonType> showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
	        
		 Alert alert = new Alert(alertType);
	        alert.setTitle(title);
	        alert.setHeaderText(null);
	        alert.setContentText(message);
	        alert.initOwner(owner);
	        
	        return alert.showAndWait();
	 }
	 
	 public static Optional<ButtonType> continueAlert(Alert.AlertType alertType, Window owner, String title, String message) {
	        
		 Alert alert = new Alert(alertType);
	        alert.setTitle(title);
	        alert.setHeaderText(null);
	        alert.setContentText(message);
	        alert.initOwner(owner);
	        alert.getButtonTypes().clear();
	        
	        ButtonType button = new ButtonType("Continue");
	        alert.getButtonTypes().add(button);
	        
	        return alert.showAndWait();
	    }
	 
	 
	 
	 

}
