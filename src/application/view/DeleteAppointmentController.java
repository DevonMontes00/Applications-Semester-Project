package application.view;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import application.Main;
import application.model.Business;
import application.model.Client;

public class DeleteAppointmentController {
	@FXML
	private TextField name;
	
	@FXML
	private DatePicker dateP;
	
	@FXML
	private Label clientL, errorText;
	
	static ArrayList <Client> clients;
	
	public static Business b;
	
	
	public void initialize() {
		b = new Business("Office", "6163", "Appointments");
		b.loadEmployees("data/employees.csv");
		b.loadClients("data/clients.csv");
		if(LoginController.userType.equals("Client")){
			name.setText(ClientController.clientName);
			name.setVisible(false);
			clientL.setText(ClientController.clientName);
		}
		clients = b.getClientAL();
	}
	
	@SuppressWarnings("static-access")
	@FXML
	public void deleteButton(ActionEvent event) throws IOException {
		if(dateP.getValue() == null) {
			return;
		}
		String y = name.getText();
		String z = dateP.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		
		if(!clients.contains(b.getClientInfo(y))) {
			errorText.setText("ERROR: Incorrect Name - Not in system");
			name.clear();
			dateP.setValue(null);
			return;
		}
		
		int j = b.deleteAppoitment(y, z);
		
		if (j == 0) {
			errorText.setText("ERROR: Incorrect Date - Not in system for Client");
			name.clear();
			dateP.setValue(null);
			return;
		}
		
		errorText.setText("Delete Sucessful");
		name.clear();
		dateP.setValue(null);
	}
	
	@FXML
	public void homeButton(ActionEvent event){
		String type = LoginController.userType;
		
		if(type.equals("Client")){
			try {
				Main.showClientView();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(type.equals("Employee")){
			try {
				Main.showEmployeeView();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
}
