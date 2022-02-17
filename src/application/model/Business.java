package application.model;

import java.io.BufferedWriter;
import java.util.List;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import application.view.EmployeeController;
import application.view.LoginController;

public class Business {
	private String BusinessName;
	private String Occupation;
	private String RegisterCode;
	private ArrayList<Employee> Employees;
	private ArrayList<User> users;
	private static ArrayList<Client> clientAL;
	private ArrayList<Appointment> appointmentAL;
	
	public Business(String businessName, String registerCode, String occupation) {
		super();
		BusinessName = businessName;
		Employees = new ArrayList<Employee>();
		users = new ArrayList<User>();
		clientAL = new ArrayList<Client>();
		appointmentAL = new ArrayList<Appointment>();
		Occupation = occupation;
		RegisterCode = registerCode;
	}

	public String getBuisnessName() {
		return BusinessName;
	}
	
	public void setBuisnessName(String businessName) {
		BusinessName = businessName;
	}
	
	public String getOccupation() {
		return Occupation;
	}
	
	public void setOccupation(String occupation) {
		Occupation = occupation;
	}
	
	public String getRegisterCode() {
		return RegisterCode;
	}
	
	public void setRegisterCode(String registerCode) {
		RegisterCode = registerCode;
	}
		
	public void removeEmployee(Employee x) {
		Employees.remove(x);
	}
	
	public void addClientAL(Client c)
	{
		clientAL.add(c);
	}
	
	public void addAppointmentAL(Appointment a)
	{
		appointmentAL.add(a);
	}
	
	
	
	public String toString(){       
        String list = "";
       
        for(int i = 0; i < Employees.size(); i++) {
        	list += Employees.get(i) + " \n";
        }
        return BusinessName + " " + Occupation + " " + RegisterCode + "\n\n" + list;
    }
	
	public ArrayList<Employee> getEmployees() {
		return Employees;
	}
	
	public ArrayList<Client> getClientAL()
	{
		return clientAL;
	}
	
	public ArrayList<Appointment> getAppointmentAL()
	{
		return appointmentAL;
	}
	
	public void loadUsers(String fileName){
		try {
    		File file = new File (fileName);
    		Scanner scan = new Scanner( file );
	
    		while( scan.hasNext()) {
    			String line = scan.nextLine();				
        		String[] values = line.split(",");
        		User x = new User(values[0], values[1], values[2]);
        		users.add(x);
    		}
    		scan.close();
    	}
    	catch( IOException e ) {
			System.out.println( "Error loading the file - please check its location." );
			e.printStackTrace(); 
		}
	}
	
	public void loadEmployees(String fileName) {
    	try {
    		File file = new File (fileName);
    		Scanner scan = new Scanner( file );
	
    		while( scan.hasNext()) {
    			String line = scan.nextLine();				
        		String[] values = line.split(",");
        		Employee x = new Employee(values[0], values[1], values[2], values[3], values[4], values[5], values[6], values[7]);	
        		Employees.add(x);
    		}
    		scan.close();
    	}
    	catch( IOException e ) {
			System.out.println( "Error loading the file - please check its location." );
			e.printStackTrace(); 
		}
    }
	
	public void loadAppointments(String fileName) {
		try {
    		File file = new File (fileName);
    		Scanner scan = new Scanner( file );
	
    		while( scan.hasNext()) {
    			String line = scan.nextLine();
        		String[] values = line.split(",");
        		
        		Appointment x = new Appointment(values[0],values[1], values[2], values[3]);
        		addAppointmentAL(x);
        		for(int i = 0; i < Employees.size(); i++) {
        			if(values[1].equals(Employees.get(i).getName())){
        				Employees.get(i).addAppointment(x);	
        			}
        		}
    		}
    		scan.close();
    	}
    	catch( IOException e ) {
			System.out.println( "Error loading the file - please check its location." );
			e.printStackTrace(); 
		}
    }
	
	public void loadClients(String fileName) {
		try {
    		File file = new File (fileName);
    		Scanner scan = new Scanner( file );
	
    		while( scan.hasNext()) {
    			String line = scan.nextLine();
        		String[] values = line.split(",");
        		Client x = new Client(values[0], values[1], values[2], values[3], values[4], values[5]);
        		addClientAL(x);
        		for(int i = 0; i < Employees.size(); i++) {
        				Employees.get(i).addClient(x);	
        		}
    		}
    		scan.close();
    	}
    	catch( IOException e ) {
			System.out.println( "Error loading the file - please check its location." );
			e.printStackTrace(); 
		}
    }
	
