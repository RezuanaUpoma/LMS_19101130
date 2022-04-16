
package finalProject;

import finalProject.MenuManager;
import controller.MainMenuController;
import controller.UserController;
import dao.BankDAO;
import dao.BankDAO.tblBill;
import dao.BankDAO.tblBook;
import dao.BankDAO.tblUser;
import model.Bill;
import model.Book;
import model.FormValidations;
import model.Novel;
import model.NumberTextField;
import model.Periodical;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.*;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javafx.event.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class UserActions implements Initializable {
	@FXML
	private Button SearchBook;

	private Stage dialogStage;



	
	@FXML
	private TableView<tblUser> tableView;
	@FXML
	private TableColumn<tblUser, String> itemBookIdColumn;
	@FXML
	private TableColumn<tblUser, String> itemBookNameColumn;
	@FXML
	private TableColumn<tblUser, String> itembookAuthorColumn;
	@FXML
	private TableColumn<tblUser, String> itemIssusedColumn;
	
	
	Integer id1=null;
	
	
	Text txt = new Text("Message");
	

    ObservableList<tblUser> UserList;

	public void initialize(URL location, ResourceBundle resources) {
		
	}
	

	
	
	public void issuedBooks(Integer id)
	{
		id1=id;
		
      System.out.println("Inside issued books");
		itemBookIdColumn.setCellValueFactory(new PropertyValueFactory<tblUser, String>("bookId"));
		itemBookNameColumn.setCellValueFactory(new PropertyValueFactory<tblUser, String>("bookName"));
		itembookAuthorColumn.setCellValueFactory(new PropertyValueFactory<tblUser, String>("bookAuthor"));
		itemIssusedColumn.setCellValueFactory(new PropertyValueFactory<tblUser, String>("dateIssued"));
		UserList = new BankDAO().getUserIssuedBooks(id);
		tableView.setItems(UserList);
	}
	
	public void UserPage() throws IOException{
		 FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/UserPage.fxml"));
			//Inflate the view using the loader
	    AnchorPane root = (AnchorPane) loader.load();
	    //Set window title
	    dialogStage.setTitle("User Main Menu");
	    //Create a scene with the inflated view
	    Scene scene = new Scene(root);
	    
	    
	    UserController controller = loader.getController();
	    controller.setUsername(id1);

		    dialogStage.setScene(scene);
		    
		    //Get the controller instance from the loader
		    //Set the parent stage in the controller
		    controller.setDialogStage(dialogStage);
		    //Show the view
		    dialogStage.show();
	}
	
	
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	
	
	


}