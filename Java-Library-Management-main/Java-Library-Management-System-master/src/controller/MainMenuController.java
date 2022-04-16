package controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import finalProject.MemberDetails;
import finalProject.MenuManager;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class MainMenuController {
  @FXML private TextField user;
  @FXML private TextField password;
  @FXML private Button loginButton;
	private Stage dialogStage;

  public void GeneralBook() {
	  try {
		  FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/GeneralBooks.fxml"));
          AnchorPane root = (AnchorPane) loader.load();
          dialogStage.setTitle("Welcome to General Books section");
          Scene scene = new Scene(root);
          ViewBookController controller = loader.getController();
          dialogStage.setScene(scene);
          controller.setDialogStage(dialogStage);
          dialogStage.show();
	    } catch (IOException ex) {
	      Logger.getLogger(MenuManager.class.getName()).log(Level.SEVERE, null, ex);
	    }
}

  public void ReferenceBook(){
	  try{
	  FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ReferenceBooks.fxml"));
		//Inflate the view using the loader
    AnchorPane root = (AnchorPane) loader.load();
    //Set window title
    dialogStage.setTitle("Welcome to Reference Books");
    //Create a scene with the inflated view
    Scene scene = new Scene(root);
    //Set the scene to the stage
    ViewPeriodicalController controller = loader.getController();
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
	  public void Addmember(){
		  try{
		  FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/UserDetailPage.fxml"));
			//Inflate the view using the loader
	    AnchorPane root = (AnchorPane) loader.load();
	    //Set window title
	    dialogStage.setTitle("Welcome to User Details page");
	    //Create a scene with the inflated view
	    Scene scene = new Scene(root);
	    //Set the scene to the stage
	    MemberDetails controller = loader.getController();

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
	  public void issueBooks(){
		  try{
		  FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/IssuedBooks_User.fxml"));
			//Inflate the view using the loader
	    AnchorPane root = (AnchorPane) loader.load();
	    //Set window title
	    dialogStage.setTitle("Books Issued to User");
	    //Create a scene with the inflated view
	    Scene scene = new Scene(root);
	    //Set the scene to the stage
	    MemberDetails controller = loader.getController();
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
	  
	  public void Createbill(){
		  try{
		  FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/PreBill.fxml"));
			//Inflate the view using the loader
	    AnchorPane root = (AnchorPane) loader.load();
	    //Set window title
	    dialogStage.setTitle("Before Bill Details");
	    //Create a scene with the inflated view
	    Scene scene = new Scene(root);
	    //Set the scene to the stage
	    BillController controller = loader.getController();

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
	  
	  public void CreatedBill(){
		  try{
		  FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/PostBill.fxml"));
			//Inflate the view using the loader
	    AnchorPane root = (AnchorPane) loader.load();
	    //Set window title
	    dialogStage.setTitle("Generated Bill Details");
	    //Create a scene with the inflated view
	    Scene scene = new Scene(root);
	    //Set the scene to the stage
	    PostBillController controller = loader.getController();
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

    
  
}
