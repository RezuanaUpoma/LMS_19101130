package controller;

/**
 * Login Controller
 */

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import finalProject.MenuManager;
import finalProject.UserActions;
import controller.UserController.loginlist;
import dao.BankDAO;
import dao.BankDAO.Listlogin;
import model.Login;
import model.memberRecords;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoginController {
	@FXML
	private TextField username;
	@FXML
	private TextField userpassword;
	@FXML
	private Button btnloginButton;
	private Stage dialogStage;
	@FXML
	public Label user;
	@FXML
	public Label pass;

	int pusername1;

	String id;
	String password;
	Integer convertedId;

	@FXML
	Label check;

	@SuppressWarnings("unused")
	public void btnloginButton() throws Exception 
	{
		final Login model = null;
		try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/UserPage.fxml"));
			AnchorPane root = (AnchorPane) loader.load();
			dialogStage.setTitle("Welome to User Page");
			Scene scene = new Scene(root);
			BankDAO pb = new BankDAO();
			if (checkEmptycredentials()) 
			{
				this.check.setText("All fields are Manadatory");
			}

			else if (checkCredentials()) 
			{
				this.check.setText("Invalid UserName Password..");
			}

			else {

				loginlist pl = new loginlist();

				UserController controller = loader.getController();
				controller.setUsername(Integer.parseInt(id));

				System.out.println("Login controller UserName  is" + controller.getUsername());
				dialogStage.setScene(scene);
				controller.setDialogStage(dialogStage);
				dialogStage.show();
			}
		} catch (IOException ex) {
			Logger.getLogger(MenuManager.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private boolean checkEmptycredentials() {
		boolean check = false;

		if (username.getText().isEmpty() || pass.getText().isEmpty()) {
			return true;
		}

		return check;
	}

	public boolean checkCredentials() 
	{

		boolean check = false;
		id = username.getText();
		password = userpassword.getText();
		System.out.println("User Given UserID is :" + id);
		System.out.println("User Entered Password is" + password);
		convertedId = Integer.parseInt(id);
		BankDAO pb = new BankDAO();
		if (!pb.getLoginCredentials(convertedId, password)) 
		{
			check = true;
		}
		return check;
	}

	public boolean checkAdminCredentials() 
	{
		boolean check = true;
		id = username.getText();
		Integer convertedId = Integer.parseInt(id);
		password = userpassword.getText();
		String convertedPassword = password;
		System.out.println("Admin Given UserID is" + id);
		System.out.println("Admin Given Password is" + password);
		convertedId = Integer.parseInt(id);
		if (convertedId == 1 && convertedPassword.equals("1")) 
		{
			check = false;
		}
		return check;
	}

	public void btnloginAdminButton() throws Exception {

		try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MainMenuPage.fxml"));
			// Inflate the view using the loader
			AnchorPane root = (AnchorPane) loader.load();
			// Set window title
			dialogStage.setTitle("Welcome to Admin Page");
			// Create a scene with the inflated view
			Scene scene = new Scene(root);
			BankDAO pb = new BankDAO();
			if (checkEmptycredentials()) 
			{
				this.check.setText("UserID and Password are Manadatory");
			}
			else if (checkAdminCredentials()) 
			{
				this.check.setText("Invalid UserName and Password");
			}

			else {

				MainMenuController controller = loader.getController();
				dialogStage.setScene(scene);
				controller.setDialogStage(dialogStage);
				dialogStage.show();
			}
		} catch (IOException ex) {
			Logger.getLogger(MenuManager.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;

	}
}
