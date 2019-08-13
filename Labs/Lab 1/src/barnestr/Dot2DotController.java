/*
 * CS2852
 * Fall 2018
 * Lab 3 - Connect the Dots Generator Revisited
 * Name: Trevor Barnes
 * Created: 9/11/18
 */
package barnestr;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The controller that handles all of the UI components of the Dot to Dot window
 *
 * @author Trevoer Barnes
 * @version 3.0
 */
public class Dot2DotController implements Initializable {

    private Logger logger = Logger.getLogger("d2d");

    private Picture picture;

    private Picture original;
    @FXML
    private MenuItem linesMenuItem;

    @FXML
    private MenuItem dotsMenuItem;

    @FXML
    private RadioMenuItem radioLinkedList;

    @FXML
    private RadioMenuItem radioIndexOnly;

    @FXML
    private Label labelTime;

    @FXML
    private Canvas canvas = new Canvas();

    @FXML
    private TextField numDotsField;

    @FXML
    private Button buttonChange;


    @FXML
    void changeDotAmount(ActionEvent event) {
        long timeNano;
        try {
            if (radioIndexOnly.isSelected()) {
                timeNano = picture.removeDots(Integer.parseInt(numDotsField.getText()));
            } else {
                timeNano = picture.removeDots2(Integer.parseInt(numDotsField.getText()));
            }
            picture.clearCanvas(canvas);
            picture.drawDots(canvas);
            picture.drawLines(canvas);
            long millis = timeNano /1000000;
            long second = (millis / 1000) % 60;
            long minute = (millis / (1000 * 60)) % 60;
            long hour = (millis / (1000 * 60 * 60)) % 24;
            String time = String.format("%02d:%02d:%02d.%d", hour, minute, second, millis);
            labelTime.setText("Run Time: " + time);
            logger.log(Level.INFO, "Run Time for selected operation" + time);
        } catch (NumberFormatException e) {
            logger.log(Level.WARNING, "The input amount was not formatted correctly");
        } catch (NullPointerException e) {
            logger.log(Level.WARNING, "No picture is loaded to be changed");
        }
    }

    @FXML
    void close(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void dotsOnly(ActionEvent event) {
        picture.clearCanvas(canvas);
        picture.drawDots(canvas);
    }

    @FXML
    void linesOnly(ActionEvent event) {
        picture.clearCanvas(canvas);
        picture.drawLines(canvas);
    }

    @FXML
    void load(ActionEvent event) {
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Dot File");
            fileChooser.getExtensionFilters()
                    .add(new FileChooser.ExtensionFilter("Dot Files", "*.dot"));
            fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
            if (radioLinkedList.isSelected()) {
                original = new Picture(new LinkedList<>());
                picture = new Picture(original, new LinkedList<>());
            } else {
                original = new Picture(new ArrayList<>());
                picture = new Picture(original, new ArrayList<>());
            }

            picture.load(fileChooser.showOpenDialog(null));

            picture.clearCanvas(canvas);
            linesMenuItem.setDisable(false);
            dotsMenuItem.setDisable(false);
            buttonChange.setDisable(false);
            picture.drawDots(canvas);
            picture.drawLines(canvas);
        } catch (NullPointerException e) {
            logger.log(Level.INFO, "No file was selected to be opened");
        } catch (InputMismatchException e) {
            logger.log(Level.WARNING, "The input data from the file was not formatted correctly");
        } catch (NumberFormatException e) {
            logger.log(Level.WARNING,
                    "The number input data from the file was not formatted correctly");
        } catch (FileNotFoundException e) {
            logger.log(Level.WARNING, "No file was selected to be opened");
        }
        logger.log(Level.INFO, "File successfully loaded");
    }

    @FXML
    void save(ActionEvent event) {
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save Dot File");
            fileChooser.getExtensionFilters()
                    .add(new FileChooser.ExtensionFilter("Dot Files", "*.dot"));
            fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
            picture.save(fileChooser.showSaveDialog(null));
        } catch (FileNotFoundException e) {
            logger.log(Level.WARNING, "No file location was selected to be saved to");
        } catch (NullPointerException e) {
            logger.log(Level.WARNING, "No picture is loaded to be saved");
        }
        logger.log(Level.INFO, "File successfully saved");
    }


    /**
     * Initializes the logger file
     *
     * @param location  location URL
     * @param resources resources ResourceBundle
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            FileHandler handler = new FileHandler(System.getProperty("user.dir") +
                    File.separator + "d2d.txt");
            logger.addHandler(handler);
        } catch (IOException e) {
            logger.severe("Could not create log file");
        }
        logger.setUseParentHandlers(false);
    }

}
