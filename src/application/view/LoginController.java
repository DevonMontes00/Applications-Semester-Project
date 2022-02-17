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


public class LoginController implements EventHandler <ActionEvent> {
	ObservableList<String> selectorList = FXCollections.observableArrayList("Client","Employee");

	@FXML
	private Label title;
	
	@FXML
	private TextField userName;
	
	@FXML
	private PasswordField passwd;
	
	@FXML
	private Label unLabel;
	
	@FXML
	private Label pwLabel;
	
	@FXML
	private Label errorText;
	
	@FXML
    public ChoiceBox<String> selector;
	
	@FXML
	private Button submitButton;
	
	@FXML
	private Button createNewAccount;

	public Main main;
	
	public static String username, password, userType;
	
	public void initialize(){
		selector.setValue("Client");
		selector.setItems(selectorList);
		//String uName = userName.getValue();
	}
    
	// submit button
	public void handle(ActionEvent e) {
		String uName = userName.getText();
		String pWord = passwd.getText();
		username = uName;
		password = pWord;
		String type = selector.getValue();
		userType = type;
		User current = User.validate("data/users.csv", uName, pWord, type);
		if(current == null){
			errorText.setText("Incorrect username or password");
			
		}
		else if(current.getUserName() != null && current.getPasswd() != null) {
			if(current.getLoginType().equals("Client")){
				try {
					Main.showClientView();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else if(current.getLoginType().equals("Employee")){
				try {
					Main.showEmployeeView();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		else if(current.getPasswd() == null) {
			errorText.setText("Incorrect password");
		}
	}
	
	// button to create new login
	public void createNewButton(ActionEvent f) {
		//String type = selector.getValue();
		try {
			Main.showCreateNewUserView();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
