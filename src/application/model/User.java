package application.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class User {
	private String username;
	private String passwd;
	private String loginType;
	
	public Employee x;
	
	public User(String username, String passwd, String loginType) {
		this.username = username;
		this.passwd = passwd;
		this.loginType = loginType;
	}
	
	/**
	 * Gets the username of User
	 * @return String of User username
	 */
	public String getUserName() {
		return this.username;
	}
	
	/**
	 * Sets the username of User
	 * @param username to set for this User (String)
	 */
	public void setUserName(String username) {
		this.username = username;
	}
	
	/**
	 * Gets the password of User
	 * @return String of User passwd
	 */
	public String getPasswd() {
		return this.passwd;
	}
	
	/**
	 * Sets the password of User
	 * @param passwd to set for this User (String)
	 */
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	
	/**
	 * Gets the Login type of user
	 * @return String of user loginType
	 */
	public String getLoginType() {
		return this.loginType;
	}
	
	/**
	 * Sets the Login type of User
	 * @param loginType to set for this User (String)
	 */
	public void setLogintype(String loginType) {
		this.loginType = loginType;
	}
	
	public static User validate(String fileName, String uName, String pWrd, String type) {
		Scanner in = null;
		try {
			in = new Scanner(new File(fileName));
		} catch(FileNotFoundException exception) {
			System.err.println("can't find " + fileName);
			System.exit(1);
		}
		while(in.hasNext()) {
			String line = in.nextLine();
			String [] params = line.split(",");
			
			if(params[0].equals(uName) && params[1].equals(pWrd) && params[2].equals(type)) {
				User us = new User(params[0], params[1], params[2]);
				in.close();
				return us;
			}
			else if(params[0].equals(uName) && !params[1].equals(pWrd)) {
				User w1 = new User(params[0], null, params[2]);
				in.close();
				return w1;
			}
			else {
				continue;
			}
		}
		in.close();
		return null;
	}
	
	public void addUser(String uName, String pWord, String type) throws IOException {
		FileWriter fileWriter = new FileWriter("data/users.csv", true);
		PrintWriter printWriter = new PrintWriter(fileWriter);
		
		if(type.equals("Client") || type.equals("Employee")){
			printWriter.println(uName + "," + pWord + "," + type);
		}
		printWriter.close();	
	}

	

}
