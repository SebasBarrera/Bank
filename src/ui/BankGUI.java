package ui;

import java.io.IOException;

import customExceptions.AreadyAddedIdException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Controller;
import model.Person;

public class BankGUI {
	private Controller control;
	private Stage extraStage;
	@FXML
	private AnchorPane clientInfo;
	@FXML
	private Label clientOptionsInfo;
	@FXML
    private TableView<Person> usersTable;
    @FXML
    private TableColumn<Person, String> nameColumn;
    @FXML
    private TableColumn<Person, String> idColumn;
    @FXML
    private TableColumn<Person, String> timeColumn;
    @FXML
    private TableColumn<Person, String> amountColumn;
    @FXML
    private Button closeScreensButton;
    @FXML
    private TextField nameField;
    @FXML
    private TextField idField;
    @FXML
    private Label row1;
    @FXML
    private Label row2;
	
	public BankGUI() {
		try {
			control = new Controller();
		} catch (AreadyAddedIdException e) {
			e.getMessage();
		}
		initialize();
	}
	
	@FXML
	void searchClientNShowUpdateOptions(ActionEvent event) throws IOException {
		showInfoScreen();
	}
	
	void showInfoScreen() throws IOException {
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
	
	@FXML
	void accountIsBeingCanceledAlert(ActionEvent event) {
		Alert cancelAcc = new Alert(AlertType.WARNING);
		cancelAcc.setTitle("U are trying to cancel an account");
		cancelAcc.setHeaderText("ARE U SURE U WANT TO CANCEL THIS ACCOUNT?");
		cancelAcc.showAndWait();
		
//		if(cancelAcc.isShowing()) {   tengo que verificar si cancela o acepta 
//			TODO cancelar la cuenta
//		}
	}
	
	@FXML
	void closeScreen(ActionEvent event) {
		extraStage.close();
	}
	
	@FXML
	void withdrawOption(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("WithdrawScreen.fxml"));
		fxmlLoader.setController(this);
		Parent withdraw = fxmlLoader.load();
    	
		Scene scene = new Scene(withdraw);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setTitle("Amount to withdraw");
		extraStage = stage;
		stage.show();
	}
	
	@FXML
	void depositOption(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DepositScreen.fxml"));
		fxmlLoader.setController(this);
		Parent withdraw = fxmlLoader.load();
    	
		Scene scene = new Scene(withdraw);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setTitle("Amount to deposit");
		extraStage = stage;
		stage.show();
	}
	
	@FXML
	void signInAction(ActionEvent event) {
//		TODO
	}
	
	private void initialize() {
		// TODO Auto-generated method stub
		
	}
}
