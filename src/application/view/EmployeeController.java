package application.view;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

import application.Main;
import application.model.Appointment;
import application.model.Business;
import application.model.Employee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;


public class EmployeeController {
	@FXML
	private Label title, message, message2;
	
	@FXML
	private DatePicker dateOfAppointment;
	
	@FXML
	private TextField searchBox;
	
	@FXML
	private ListView<String> list;
	
	public static Business x;
	public static String employeeName;
	private Employee w;
	
	@FXML
	void initialize(){
		x = new Business("doctors clinic", "6721", "health");
		x.loadEmployees("data/employees.csv");
		x.loadClients("data/clients.csv");
		x.loadAppointments("data/appointments.csv");
		x.loadUsers("data/users.csv");
		
		String userName = LoginController.username;
		String pass = LoginController.password;
		ArrayList<Employee> Employees = x.getEmployees();
		Employee y = null;
		
		for( int i = 0; i < Employees.size(); i++){
			if( Employees.get(i).getUserName().equals(userName) && Employees.get(i).getUserPass().equals(pass) ){
				y = Employees.get(i);
				w = Employees.get(i);
				employeeName = Employees.get(i).getName();

			}
		}
		title.setText("Welcome " + employeeName);
		list.getItems().add(y.toString());
    }
	
	public void jumpToDate(ActionEvent event) {
		ArrayList<Appointment> y = w.getAppointments();
		String date = dateOfAppointment.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		String appOnDate = "";
		
		for(int i = 0; i < y.size(); i++)
		{
			if(y.get(i).getDoctor().equals(employeeName) && date.equals(y.get(i).getDate()))
			{
				appOnDate = appOnDate + y.get(i).toString() + "\n";
			}
		}
		if(appOnDate.equals("")){
			appOnDate = "No Appointments Scheduled";
			list.getItems().add("Appointment on " + date + "\n\t" +  appOnDate);

		}
		else {
			list.getItems().add("Appointment on " + date + "\n" +  appOnDate);
		}
	}
	public void clear() {
		list.getItems().clear();
	}
	
	@FXML
	public void getClientApps(ActionEvent event){
		String x = searchBox.getText();
		String z = EmployeeController.x.getAppsByClient(x);
		list.getItems().clear();
		if (z == ""){
			list.getItems().add("No appointments.");
			searchBox.clear();
		}
		else{
			list.getItems().add(z);
		}
		searchBox.clear();

	}

	@FXML
	public void Button1(ActionEvent e){
		try {
			Main.showAddAppointment();
		} 
		catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	@FXML
	public void Button2(ActionEvent e){
		try {
			Main.showDeleteEmployee();
		} 
		catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	@FXML
	public void Button3(ActionEvent e){
		try {
			Main.showDeleteAppointment();
		} 
		catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	@FXML
	public void Button4(ActionEvent e){
		try {
			Main.showGetClientInfo();
		} 
		catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	@FXML
	public void sortByName(ActionEvent e){
		ArrayList<Appointment> y = w.getAppointments();
		int size = y.size();
		int newSize;
		String output = "";
		String names[] = new String[size];
			
		for( int i = 0; i < y.size(); i++ ){
			if ( y.get(i).getDoctor().equals(employeeName) )
				names[i] = y.get(i).getName();	
		}
		String[] names2	= x.duplicates(names, names.length);
		Arrays.sort(names2);	
		newSize = names2.length;
	
		for( int i = 0; i < newSize; i++ ){
			for( int j = 0; j < y.size(); j++){
				Appointment a = y.get(j);
				if ( a.getDoctor().equals(employeeName) && a.getName().equals(names2[i]) )
					output = output + a.toString() + "\n";//a.getName() + " " + a.getDoctor() + " " + a.getDate() + " " + a.getTime() + "\n";
			}
		}	
		list.getItems().clear();
		list.getItems().add(output);
	}
	
	@FXML
	public void sortByDate(ActionEvent e){
		System.out.println("this needs to be worked on");
	}
	
	
	
	
	@FXML
	public void logOutButton(ActionEvent e) {
		try {
			Main.showLoginView();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}	
}
