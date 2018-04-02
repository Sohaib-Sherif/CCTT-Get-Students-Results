package ly.sohaib.seleniumCollegeID.Controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

public class MainAppController{
	@FXML
	private Button start;

	@FXML
	private Button stop;

	@FXML
	private Button setPath;

	@FXML
	private Button setDatabase;

	@FXML
	private ComboBox<Integer> from;

	@FXML
	private ComboBox<Integer> To;

	@FXML
	private ChoiceBox<Integer> groupID;

	@FXML
	private Label databaseLED;

	@FXML
	private Label pathOfFile;

	@FXML
	private ProgressBar progress;

	@FXML
	private Label currentID;

	@FXML
	private Label message;

	@FXML
	public void initialize() {
		groupID.setItems(FXCollections.observableArrayList(131,132,141,142,151,152,161,162,171,172));

	}

}