	public int deleteAppoitment(String clientName, String Date) throws IOException {	
			String name = "";
			String doctor = "";
			String date = "";
			String time = "";
			
			String tempFile = "temp.txt";
			File oldFile = new File("data/appointments.csv");
			File newFile = new File(tempFile);
			FileWriter fw = new FileWriter(tempFile, true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			Scanner s = new Scanner(new File("data/appointments.csv"));
			s.useDelimiter("[,\n]");
			int j = 0;
			while(s.hasNext()) {
				name = s.next();
	    		doctor = s.next();
	    		date = s.next();
	    		time = s.next();
	    			
	    		if (clientName.equals(name) && Date.equals(date) ){
	    			j = 1;
	    			continue;
	    		}
	    		else {
	    			pw.print(name + "," + doctor + "," + date + "," + time  + "\n");

	    		}
			}
			s.close();
			pw.flush();
			pw.close();
			oldFile.delete();
			File dump = new File("data/appointments.csv");
			newFile.renameTo(dump);
			
			return j;
		
	}
	
	public int addAppoitment(String name, String doctor, String date, String time) {	
		try {	
			if(name == null || doctor == null || date == null || time == null) {	
				return -1;
			}
				
			Appointment x = new Appointment(name, doctor, date, time);
				
			for(int i = 0; i < Employees.size(); i++) {
				for(int j = 0; j < Employees.get(i).getAppointments().size(); j++) {
					String AppDate = Employees.get(i).getAppointments().get(j).getDate();
					String AppTime = Employees.get(i).getAppointments().get(j).getTime();
					
					if (AppDate.equals(x.getDate()) && AppTime.equals(x.getTime())) {
						return 0;
					}
				}
			}
			String open = "";
			String close = "";
			
			for(int n = 0; n < Employees.size(); n++) {
				if( Employees.get(n).getName().equals(doctor)) {
					open = Employees.get(n).getOpenTime();
					close = Employees.get(n).getClosingTime();
				}	
			}
			String [] u = null;
			String[] y = open.split(":");
			String[] w = close.split(":");
			if(time.contains(":")) {
				u = time.split(":");
			}
			else {
				return -2;
			}
			
			
			int num = Integer.parseInt(y[0] + y[1]);
			int num2 = Integer.parseInt(w[0] + w[1]);
			int userTime = Integer.parseInt(u[0] + u[1]);
				
			if(num < userTime && userTime < num2 ) {		
				FileWriter fstream = new FileWriter("data/appointments.csv",true);
				BufferedWriter writer = new BufferedWriter(fstream);
				PrintWriter pw = new PrintWriter(writer);
				pw.print(name + "," + doctor + "," + date + "," + time  + "\n");
				pw.close();
				return 1;
			}
			else {
				return -1;
			}
		}
		catch( IOException e ) {
			System.out.println( "Error loading the file - please check its location." );
			e.printStackTrace(); 
		}
		return -2;
	}
	
	//this function is called from login to create new employee 
	public void addEmployee(String name, String email, String phone, String code, String userName, String password, String openTime, String closingTime) {
		try {
			Employee x = new Employee(name, email, phone, code, userName, password, openTime, closingTime);
			
			for(int i = 0; i < Employees.size(); i++) {
				if(x.getName().equals(Employees.get(i).getName()) && x.getEmail().equals(Employees.get(i).getEmail())) {
					System.out.println("ERROR: This is an existing employee.");
					return;
				}
			}
			FileWriter fstream = new FileWriter("data/employees.csv",true);
			BufferedWriter writer = new BufferedWriter(fstream);
			writer.write(name +"," + email + "," + phone + "," + code + "," + userName + "," + password + "," + openTime +  "," + closingTime);
			writer.close();
			
			Employees.add(x);
		}
		catch( IOException e ) {
			System.out.println( "Error loading the file - please check its location." );
			e.printStackTrace(); 
		}
	}
	
	public int deleteEmployee(String userName, String password) throws IOException {
		String name = "", email = "", phone = "", code = "", username = "", passWord = "", open = "", close = "";
		
		String tempFile = "temp.txt";
		File oldFile = new File("data/employees.csv");
		File newFile = new File(tempFile);
		FileWriter fw = new FileWriter(tempFile, true);
		BufferedWriter bw = new BufferedWriter(fw);
		PrintWriter pw = new PrintWriter(bw);
		Scanner s = new Scanner(new File("data/employees.csv"));
		s.useDelimiter("[,\n]");
		int i = 0;
		while(s.hasNext()) {
			name = s.next();
    		email = s.next();
    		phone = s.next();
    		code = s.next();
    		username = s.next();
    		passWord = s.next();
    		open = s.next();
    		close = s.next();
    			
			if ( userName.equals(username) && password.equals(passWord) ){
    			i = 1;
				continue;//pw.print("");
    		}
    		else {
    			pw.print(name + "," + email + "," + phone + "," + code + "," + username + "," + passWord + "," + open + "," + close + "\n");

    		}
		}
		s.close();
		pw.flush();
		pw.close();
		oldFile.delete();
		File dump = new File("data/employees.csv");
		newFile.renameTo(dump);
		
		return i;	
	}
	
	public int deleteClient(String UserName, String Name) throws IOException {
		String name =""; String email =""; String phone=""; String birthDate=""; String doctor=""; String username="";	
		String tempFile = "temp.txt";
		File oldFile = new File("data/clients.csv");
		File newFile = new File(tempFile);
		FileWriter fw = new FileWriter(tempFile, true);
		BufferedWriter bw = new BufferedWriter(fw);
		PrintWriter pw = new PrintWriter(bw);
		Scanner s = new Scanner(new File("data/clients.csv"));
		s.useDelimiter("[,\n]");
		int i = 0;
		while(s.hasNext()) {
			name = s.next();
    		email = s.next();
    		phone = s.next();
    		birthDate = s.next();
    		doctor = s.next();
    		username = s.next();
    			
			if (UserName.equals(username) && Name.equals(name) ){
    			i = 1;
				continue;//pw.print("");
    		}
    		else {
    			pw.print(name + "," + email + ","  + phone + "," + birthDate + "," + doctor + "," + username + "\n");

    		}
		}
		s.close();
		pw.flush();
		pw.close();
		oldFile.delete();
		File dump = new File("data/clients.csv");
		newFile.renameTo(dump);
		
		return i;
			
	}
	
	public int deleteUsername(String username, String password) throws IOException{
		String Username = "", Password = "", type = "";
		String tempFile = "temp.txt";
		File oldFile = new File("data/users.csv");
		File newFile = new File(tempFile);
		FileWriter fw = new FileWriter(tempFile, true);
		BufferedWriter bw = new BufferedWriter(fw);
		PrintWriter pw = new PrintWriter(bw);
		Scanner s = new Scanner(new File("data/users.csv"));
		s.useDelimiter("[,\n]");
		int i = 0;
		while(s.hasNext()) {
			Username = s.next();
    		Password = s.next();
    		type = s.next();
    			
    		if (Username.equals(username) && Password.equals(password) ){
    			i = 1;
    			continue;//pw.print("");
    		}
    		else {
    			pw.print(Username + "," + Password + "," + type + "\n");
    			
    		}
		}
		s.close();
		pw.flush();
		pw.close();
		oldFile.delete();
		File dump = new File("data/users.csv");
		newFile.renameTo(dump);
		
		return i;		
	}
	public static void updateClient(Client c) throws IOException{
		String tempFile = "temp.txt";
		File oldFile = new File("data/clients.csv");
		File newFile = new File(tempFile);
		String name = ""; String email=""; String phone=""; String birthDate="";
		String doctor=""; String username ="";
			FileWriter fw = new FileWriter(tempFile, true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			Scanner s = new Scanner(new File("data/clients.csv"));
			s.useDelimiter("[,\n]");
			
			while(s.hasNext()) {
				name = s.next(); email=s.next(); phone=s.next(); birthDate = s.next();
				doctor=s.next(); username =s.next();
				
				if(LoginController.username.equals(username)) {
					pw.print(c.getName() + "," + c.getEmail() + "," + c.getPhone() + "," + c.getBirthDate() + "," + c.getDoctor() + "," +LoginController.username + "\n");
				}
				else {
					pw.print(name + "," + email + "," + phone + "," + birthDate + "," + doctor + "," + username + "\n");
				}
			}
			s.close();
			pw.flush();
			pw.close();
			oldFile.delete();
			File dump = new File("data/clients.csv");
			newFile.renameTo(dump);
	}
	
	public static void updateClientNameInApp(String oldName, String newName) throws IOException{
		String tempFile = "temp.txt";
		File oldFile = new File("data/appointments.csv");
		File newFile = new File(tempFile);
		String names = ""; String doctors = ""; String dates = ""; String times = "";
		FileWriter fw = new FileWriter(tempFile, true);
		BufferedWriter bw = new BufferedWriter(fw);
		PrintWriter pw = new PrintWriter(bw);
		Scanner s = new Scanner(new File("data/appointments.csv"));
		s.useDelimiter("[,\n]");
		
		while(s.hasNext()) {
			names = s.next(); 
			doctors = s.next(); 
			dates = s.next(); 
			times = s.next();
			
			if(oldName.equals(names)) {
				pw.print(newName + "," + doctors + "," + dates + "," + times + "\n");
			}
			else {
				pw.print(names + "," + doctors + "," + dates + "," + times + "\n");
			}
		}
		
		
		s.close();
		pw.flush();
		pw.close();
		oldFile.delete();
		File dump = new File("data/appointments.csv");
		newFile.renameTo(dump);
	}
	
	public static Client getClientInfo(String name){
		for(int i = 0; i < clientAL.size(); i++){
			if(clientAL.get(i).getName().equals(name)){
				Client x = clientAL.get(i);
				return x;
			}
		}
		return null;
	}
	
	public String getAppsByClient(String name){
		String x = "";
		
		for(int i = 0; i < appointmentAL.size(); i++){
			if(appointmentAL.get(i).getName().equals(name) && appointmentAL.get(i).getDoctor().equals(EmployeeController.employeeName)){
				Appointment y = appointmentAL.get(i);
				x = x +  y.toString() + "\n";
			}
		}
		return x;
	}
	
	public String[] duplicates( String[] arr, int n)
    {
        if (n==0 || n==1)
           return arr;
       
        List<String> list = new ArrayList<String>();
     
        for(int i = 0; i < n; i++){
        	if(	list.contains(arr[i]) == false){
        		list.add(arr[i]);
        	}
        }
        String[] array = new String[list.size()];
        
        for (int i = 0; i < list.size(); i++){
            array[i] = list.get(i);
        }
        return array;
    }

}
