package application.model;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Client {
	private String Name;
	private String Email;
	private String Phone;
	private String BirthDate;
	private String Doctor;
	private String Username;
	
	public Client(String name, String email, String phone, String birthDate, String doctor, String username) {
		super();
		Name = name;
		Email = email;
		Phone = phone;
		BirthDate = birthDate;
		Doctor = doctor;
		Username = username;
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
	
	public String getBirthDate() {
		return BirthDate;
	}
	
	public void setBirthDate(String birthDate) {
		BirthDate = birthDate;
	}
	
	public String getDoctor() {
		return Doctor;
	}
	
	public void setDoctor(String doctor) {
		Doctor = doctor;
	}
	
	public String getUsername() {
		return Username;
	}
	
	public void setUsername (String username) {
		Username = username;
	}
	
	public String toString() {
		return  "\t" + Name + " " + Email + " " + Phone;
	}

	public void addClient(Client c) throws IOException {
		FileWriter fileWriter = new FileWriter("data/clients.csv", true);
		PrintWriter printWriter = new PrintWriter(fileWriter);
		
		printWriter.println(Name + "," + Email + "," + Phone + "," + BirthDate + "," + Doctor + ","  + Username);
		printWriter.close();	
	}
	
}
