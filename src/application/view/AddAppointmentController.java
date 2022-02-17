package application.view;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Collections;

import application.Main;
import application.model.Appointment;
import application.model.Business;

public class AddAppointmentController {
	ObservableList<String> selectorList = FXCollections.observableArrayList();

	@FXML
	private TextField name1, time;
	
	@FXML
	private Label message, message2, nameL, clientL;
	
	@FXML
	public ChoiceBox<String> selector;
	
	@FXML
	public Button home;
	
	@FXML
	private DatePicker date;
	
	public static Business b;
		
	@FXML
	public void initialize() {
		b = new Business("Office", "6163", "Appointments");
		b.loadEmployees("data/employees.csv");
		b.loadAppointments("data/appointments.csv");
		b.loadClients("data/clients.csv");
		
		for(int i = 0; i < b.getEmployees().size(); i++) {
			selectorList.add(b.getEmployees().get(i).getName() + "\nOperating Hours: \n" + Appointment.clientTime(b.getEmployees().get(i).getOpenTime()) + " - " + Appointment.clientTime(b.getEmployees().get(i).getClosingTime()));
		}
		selector.setItems(selectorList);
		if(LoginController.userType.equals("Employee")) {
			for(int i = 0; i < b.getEmployees().size(); i++) {
				if(EmployeeController.employeeName.equals(b.getEmployees().get(i).getName())){
					selector.setValue(selectorList.get(i));
				}
			}
		}
		else if(LoginController.userType.equals("Client")){
			name1.setText(ClientController.clientName);
			name1.setVisible(false);
			clientL.setText(ClientController.clientName);
			for(int i = 0; i < b.getEmployees().size(); i++) {
				if(Business.getClientInfo(ClientController.clientName).getDoctor().equals(b.getEmployees().get(i).getName())){
					selector.setValue(selectorList.get(i));
				}
			}
		}
		Collections.sort(selectorList);
		Collections.sort(selectorList);
	}
	
	@FXML
	public void addButton(ActionEvent event){
		if(name1 == null || selector.getValue().equals("Select Doctor") || date == null || time == null) {
			message.setText("ERROR: Incorrect structure - Check Information");
			return;
		}
		String name = name1.getText();
		String appDate = date.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		String appTime = time.getText();
		String doc = selector.getValue();
		String dr [] = doc.split("\n") ;
		doc = dr[0];
		
		if (!b.getClientAL().contains(Business.getClientInfo(name))){
			message.setText("ERROR: Incorrect Name - Not in system");
			return; 
		} 
		
		if (appTime.contains("PM")){
			String[] u = appTime.split(":");
			int num = Integer.parseInt(u[0]);
			if(num != 12) {
				num += 12;
			}
            appTime = String.valueOf(num) + ":" + u[1];
            appTime = appTime.replace(" PM", "");
        }
		if (appTime.contains("AM")) {
        	appTime = appTime.replace(" AM", "");
        }
		
		int x = b.addAppoitment(name, doc, appDate, appTime);
		
		if ( x == 0 ) {
			message.setText("ERROR: This date and time is already taken.");
			date.setValue(null);
		}
		else if( x == 1) {
			message.setText("Succsessful Addition");
			time.clear();
			date.setValue(null);
			if(LoginController.userType.equals("Employee")) {
				name1.clear();
			}
		}
		else if( x == -1) {
			message.setText("ERROR: Not in time range");
			time.clear();
		}
		else {
			message.setText("ERROR: Incorrect structure - Check Information");
		}
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
