package application.view;

import java.io.IOException;

import application.Main;
import application.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class CreateNewUserController implements EventHandler <ActionEvent> {
	ObservableList<String> selectorList = FXCollections.observableArrayList("Client","Employee");

	@FXML
	private Label title;
	
	@FXML
	private TextField userName;
	
	@FXML
	private PasswordField passwd;
	
	@FXML
	private PasswordField confirmPasswd;
	
	@FXML
	private Label unLabel;
	
	@FXML
	private Label pwLabel;
	
	@FXML
	private Label conPwLabel;
	
	@FXML
    public ChoiceBox<String> selector;
	
	@FXML
	private Button submitButton;
	
	@FXML
	private Button backButton;
	
	@FXML
	private Label errorText;
	
	public static String username, password;
	
	public void initialize(){
		selector.setValue("Client");
		selector.setItems(selectorList);
		//String uName = userName.getValue();
	}

	@Override
	public void handle(ActionEvent event) {
		String uName = userName.getText();
		String pWord = passwd.getText();
		String cPWord = confirmPasswd.getText();
		String type = selector.getValue();
		
		username = uName; 
		password = pWord;

		if(!pWord.equals(cPWord)) {
			errorText.setText("Password does not match");
		}
		else if(!uName.equals("") && !pWord.equals("") && !cPWord.equals("")) {
			User current = User.validate("data/users.csv", uName, pWord, type);
			if(current == null){
				current = new User(uName, pWord, type);
				try {
					current.addUser(uName, pWord, type);
				} catch (IOException e) {
					e.printStackTrace();
				}
				//errorText.setText("Account created!");
				// go to next screen
				if(type.equals("Client")) {
					try {
						Main.showCreateNewClientView();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else if(type.equals("Employee")){
					try {
						Main.showCreateNewEmployeeView();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
	
			}
			else {
				errorText.setText("Account already exists!");
			}
		}
	}
	
	public void backButton(ActionEvent f) {
		try {
			Main.showLoginView();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
