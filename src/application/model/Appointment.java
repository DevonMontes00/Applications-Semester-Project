package application.model;


public class Appointment {
	private String name, doctor, date, time;

	public Appointment(String name, String doctor, String date, String time) {
		super();
		this.name = name;
		this.doctor = doctor;
		this.date = date;
		this.time = time;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDoctor() {
		return doctor;
	}
	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	public static String clientTime(String time) {
        String clientTime = "";
		String[] u = time.split(":");
		int num = Integer.parseInt(u[0]);
		
		if (num < 12 && num != 0){
            return time + " AM";
		}
        else if (num > 12){
            num -= 12; 
            clientTime = String.valueOf(num) + ":" + u[1];
            return clientTime + " PM";
        }
        else if (num == 12){
            return time + " PM";
        }
        else if (num == 0){
            return "12:" + u[1] + " AM";
        }
		
		return null;

	}
	

	@Override
	public String toString() {
		String cTime = clientTime(time);
		return name + " - " + cTime + " " + date;
	}
	
	public String toStringClient() {
		String cTime = clientTime(time);
		return doctor + " - " + cTime + " " + date;
	}
	
	
	
}
