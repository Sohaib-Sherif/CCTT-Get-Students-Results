package ly.sohaib.seleniumCollegeID.Controllers;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.DialogPane;
import javafx.scene.control.MenuItem;

public class MenuWindowController {

    @FXML
    private MenuItem close;

    @FXML
    private MenuItem help;

    @FXML
    private MenuItem about;

	@FXML
	public void initialize() {
		close.setOnAction(event -> event.consume()/*Stage.close()*/);
		
		help.setOnAction(event -> {
			DialogPane pane = new DialogPane();
			pane.setContentText("blah blah");
		});
	}

}