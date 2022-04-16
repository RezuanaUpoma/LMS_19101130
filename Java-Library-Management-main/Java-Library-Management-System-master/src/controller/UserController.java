package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import finalProject.MenuManager;
import finalProject.UserActions;
import model.Login;
import model.memberRecords;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class UserController {

	@FXML
	private Button btnloginButton;
	private Stage dialogStage;
	private Integer username;
	@FXML
	private Label user;

	public void General() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/UserGeneralBooks.fxml"));
			// Inflate the view using the loader
			AnchorPane root = (AnchorPane) loader.load();
			// Set window title
			dialogStage.setTitle("Novel Books");
			// Create a scene with the inflated view
			Scene scene = new Scene(root);
			// Set the scene to the stage
			ViewBookController controller = loader.getController();
			controller.setMemberId(this.username);
			dialogStage.setScene(scene);
			// Get the controller instance from the loader
			// Set the parent stage in the controller
			controller.setDialogStage(dialogStage);
			// Show the view
			dialogStage.show();

		} catch (IOException ex) {
			Logger.getLogger(MenuManager.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void Reference() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/UserReferenceBook.fxml"));
			// Inflate the view using the loader
			AnchorPane root = (AnchorPane) loader.load();
			// Set window title
			dialogStage.setTitle("Periodical Books");
			// Create a scene with the inflated view
			Scene scene = new Scene(root);
			// Set the scene to the stage
			ViewPeriodicalController controller = loader.getController();
			controller.setMemberId(this.username);

			dialogStage.setScene(scene);

			// Get the controller instance from the loader
			// Set the parent stage in the controller
			controller.setDialogStage(dialogStage);
			// Show the view
			dialogStage.show();

		} catch (IOException ex) {
			Logger.getLogger(MenuManager.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	public void btnviewIssued() {

		try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/IssuedBooks_User.fxml"));
			// Inflate the view using the loader
			AnchorPane root = (AnchorPane) loader.load();
			// Set window title
			dialogStage.setTitle("Issued Books");
			// Create a scene with the inflated view
			Scene scene = new Scene(root);
			// Set the scene to the stage
			UserActions controller = loader.getController();
			System.out.println("got username from comm" + this.username);
			controller.issuedBooks(this.username);

			dialogStage.setScene(scene);

			// Get the controller instance from the loader
			// Set the parent stage in the controller
			controller.setDialogStage(dialogStage);
			// Show the view
			dialogStage.show();

		} catch (IOException ex) {
			Logger.getLogger(MenuManager.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	public final Integer getUsername() {
		return username;
	}

	public final void setUsername(Integer username) {
		this.username = username;
	}

	public void Billpayment() {

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/PayBill.fxml"));
			// Inflate the view using the loader
			AnchorPane root = (AnchorPane) loader.load();
			// Set window title
			dialogStage.setTitle("Pay Bill");
			// Create a scene with the inflated view
			Scene scene = new Scene(root);
			// Set the scene to the stage
			UserBillPayController controller = loader.getController();
			controller.viewPaybills(this.username);
			dialogStage.setScene(scene);
			// Get the controller instance from the loader
			// Set the parent stage in the controller
			controller.setDialogStage(dialogStage);
			// Show the view
			dialogStage.show();

		} catch (IOException ex) {
			Logger.getLogger(MenuManager.class.getName()).log(Level.SEVERE, null, ex);
		}

	}
	
	public void Signout(){
		  try{
		  FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/LoginPage.fxml"));
			//Inflate the view using the loader
	    AnchorPane root = (AnchorPane) loader.load();
	    //Set window title
	    dialogStage.setTitle("Login Page");
	    //Create a scene with the inflated view
	    Scene scene = new Scene(root);
	    //Set the scene to the stage
	    LoginController controller = loader.getController();

	    dialogStage.setScene(scene);
	    //Get the controller instance from the loader
	    //Set the parent stage in the controller
	    controller.setDialogStage(dialogStage);
	    //Show the view
	    dialogStage.show();
	   
	  } catch (IOException ex) {
	    Logger.getLogger(MenuManager.class.getName()).log(Level.SEVERE, null, ex);
	  }
	  }

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;

	}

	public static class loginlist {

		private int username;
		private String userpassword;

		public final int getUsername() {
			return username;
		}

		public final void setUsername(int username) {
			this.username = username;
		}

		public final String getUserpassword() {
			return userpassword;
		}

		public final void setUserpassword(String userpassword) {
			this.userpassword = userpassword;
		}

	}

}
