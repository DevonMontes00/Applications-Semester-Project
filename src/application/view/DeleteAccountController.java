package application.view;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.io.IOException;
import application.Main;

public class DeleteAccountController {
	@FXML
	private TextField username;
	 @FXML
	 private TextField password;
	 
	 @FXML
	 private TextField nameC;
	 
	 @FXML
	 private TextField usernameC;
	 
	 @FXML
	 private TextField passwordC;
	 
	 @FXML
	 private Label errorText;
	
	@FXML
	public void deleteEmployee(ActionEvent event) throws IOException{
		String userName = username.getText();
		String Password = password.getText();
		if(userName == null || Password == null)
			return;
		int i = EmployeeController.x.deleteEmployee(userName, Password);
		int j = EmployeeController.x.deleteUsername(userName, Password);
		if(i == 1 && j == 1) {
			errorText.setText("Termintation Sucessful");
			username.clear();
			password.clear();	
			Main.showLoginView();
		}
	}
	
	@FXML
	public void deleteClient(ActionEvent event) throws IOException{
		String userName = usernameC.getText();
		String passWord = passwordC.getText();
		String name = nameC.getText();
		if(userName == null || passWord == null || name == null)
			return;
		int i = EmployeeController.x.deleteClient(userName, name);
		int j = EmployeeController.x.deleteUsername(userName, passWord);
		if(i == 1 && j == 1) {
			errorText.setText("Termintation Sucessful");
			usernameC.clear();
			passwordC.clear();
			nameC.clear();
		}
	}
	
	@FXML
	public void homeButton(ActionEvent event){
		try{
			Main.showEmployeeView();
		}
		catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
