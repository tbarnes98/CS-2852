/*
 * CS2852
 * Fall 2018
 * Lab 3 - Connect the Dots Generator Revisited
 * Name: Trevor Barnes
 * Created: 9/11/18
 */
package barnestr;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The main class for Lab 1. Launches the JavaFX Application
 *
 * @author Trevor Barnes
 * @version 1.0
 */
public class Dot2Dot extends Application {

    /**
     * Loads the Dot2DotController.fxml file with its controller.
     *
     * @param primaryStage the stage in which the application runs
     * @throws Exception if there is a problem launching the program
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Dot2DotController.fxml"));
        primaryStage.setTitle("Dot to Dot");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
