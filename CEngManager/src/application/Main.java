package application;
	
import java.io.IOException;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This is the main node at the which the application starts running.
 * 
 * @author Ben-Malik TCHAMALAM
 *
 */
public class Main extends Application {
	
	@SuppressWarnings("unused")
	private Stage primaryStage;
	
    @Override
    public void start(Stage primaryStage) throws IOException, SQLException {
        Parent root = FXMLLoader.load(Main.class.getResource("Home.fxml"));
        Scene scene = new Scene(root, 800, 700);
        scene.getStylesheets().add(Main.class.getResource("assets/application.css").toExternalForm());
        primaryStage.setScene(scene);

        primaryStage.setTitle("IZTECH Ceng Manager");
        
        primaryStage.show();
        database.BasicConnectionPool.getConnection();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
