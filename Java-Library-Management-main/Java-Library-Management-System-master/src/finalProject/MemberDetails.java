
package finalProject;

import finalProject.MenuManager;
import controller.MainMenuController;
import dao.BankDAO;
import dao.BankDAO.tblBook;
import dao.BankDAO.tblMember;
import model.Book;
import model.FormValidations;
import model.Novel;
import model.NumberTextField;
import model.Periodical;
import model.memberRecords;

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

public class MemberDetails implements Initializable {
	@FXML
	private Button SearchBook;

	private Stage dialogStage;    

	@FXML
	private TableView<tblMember> tableView;
	@FXML
	private TableColumn<tblMember, String> itemIdColumn;
	@FXML
	private TableColumn<tblMember, String> itemNameColumn;
	@FXML
	private TableColumn<tblMember, String> itemAddColumn;
	@FXML
	private TableColumn<tblMember, String> itemPhColumn;
	@FXML
	private TableColumn<tblMember, String> itemTypColumn;
	@FXML
	private TableColumn<tblMember, String> itemNoColumn;
	

	@FXML
	private TextField memberName;
	@FXML
	private TextField memberAddress;
	@FXML
	private TextField memberPhone;
	@FXML
	private TextField typeOfMember;
	@FXML
	private TextField noOfBooksIssued;
	@FXML
	Label check;
	Text txt = new Text("Notifications");
	ObservableList<tblMember> memberList;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tableView.setEditable(true);
		itemIdColumn.setCellValueFactory(new PropertyValueFactory<tblMember, String>("memberId"));
		itemNameColumn.setCellValueFactory(new PropertyValueFactory<tblMember, String>("memberName"));
		itemAddColumn.setCellValueFactory(new PropertyValueFactory<tblMember, String>("memberAddress"));
		itemPhColumn.setCellValueFactory(new PropertyValueFactory<tblMember, String>("memberPhone"));
		itemTypColumn.setCellValueFactory(new PropertyValueFactory<tblMember, String>("typeOfMember"));
		itemNoColumn.setCellValueFactory(new PropertyValueFactory<tblMember, String>("noOfBooksIssued"));
		memberList = new BankDAO().getAllMemberDetails();
		tableView.setItems(new BankDAO().getAllMemberDetails());
		check.setTextFill(Color.web("#FF0000"));
		check.setFont(Font.font("20"));
	}

	public void addUser() throws Exception 
	{
		memberRecords member=new memberRecords();
		System.out.println("Length of phone is"+memberPhone.getText().length());
		System.out.println("Type of Memeber  is"+typeOfMember.getText());
		if ((memberName.getText().isEmpty()) || (memberAddress.getText().isEmpty()) || (memberPhone.getText().isEmpty())
				|| (typeOfMember.getText().isEmpty()) || (noOfBooksIssued.getText().isEmpty())) {
			this.check.setText("Please fill all the fields");
		}
		else if(memberPhone.getText().length()>=10)
		{
			this.check.setText("Length should be 10 and type should be Either S or P");

		}
		
	else 
	{
			if (!(memberName.getText().isEmpty())) {
				member.setMemberName(memberName.getText());
			} 
			if (!(memberAddress.getText().isEmpty())) {
				member.setMemberAddress(memberAddress.getText());
			}
			if (!(memberPhone.getText().isEmpty())) {
				member.setMemberPhone(Long.parseLong(memberPhone.getText()));
			}
			if (!(typeOfMember.getText().isEmpty())) {
				member.setTypeOfMember(typeOfMember.getText());
			}
			
			if (!(noOfBooksIssued.getText().isEmpty())) {
				member.setNoOfBooksIssued(Integer.parseInt(noOfBooksIssued.getText()));
			}
			
			BankDAO.tblMember membertable;
			BankDAO bd=new BankDAO();
			membertable=bd.AddMember(member);
			memberList.add(membertable);
			tableView.setItems(memberList);
			clearAddform();
		}
		System.out.println("Inside update3");
	}

	public void updateUser()
	{
		memberRecords member=new memberRecords();
		System.out.println("Inside update1");
		txt.setFont(Font.font(20));
		if(tableView.getSelectionModel().isEmpty()){
			this.check.setText("Please select any of the row first");
		}
		else if(memberPhone.getText().length()>=10)
		{
			this.check.setText("Length should be 10 and type should be Either S or P");

		}
		else{

		Integer id = Integer.parseInt(memberList.get(tableView.getSelectionModel().getSelectedIndex()).getMemberId());
		System.out.println("id is" + id);

		String name = memberList.get(tableView.getSelectionModel().getSelectedIndex()).getMemberName();

		System.out.println("name is" + name);

		String add = memberList.get(tableView.getSelectionModel().getSelectedIndex()).getMemberAddress();
		System.out.println(add);

		Long phon = Long.parseLong(memberList.get(tableView.getSelectionModel().getSelectedIndex()).getMemberPhone());
		System.out.println("no of books is is" + phon);

		String type = memberList.get(tableView.getSelectionModel().getSelectedIndex()).getTypeOfMember();
		System.out.println("type is" + type);

	

		Integer no = Integer.parseInt(memberList.get(tableView.getSelectionModel().getSelectedIndex()).getNoOfBooksIssued());
		System.out.println("limit is is" + no);
		member.setMemberId(id);
		member.setMemberName(name);
		member.setMemberAddress(add);
		member.setMemberPhone(phon);
		member.setNoOfBooksIssued(no);
		member.setTypeOfMember(type);

		if (!(memberName.getText().isEmpty())) {
			member.setMemberName(memberName.getText());
		} 
		if (!(memberAddress.getText().isEmpty())) {
			member.setMemberAddress(memberAddress.getText());
		}
		if (!(memberPhone.getText().isEmpty())) {
			member.setMemberPhone(Long.parseLong(memberPhone.getText()));
		}
		if (!(typeOfMember.getText().isEmpty())) {
			member.setTypeOfMember(typeOfMember.getText());
		}
		
		if (!(noOfBooksIssued.getText().isEmpty())) {
			member.setNoOfBooksIssued(Integer.parseInt(noOfBooksIssued.getText()));
		}
		if (!(typeOfMember.getText().isEmpty())) {
			member.setTypeOfMember(typeOfMember.getText());
		}
		System.out.println("Inside update3");
		memberList.remove(tableView.getSelectionModel().getSelectedIndex());

		BankDAO.tblMember membertable;
		BankDAO bd=new BankDAO();
		membertable=bd.updateMember(member);
		memberList.add(membertable);
		clearUpdateform();
		tableView.setItems(memberList);
		}
	}
	
	public void mainPage() throws IOException{
		 FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MainMenuPage.fxml"));
			//Inflate the view using the loader
	    AnchorPane root = (AnchorPane) loader.load();
	    //Set window title
	    dialogStage.setTitle("Admin Menu Main");
	    //Create a scene with the inflated view
	    Scene scene = new Scene(root);	    
		  MainMenuController controller = loader.getController();
		    dialogStage.setScene(scene);
		    //Get the controller instance from the loader
		    //Set the parent stage in the controller
		    controller.setDialogStage(dialogStage);
		    //Show the view
		    dialogStage.show();
	}
	private void clearform() 
	{
		memberName.clear();
		memberAddress.clear();
		memberPhone.clear();
		typeOfMember.clear();
		noOfBooksIssued.clear();
		this.check.setText("");
		tableView.getSelectionModel().clearSelection();	
	}
	
private void clearUpdateform() 
{
		memberName.clear();
		memberAddress.clear();
		memberPhone.clear();
		typeOfMember.clear();
		noOfBooksIssued.clear();
		this.check.setText("Member has been updated successfully");
		tableView.getSelectionModel().clearSelection();	
	}

private void clearAddform() 
{
	memberName.clear();
	memberAddress.clear();
	memberPhone.clear();
	typeOfMember.clear();
	noOfBooksIssued.clear();
	this.check.setText("Member has been added successfully");
	tableView.getSelectionModel().clearSelection();	
}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}


}