package ui;

import java.io.IOException;
import java.util.Optional;

import customExceptions.AreadyAddedIdException;
import customExceptions.SmallerKeyException;
import customExceptions.UserIsNotRegiterException;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Controller;
import model.Person;

public class BankGUI {
	private Controller control;
	private Stage extraStage;
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
    private ListView<String> row1;
    @FXML
    private ListView<String> row2;
    @FXML
    private TextField addName;
    @FXML
    private TextField addId;
    @FXML
    private TextField addAge;
    @FXML
    private CheckBox addDisabled;
    @FXML
    private CheckBox addPregnant;
    @FXML
    private RadioButton maleAdd;
    @FXML
    private RadioButton femaleAdd;
	
	public BankGUI() {
		try {
			control = new Controller();
		} catch (AreadyAddedIdException e) {
			e.getMessage();
		}
		initialize();
		
	}
	
	@FXML
	void attendClient(MouseEvent event) throws IOException {
		showInfoScreen();
	}
	
	void showInfoScreen() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("OptionsInfo.fxml"));
		fxmlLoader.setController(this);
		Parent infoPane = fxmlLoader.load();

		Scene scene = new Scene(infoPane);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setTitle("Update client");
		extraStage = stage;
		stage.show();
	}
	
	@FXML
	void accountIsBeingCanceledAlert(ActionEvent event) {
		Alert cancelAcc = new Alert(AlertType.CONFIRMATION);
		cancelAcc.setTitle("U are trying to cancel an account");
		cancelAcc.setHeaderText("ARE U SURE U WANT TO CANCEL THIS ACCOUNT?");
		
		Optional<ButtonType> result = cancelAcc.showAndWait();
		if(result.get() == ButtonType.OK) {
		     //TODO hay que cancelar la cuenta
			Alert accCanceled = new Alert(AlertType.INFORMATION);
			accCanceled.setTitle("Account canceled");
			accCanceled.setHeaderText("The choosen account has been canceled");
			accCanceled.showAndWait();
		}else {
			Alert accCanceled = new Alert(AlertType.INFORMATION);
			accCanceled.setTitle("Account was not canceled");
			accCanceled.setHeaderText("The choosen account has not been canceled");
			accCanceled.showAndWait();
		}
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
	void signInAction(ActionEvent event) throws AreadyAddedIdException, IOException {
		try {
			String idS = idField.getText();
			int id = Integer.parseInt(idS);
			String name = nameField.getText();
			control.addToRow(id, name);
			
			if (control.getPriority(control.findUser(id, name)) == 0) {
				row1.getItems().add(name);
			}else {
				row2.getItems().add(name);
			}
		} catch (SmallerKeyException e) {
			e.getMessage();
		} catch (UserIsNotRegiterException e) {
			Alert addAcc = new Alert(AlertType.INFORMATION);
			addAcc.setTitle("User is not register");
			addAcc.setHeaderText("User is not register");
			addAcc.setContentText("Push accept button to add him or close this window to cancel.");
			
			Optional<ButtonType> result = addAcc.showAndWait();
			if(result.get() == ButtonType.OK) {
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddScreen.fxml"));
				fxmlLoader.setController(this);
				Parent add = fxmlLoader.load();			    	
				Scene scene = new Scene(add);
				Stage stage = new Stage();
				stage.setScene(scene);
				stage.setTitle("Form to add");
				extraStage = stage;
				stage.show();
				addPregnant.setDisable(true);
			}
			
		} catch (NumberFormatException e) {
			Alert error = new Alert(AlertType.ERROR);
			error.setTitle("Error");
			error.setHeaderText("Make sure that in \"Citizenship id\" field are no characters but numbers");
			error.showAndWait();
			
		}
	}
	
	@FXML
	void addUser(ActionEvent event) throws AreadyAddedIdException {
		try {
			String name = addName.getText();
			String idS = addId.getText();
			int id = Integer.parseInt(idS);
			String ageS = addAge.getText();
			int age = Integer.parseInt(ageS);
			boolean disabled = addDisabled.isSelected();
			boolean pregnant = addPregnant.isSelected();
			int gender = 3;
			if (maleAdd.isSelected()) {
				gender = 1;
			}else if(femaleAdd.isSelected()){
				gender = 0;
			}
			
			if (gender == 3 || name.equals("")) {
				Alert error = new Alert(AlertType.ERROR);
				error.setTitle("Fields are empty");
				error.setHeaderText("Some fields are empty");
				error.setContentText("Please fill the required fields");
				error.showAndWait();
			}
			
			control.addPerson(name, 
					id, 
					age, 
					disabled,
					gender, 
					pregnant);
			
			Alert succes = new Alert(AlertType.INFORMATION);
			succes.setTitle("Saves");
			succes.setHeaderText("User added successfully");
			succes.showAndWait();
			
			extraStage.close();
		} catch (NumberFormatException e) {
			Alert error = new Alert(AlertType.ERROR);
			error.setTitle("Error");
			error.setHeaderText("Make sure that in \"Citizenship id\" and \"Age\" fields are no characters but numbers");
			error.showAndWait();
		}
	}
	
	@FXML
	void changePregnantStatusM(ActionEvent e) {
		if (maleAdd.isSelected()) {
			addPregnant.setDisable(true);
			addPregnant.setSelected(false);
		}
	}
	
	@FXML
	void changePregnantStatusF(ActionEvent e) {
		if (femaleAdd.isSelected()) {
			addPregnant.setDisable(false);
		}
	}
	
	private void initialize() {
		
		
	}
}
