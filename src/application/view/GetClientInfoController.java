package application.view;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import java.io.IOException;
import application.Main;
import application.model.Business;
import application.model.Client;

public class GetClientInfoController {
	@FXML
	private TextField name;
	@FXML
	private Label info, info2, info3, info4;
	
	@FXML
	public void getInfo(ActionEvent event){
		if(name.getText() == null) {
			return;
		}
		String x = name.getText();
		Client y = Business.getClientInfo(x);
		
		if( y == null){
			info.setText("Error: Name not in system.");
			info2.setText("");
			info3.setText("");
			info4.setText("");
		}
		else{
			info.setText("Name: " + y.getName());
			info2.setText("Email: " + y.getEmail());
			info3.setText("Number: " + y.getPhone());
			info4.setText("DOB: " + y.getBirthDate());
		}	
	}
	
	@FXML
	public void homeButton(ActionEvent event){
		try{
			Main.showEmployeeView();
		}
		catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
}
