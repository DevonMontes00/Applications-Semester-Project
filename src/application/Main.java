package application;

import java.io.IOException;

//import application.view.EmployeeController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;


public class Main extends Application {
	public static Stage primaryStage;
	private static AnchorPane loginLayout;
	@Override
	public void start(Stage primaryStage) throws IOException {
		Main.primaryStage = primaryStage;
		Main.primaryStage.setTitle("Bam-Cal");
		showLoginView();
	}
	
	public static void showLoginView() throws IOException{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/Login.fxml"));
		loginLayout = loader.load();
		Scene scene = new Scene(loginLayout);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void showEmployeeView() throws IOException{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/Employee.fxml"));
		AnchorPane employeeLayout = loader.load();
		loginLayout.getChildren().setAll(employeeLayout);
	}
	
	public static void showClientView() throws IOException{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/Client.fxml"));
		AnchorPane clientLayout = loader.load();
		loginLayout.getChildren().setAll(clientLayout);
	}
	
	public static void showCreateNewUserView() throws IOException{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/CreateNewUser.fxml"));
		AnchorPane NewUserLayout = loader.load();
		loginLayout.getChildren().setAll(NewUserLayout);
	}
	public static void showCreateNewClientView() throws IOException{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/CreateNewClient.fxml"));
		AnchorPane NewClientLayout = loader.load();
		loginLayout.getChildren().setAll(NewClientLayout);
	}
	public static void showCreateNewEmployeeView() throws IOException{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/CreateNewEmployee.fxml"));
		AnchorPane NewEmployeeLayout = loader.load();
		loginLayout.getChildren().setAll(NewEmployeeLayout);
	}
	
	public static void showAddAppointment() throws IOException{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/AddAppointment.fxml"));
		AnchorPane clientLayout = loader.load();
		loginLayout.getChildren().setAll(clientLayout);
	}
	
	public static void showDeleteEmployee() throws IOException{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/DeleteAccount.fxml"));
		AnchorPane clientLayout = loader.load();
		loginLayout.getChildren().setAll(clientLayout);
	}
	
	public static void showDeleteAppointment() throws IOException{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/DeleteAppointment.fxml"));
		AnchorPane clientLayout = loader.load();
		loginLayout.getChildren().setAll(clientLayout);
	}
	
	public static void showEditAccount() throws IOException{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/EditAccount.fxml"));
		AnchorPane editAccount = loader.load();
		loginLayout.getChildren().setAll(editAccount);
	}

	
	public static void showGetClientInfo() throws IOException{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/GetClientInfo.fxml"));
		AnchorPane clientLayout = loader.load();
		loginLayout.getChildren().setAll(clientLayout);
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}


