package helpers;

import repositories.UserRepository;
import application.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.Category;
import models.User;
import models.UserType;

/**
 * 
 * @author Ben-Malik TCHAMALAM
 *
 */
public class ApplicationHelper {
	
	private UserRepository userRepository;
	
	public ApplicationHelper() {
		userRepository = new UserRepository();
	}
	
	public boolean doesUserExist(String username) {
		boolean flag = false;
		User user = userRepository.selectUser(username);
		flag = !user.equals(null);
		
		return flag;
	}
	
	public static User getCurrentUser() {
		return new User(1,"Ben-Malik", "BenimadimBen", UserType.ADMIN);
	}
	
	public static void goBack(String previousPage) {
		try {
    		FXMLLoader fxmlloader;
        	fxmlloader = new FXMLLoader(Main.class.getResource("/application/" + previousPage));
    		
    		
    		Parent root1 = (Parent) fxmlloader.load();
    		Stage stage = new Stage();
    		stage.setTitle("IZTECH Ceng Manager");
    		Scene scene = new Scene(root1);
            scene.getStylesheets().add(Main.class.getResource("assets/application.css").toExternalForm());
    		stage.setScene(scene);
    		stage.show();
    		
    	} catch (Exception e) {
    		
    	}
	}
	
	public static User verifyUserCredentials(String username, String password) {
//		User user = personRepository.fetchUser(username);
//		if (doesUserExist(username)) {
//			if (password.equals(user.getPassword())) {
//				return user;
//			}
//		}
		
		User user = new User(1,"Ben-Malik", "BenimadimBen", UserType.ADMIN);
		if (password.equals(user.getPassword()) && user.getUsername().equals(username)) 
			return user;
		return null;
	}
	
	/**
	 * 
	 * @param category
	 * @return
	 */
	public static Category categoryFinder(String category) {
		
		switch(category) {
		case "instructor":
			return Category.INSTRUCTOR;
		case "assistant":
			return Category.ASSISTANT;
		case "student":
			return Category.STUDENTS;
		case "first_grade":
			return Category.FIRST_GRADE;
		case "second_grade":
			return Category.SECOND_GRADE;
		case "third_grade":
			return Category.THIRD_GRADE;
		case "forth_grade":
			return Category.FORTH_GRADE;
		case "all":
			return Category.ALL;
		case "preparatory":
			return Category.PREPARATORY;
		default:
			return Category.OTHER;
		}
	}
	
}
