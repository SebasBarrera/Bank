package ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import customExceptions.ActionsOnInactiveException;
import customExceptions.AlreadyInactiveException;
import customExceptions.AlreadyPaidException;
import customExceptions.AreadyAddedIdException;
import customExceptions.HeapUnderFlowException;
import customExceptions.NormalRowIsEmptyException;
import customExceptions.NotEnoughtMoneyException;
import customExceptions.NotFoundCardException;
import customExceptions.NothingToRedoException;
import customExceptions.NothingToUndoException;
import customExceptions.PriorityRowIsEmptyException;
import customExceptions.SmallerKeyException;
import customExceptions.UserIsNotRegiterException;
import javafx.collections.FXCollections;
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
import javafx.scene.control.cell.PropertyValueFactory;
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
	private Person attending;
	@FXML
	private AnchorPane attendScreen;
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
    @FXML
    private Label clientsNameLbl;
    @FXML
    private TextField withdrawValue;
    @FXML
    private TextField depositValue;
    @FXML
    private TextField why;
    @FXML
    private TextField cardNumber;
    @FXML
    private RadioButton entireDebt;
    @FXML
    private RadioButton quota;
    @FXML
    private RadioButton savingAccount;
    @FXML
    private RadioButton cash;
    @FXML
    private TextField debtField;
    @FXML
    private TextField quotasField;
    @FXML
    private TextField fitField;
    @FXML
    private TextField feeField;
    @FXML
    private TextField paymentField;
	
	public BankGUI() {
		try {
			control = new Controller();
		} catch (AreadyAddedIdException e) {
			e.getMessage();
		}
	}
	
	@FXML
	void attendClient(MouseEvent event) throws IOException {
		try {
			attending = control.getNextInNormalRow();
			control.peekInNormalQ();
			row1.getItems().remove(attending.getName());
			showInfoScreen();
		} catch (NormalRowIsEmptyException e) {
			Alert accCanceled = new Alert(AlertType.INFORMATION);
			accCanceled.setTitle("Row empty");
			accCanceled.setHeaderText("There is no more clients waiting in the row");
			accCanceled.showAndWait();
		}
	}
	
	@FXML
	void attendClientPriority(MouseEvent event) throws IOException {
		try {
			attending = control.getNextInPriotityRow();
			control.extractInPriorityQ();
			row2.getItems().remove(attending.getName());
			showInfoScreen();
		} catch (HeapUnderFlowException e) {
			e.printStackTrace();
		} catch (PriorityRowIsEmptyException e) {
			Alert accCanceled = new Alert(AlertType.INFORMATION);
			accCanceled.setTitle("Row empty");
			accCanceled.setHeaderText("There is no more clients waiting in the row");
			accCanceled.showAndWait();
		}				
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
		clientsNameLbl.setText(attending.getName());
	}
	
	void accountIsBeingCanceledAlert() {
		Alert cancelAcc = new Alert(AlertType.CONFIRMATION);
		cancelAcc.setTitle("U are trying to cancel an account");
		cancelAcc.setHeaderText("ARE U SURE U WANT TO CANCEL THIS ACCOUNT?");
		
		Optional<ButtonType> result = cancelAcc.showAndWait();
		if(result.get() == ButtonType.OK) {
	    	Alert accCanceled = new Alert(AlertType.INFORMATION);
			accCanceled.setTitle("Account canceled");
			accCanceled.setHeaderText("The choosen account has been canceled");
			accCanceled.showAndWait();
			extraStage.close();
		}else {
			Alert accCanceled = new Alert(AlertType.INFORMATION);
			accCanceled.setTitle("Account was not canceled");
			accCanceled.setHeaderText("The choosen account has not been canceled");
			accCanceled.showAndWait();
			extraStage.close();
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
	void withdrawAction(ActionEvent event) {
		try {
			int value = Integer.parseInt(withdrawValue.getText());
			control.withdraw(attending, value);
		} catch (NotEnoughtMoneyException e) {
			Alert money = new Alert(AlertType.INFORMATION);
			money.setTitle("Not enough money");
			money.setHeaderText("U have not enough money in ur account");
			money.setContentText("Please type a lesser amount and try again");
			money.showAndWait();
		} catch (ActionsOnInactiveException e) {
			Alert money = new Alert(AlertType.ERROR);
			money.setTitle("Inactive account");
			money.setHeaderText("This account is inactive");
			money.setContentText("Please active it and try again");
			money.showAndWait();
		} catch (AlreadyInactiveException e) {
			
		} catch (NumberFormatException e) {
			Alert error = new Alert(AlertType.ERROR);
			error.setTitle("Error");
			error.setHeaderText("Make sure that in field are no characters but numbers or it stay empty");
			error.showAndWait();
		}
	}
	
	@FXML
	void depositAction(ActionEvent event) {
		try {
			int value = Integer.parseInt(depositValue.getText());
			control.deposit(attending, value);
			Alert succes = new Alert(AlertType.INFORMATION);
			succes.setTitle("Successfully");
			succes.setHeaderText("Deposit made successfully");
			succes.showAndWait();
			extraStage.close();
			extraStage.close();
		} catch (ActionsOnInactiveException e) {
			Alert money = new Alert(AlertType.ERROR);
			money.setTitle("Inactive account");
			money.setHeaderText("This account is inactive");
			money.setContentText("Please active it and try again");
			money.showAndWait();
			extraStage.close();
		} catch (AlreadyInactiveException e) {
			
		} catch (NumberFormatException e) {
			Alert error = new Alert(AlertType.ERROR);
			error.setTitle("Error");
			error.setHeaderText("Make sure that in field are no characters but numbers or it stay empty");
			error.showAndWait();
			extraStage.close();
		}
	}
	
	@FXML
	void cancelAccOption(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CancelAccScreen.fxml"));
		fxmlLoader.setController(this);
		Parent cancel = fxmlLoader.load();
    	
		Scene scene = new Scene(cancel);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setTitle("Cancel Account");
		extraStage = stage;
		stage.show();
	}
	
	@FXML
	void cancelAction(ActionEvent event) {
	    try {
	    	String whyS = why.getText();
		    control.cancelAcc(attending, whyS);
		    accountIsBeingCanceledAlert();
		} catch (AlreadyInactiveException e) {
			Alert accCanceled = new Alert(AlertType.INFORMATION);
			accCanceled.setTitle("Account was not canceled");
			accCanceled.setHeaderText("The choosen account is already cancelled");
			accCanceled.showAndWait();
		}
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
				row1.getItems().add(control.findUser(id, name).getName());
			}else {
				row2.getItems().add(control.findUser(id, name).getName());
			}
		} catch (SmallerKeyException e) {
			e.getMessage();
		} catch (UserIsNotRegiterException e) {
			Alert addAcc = new Alert(AlertType.INFORMATION);
			addAcc.setTitle("User is not register");
			addAcc.setHeaderText("User is not register");
			addAcc.setContentText("Push accept button to add him or close this window to cancel.");
			attendScreen.setDisable(true);
			
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
				stage.setOnCloseRequest(ev ->{
					attendScreen.setDisable(false);
				});
				addPregnant.setDisable(true);
			}else {
				attendScreen.setDisable(false);
			}
			
		} catch (NumberFormatException e) {
			Alert error = new Alert(AlertType.ERROR);
			error.setTitle("Error");
			error.setHeaderText("Make sure that in \"Citizenship id\" field are no characters but numbers");
			error.showAndWait();
			
		}
	}
	
	@FXML
	void addUser(ActionEvent event) {
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
			
			control.addPerson(name, id, age, disabled, gender, pregnant);
			
			Alert succes = new Alert(AlertType.INFORMATION);
			succes.setTitle("Saves");
			succes.setHeaderText("User added successfully");
			succes.showAndWait();
			
			attendScreen.setDisable(false);
			extraStage.close();
		} catch (NumberFormatException e) {
			Alert error = new Alert(AlertType.ERROR);
			error.setTitle("Error");
			error.setHeaderText("Make sure that in \"Citizenship id\" and \"Age\" fields are no characters but numbers");
			error.showAndWait();
		} catch (AreadyAddedIdException e) {
			Alert dontAdd = new Alert(AlertType.ERROR);
			dontAdd.setTitle("Error");
			dontAdd.setHeaderText("User with that Id is already added");
			dontAdd.showAndWait();
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
	
	@FXML
	public void loadUsersTable() {
		ObservableList<Person> observableList;
    	observableList = FXCollections.observableArrayList(control.getPersons());
    	
		usersTable.setItems(observableList);
		nameColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("name"));
		idColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("id"));
		timeColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("dateOfIngress"));
		amountColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("totalDebt"));
	}
	
	@FXML
	void paymentOptions(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PaymentScreen.fxml"));
		fxmlLoader.setController(this);
		Parent payment = fxmlLoader.load();
		
		Scene scene = new Scene(payment);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setTitle("Payment Form");
		extraStage = stage;
		stage.show();
	}
	
	@FXML
	void paymentAction(ActionEvent event) {
		try {
			int cardNumberI = Integer.parseInt(cardNumber.getText());
			boolean all = entireDebt.isSelected();
			boolean accType = savingAccount.isSelected();
			control.cardPayment(attending, cardNumberI, all, accType);
			
			Alert success = new Alert(AlertType.INFORMATION);
			success.setTitle("Successfully");
			success.setHeaderText("Payment done succesfully");
			success.showAndWait();
			extraStage.close();
		} catch (NotFoundCardException e) {
			Alert error = new Alert(AlertType.ERROR);
			error.setTitle("Error");
			error.setHeaderText("Card with number "+ cardNumber.getText() + " was not found in your account." );
			error.showAndWait();
		} catch (AlreadyPaidException e) {
			Alert error = new Alert(AlertType.ERROR);
			error.setTitle("Error");
			error.setHeaderText("This card is already paid");
			error.showAndWait();
		} catch (AlreadyInactiveException e) {
			
		} catch (NotEnoughtMoneyException e) {
			Alert error = new Alert(AlertType.ERROR);
			error.setTitle("Error");
			error.setHeaderText("Ur saving card have not enough money");
			error.showAndWait();
		} catch (ActionsOnInactiveException e) {
			Alert money = new Alert(AlertType.ERROR);
			money.setTitle("Inactive account");
			money.setHeaderText("This account is inactive");
			money.setContentText("Please active it and try again");
			money.showAndWait();
		} catch (NumberFormatException e) {
			Alert error = new Alert(AlertType.ERROR);
			error.setTitle("Error");
			error.setHeaderText("Make sure that in field are no characters but numbers or it stay empty");
			error.showAndWait();
		}
	}
	
	@FXML
	void addCardOption(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddCardScreen.fxml"));
		fxmlLoader.setController(this);
		Parent card = fxmlLoader.load();	
		
		Scene scene = new Scene(card);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setTitle("Add card form");
		extraStage = stage;
		stage.show();
	}
	
	@FXML
	void addCardAction(ActionEvent event) {
		try {
			double debt = Double.parseDouble(debtField.getText());
			double fit = Double.parseDouble(fitField.getText());
			int quotas = Integer.parseInt(quotasField.getText());
			int fees = Integer.parseInt(feeField.getText());
			int paymentDay = Integer.parseInt(paymentField.getText());
			control.addCard(attending, debt, fit, quotas, fees, paymentDay);
		} catch (NumberFormatException e) {
			Alert error = new Alert(AlertType.ERROR);
			error.setTitle("Error");
			error.setHeaderText("Make sure that in field are no characters but numbers or it stay empty");
			error.showAndWait();
		}
	}
	
	@FXML
	void undo(ActionEvent event) {
		try {
			control.undo(attending);
		} catch (NothingToUndoException e) {
			Alert noUndo = new Alert(AlertType.ERROR);
			noUndo.setTitle("Error");
			noUndo.setHeaderText("There is no actions done yet");
			noUndo.showAndWait();
		}
	}
	
	@FXML
	void redo(ActionEvent event) {
		try {
			control.redo(attending);
		} catch (NothingToRedoException e) {
			Alert noRedo = new Alert(AlertType.ERROR);
			noRedo.setTitle("Error");
			noRedo.setHeaderText("There is no actions undone yet");
			noRedo.showAndWait();
		}
	}
}
