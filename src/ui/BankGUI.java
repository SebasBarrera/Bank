package ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import model.Controller;

public class BankGUI {
	private Controller control;
	@FXML
	private AnchorPane clientInfo;
	@FXML
	private Label clientOptionsInfo;
	
	public BankGUI() {
		control = new Controller();
		initialize();
	}
	
	@FXML
	void showInfoScreen(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("OptionsInfo.fxml"));

		fxmlLoader.setController(this);
		Parent infoPane = fxmlLoader.load();

		clientInfo.getChildren().clear();
		clientInfo.getChildren().add(infoPane);
	}
	
	@FXML
	void showPredeterminedInfoScreen(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PredeterminatedInfoScreen.fxml"));

		fxmlLoader.setController(this);
		Parent infoPane = fxmlLoader.load();

		clientInfo.getChildren().clear();
		clientInfo.getChildren().add(infoPane);
	}
	
	private void initialize() {
		// TODO Auto-generated method stub
		
	}
}
