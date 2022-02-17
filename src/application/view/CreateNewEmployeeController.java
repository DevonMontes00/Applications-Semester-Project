package application.view;

import java.io.IOException;

import application.Main;
import application.model.Employee;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CreateNewEmployeeController implements EventHandler <ActionEvent>{
	
	@FXML
	private Label title;
	
	@FXML
	private Label fullNL;
	
	@FXML
	private Label emL;
	
	@FXML
	private Label phonL;
	
	@FXML
	private Label bCodeL;
	
	@FXML
	private Label openTimeL;
	
	@FXML
	private Label endTimeL;
	
	@FXML
	private TextField businessCodebox;
	
	@FXML
	private TextField fullNamebox;
	
	@FXML
	private TextField emailbox;
	
	@FXML
	private TextField phonebox;
	
	@FXML
	private TextField openTime;
	
	@FXML
	private TextField closeTime;

	
	@FXML
	public Button submitbutton;



	@Override
	public void handle(ActionEvent event) {
		String fullName = fullNamebox.getText();
		String email = emailbox.getText();
		String phone = phonebox.getText();
		String bussinessCode = businessCodebox.getText();
		String startTime = openTime.getText();
		String endingTime = closeTime.getText();
		String userName = CreateNewUserController.username;
		String passWord = CreateNewUserController.password;
		
		Employee e = new Employee(fullName, email, phone, bussinessCode, userName, passWord, startTime, endingTime);
		try {
			e.addEmployee(e);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			Main.showLoginView();
		} catch (IOException f) {
			// TODO Auto-generated catch block
			f.printStackTrace();
		}
	}

}
