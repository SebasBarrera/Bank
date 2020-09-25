package ui;

import javafx.fxml.FXMLLoader;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{
private BankGUI bankGUI;
	
	public Main() {
		bankGUI = new BankGUI();
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader fxmll = new FXMLLoader(getClass().getResource("MainScreen.fxml"));
		fxmll.setController(bankGUI);
		Parent root = fxmll.load();
		//root.getStylesheets().add(getClass().getResource("styleFirstScreen.css").toExternalForm());
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Bank Application");
		//primaryStage.setResizable(false);
		primaryStage.show();
	}
}
