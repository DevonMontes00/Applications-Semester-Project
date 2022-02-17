package application.model;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Employee {
	private String Name;
	private String Email;
	private String Phone;
	private String Code;
	private String UserName;
	private String UserPass;
	private String OpenTime;
	private String ClosingTime;
	private ArrayList<Client> clients;
	private ArrayList<Appointment> appointments;
	
	public Employee(String name, String email, String phone, String code, String userName, String userPass, String openTime, String closingTime) {
		super();
		Name = name;
		Email = email;
		Phone = phone;
		Code = code;
		UserName = userName;
		UserPass = userPass;
		OpenTime = openTime;
		ClosingTime = closingTime;
		clients = new ArrayList<Client>();
		appointments = new ArrayList<Appointment>();
	}

	public String getName() {
		return Name;
	}
	
	public void setName(String name) {
		Name = name;
	}
	
	public String getEmail() {
		return Email;
	}
	
	public void setEmail(String email) {
		Email = email;
	}
	
	public String getPhone() {
		return Phone;
	}
	
	public void setPhone(String phone) {
		Phone = phone;
	}
	
	public String getUserName() {
		return UserName;
	}
	
	public void setUserName(String userName) {
		UserName = userName;
	}
	
	public String getUserPass() {
		return UserPass;
	}
	
	public void setUserPass(String userPass) {
		UserPass = userPass;
	}
	
	public String getCode() {
		return Code;
	}
	
	public void setCode(String code) {
		Code = code;
	}
	
	public String getOpenTime() {
		return OpenTime;
	}

	public void setOpenTime(String openTime) {
		OpenTime = openTime;
	}

	public String getClosingTime() {
		return ClosingTime;
	}

	public void setClosingTime(String closingTime) {
		ClosingTime = closingTime;
	}

	
	public ArrayList<Client> getClients() {
		return clients;
	}
	
	public ArrayList<Appointment> getAppointments(){
		return appointments;
	}
	
	public void addAppointment(Appointment x){
		appointments.add(x);
	}
	
	public void addClient(Client x){
		clients.add(x);
	}
	
	public void removeClient(Client x) {
		clients.remove(x);
	}
	
	public void removeAppointment(Appointment x) {
		appointments.remove(x);
	}
	
	public String toString() {
		String list = "";
	    for(int i = 0; i < appointments.size(); i++) {
	    	list += appointments.get(i) + " \n";
	    }
        return  list;//Name + " " + Email + " " + Phone + " " + OpenTime + " " + ClosingTime + "\n" + list;
	}

	public void addEmployee(Employee e) throws IOException {
		FileWriter fileWriter = new FileWriter("data/employees.csv", true);
		PrintWriter printWriter = new PrintWriter(fileWriter);
		
		printWriter.println(Name + "," + Email + "," + Phone + "," + Code + "," + UserName + "," +  UserPass + "," + OpenTime + "," + ClosingTime);
		printWriter.close();	
		
	}
}

