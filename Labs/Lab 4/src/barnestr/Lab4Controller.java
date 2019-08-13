package barnestr;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

import java.net.URL;
import java.util.ResourceBundle;

public class Lab4Controller implements Initializable{

    @FXML
    private RadioMenuItem radioArrayListIndex;

    @FXML
    private RadioMenuItem radioLinkedListIndex;

    @FXML
    private RadioMenuItem radioArrayListFor;

    @FXML
    private RadioMenuItem radioLinkedListFor;

    @FXML
    private ToggleGroup strategy;

    @FXML
    private TextField textSearch;

    @FXML
    private TextArea textMatches;

    @FXML
    private Label labelTime;

    @FXML
    private Label labelMatches;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void close(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void load(ActionEvent event) {
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Dictionary File");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Dictionary Files","*txt", "*.csv"));

        }catch(Exception e) {

        }

    }
}
