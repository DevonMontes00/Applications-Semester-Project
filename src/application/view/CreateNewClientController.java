package application.view;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Collections;

import application.Main;
import application.model.Business;
import application.model.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CreateNewClientController implements EventHandler <ActionEvent>{
	ObservableList<String> selectorList = FXCollections.observableArrayList();
	@FXML
	private Label title;
	
	@FXML
	private Label fullNL;
	
	@FXML
	private Label emL;
	
	@FXML
	private Label phonL;
	
	@FXML
	private Label dobL;
	
	@FXML
	private Label docL;
	
	@FXML
	private TextField fullNamebox;
	
	@FXML
	private TextField emailbox;
	
	@FXML
	private TextField phonebox;
	
	@FXML
	private DatePicker dateOfBirth;

	@FXML
    public ChoiceBox<String> selector;
	
	@FXML
	public Button submitbutton;
	
	
	
	public void initialize(){
		Business b = new Business("Office", "6163", "Appointments");
		b.loadEmployees("data/employees.csv");
		for(int i =0; i < b.getEmployees().size(); i++) {
			selectorList.add(b.getEmployees().get(i).getName());
		}
		Collections.sort(selectorList);
		//selector.setValue("Pick a Doctor");
		selector.setItems(selectorList);
	}
	@Override
	public void handle(ActionEvent event) {
		String fullName = fullNamebox.getText();
		String email = emailbox.getText();
		String phone = phonebox.getText();
		String birthdate = dateOfBirth.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		String doctor = selector.getValue();
		String userName = CreateNewUserController.username;
		Client c = new Client(fullName, email, phone, birthdate, doctor, userName);
		try {
			c.addClient(c);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			Main.showLoginView();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
