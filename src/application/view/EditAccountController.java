package application.view;

import java.io.IOException;
import java.util.ArrayList;
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

public class EditAccountController implements EventHandler <ActionEvent> {
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
	
	static String newDate, oldName;
	public static Business b;
	
	
	
	public void initialize(){
		b = new Business("Office", "6163", "Appointments");
		b.loadEmployees("data/employees.csv");
		b.loadClients("data/clients.csv");
		for(int i =0; i < b.getEmployees().size(); i++) {
			selectorList.add(b.getEmployees().get(i).getName());
		}
		Collections.sort(selectorList);
		ArrayList <Client> ClientAL = b.getClientAL();
		for(int i = 0; i < ClientAL.size(); i++){
			if(LoginController.username.equals(ClientAL.get(i).getUsername())){
				selector.setItems(selectorList);
				fullNamebox.setText(ClientAL.get(i).getName());
				emailbox.setText(ClientAL.get(i).getEmail());
				phonebox.setText(ClientAL.get(i).getPhone());
				newDate = ClientAL.get(i).getBirthDate();
				selector.setValue(ClientAL.get(i).getDoctor());
				oldName = ClientAL.get(i).getName();
			}
		}
			
	}
	@Override
	public void handle(ActionEvent event) {
		String fullName = fullNamebox.getText();
		String email = emailbox.getText();
		String phone = phonebox.getText();
		//String birthdate = dateOfBirth.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		String doctor = selector.getValue();
		String userName = LoginController.username;
		if(fullName == null || email == null || phone == null|| doctor == null)
			return;
		Client c = new Client(fullName, email, phone, newDate, doctor, userName);
		
			try {
				Business.updateClient(c);
				if(!fullName.equals(oldName)) {
					Business.updateClientNameInApp(oldName, c.getName());
				}
				Main.showClientView();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	}
	
	public void backButton(ActionEvent e) {
		try {
			Main.showClientView();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
