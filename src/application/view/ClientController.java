package application.view;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.Main;
import application.model.Appointment;
import application.model.Business;
import application.model.Client;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class ClientController implements EventHandler <ActionEvent>, Initializable {
	
	@FXML
	private Button LogOut;
	
	@FXML
	private Button createAppointment;
	
	@FXML
	private Button deleteAppointment;
	
	@FXML
	private Button editAppointment;
	
	@FXML
	private Button editAccount;
	
	@FXML
	private Button showAll;
	
	@FXML
	private Label ClientName;
	
	@FXML
	private TextArea DateBox;
	
	@FXML
	private TextArea clientAppointments;
	
	@FXML
	private DatePicker dateOfAppointment;
	
	private String Username;
	
	static String clientName;
	
	static ArrayList<Appointment> AppointmentAL;
	
	static ArrayList<Client> ClientAL;
	
	public static Business b;

	public void initialize(URL url, ResourceBundle rb){
		b = new Business("Business", "123", "Doctor");
		b.loadAppointments("data/appointments.csv");
		b.loadClients("data/clients.csv");
		b.loadEmployees("data/employees.csv");
		b.loadUsers("data/users.csv");
		ClientAL = b.getClientAL();
		AppointmentAL = b.getAppointmentAL();
		//clientName = "";
		String appointmentList = "";
		int i;
		Username = LoginController.username;

		
		for(i = 0; i < ClientAL.size(); i++)
		{
			if(Username.equals(ClientAL.get(i).getUsername()))
			{
				clientName = ClientAL.get(i).getName();
				ClientName.setText("Welcome back, " + clientName);
			}
		}
		
		for(i = 0; i < AppointmentAL.size(); i++)
		{
			if(clientName.equals(AppointmentAL.get(i).getName()))
			{
				appointmentList = appointmentList + AppointmentAL.get(i).toStringClient() + "\n";
				break;
			}
		}
		
		clientAppointments.setText("Next Appointment\n" + appointmentList);
    
    }
	
	public void showAllAppointments(ActionEvent event) {
		String allAppointments = "";

		for(int i = 0; i < AppointmentAL.size(); i++)
		{
			if(clientName.equals(AppointmentAL.get(i).getName()))
			{
				allAppointments = allAppointments + AppointmentAL.get(i).toStringClient() + "\n";
			}
		}
		
		clientAppointments.setText("All Appointments\n" + allAppointments);
	}
	
	public void jumpToDate(ActionEvent event) {
		String date = dateOfAppointment.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		String appOnDate = "";
		
		for(int i = 0; i < AppointmentAL.size(); i++)
		{
			if(clientName.equals(AppointmentAL.get(i).getName()) && date.equals(AppointmentAL.get(i).getDate()))
			{
				appOnDate = appOnDate + AppointmentAL.get(i).toStringClient() + "\n";
			}
		}
		if(appOnDate.equals("")){
			appOnDate = "No Appointments Scheduled";
			clientAppointments.setText("Appointment on " + date + "\n\t" +  appOnDate);

		}
		else {
			clientAppointments.setText("Appointment on " + date + "\n" +  appOnDate);
		}
	}

	
	// button add appointment
	public void handle(ActionEvent event) {
		try {
			Main.showAddAppointment();
		} 
		catch (IOException e1) {
			e1.printStackTrace();
		}	
	}
	
	public void editAccount(ActionEvent e){
		try {
			Main.showEditAccount();
		} 
		catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public void deleteAppointment(ActionEvent e){
		try {
			Main.showDeleteAppointment();
		} 
		catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public void logOutButton(ActionEvent e) {
		try {
			Main.showLoginView();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void setUsername(String uName)
	{
		Username = uName;
	}

}
