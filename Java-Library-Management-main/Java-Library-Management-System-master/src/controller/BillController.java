
package controller;

import finalProject.MenuManager;
import dao.BankDAO;
import dao.BankDAO.tblBill;
import dao.BankDAO.tblBook;
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

public class BillController implements Initializable {
	@FXML
	private Button SearchBook;
	private Stage dialogStage;  

	@FXML
	private TableView<tblBill> tableView;
	@FXML
	private TableColumn<tblBill, String> itemBiNoColumn;
	@FXML
	private TableColumn<tblBill, String> itemBidNoColumn;
	@FXML
	private TableColumn<tblBill, String> itemAmtColumn;
	@FXML
	private TableColumn<tblBill, String> itemBoIdColumn;
	@FXML
	private TableColumn<tblBill, String> itemMeIdColumn;
	@FXML
	private TableColumn<tblBill, String> itemLiIdColumn;
	@FXML
	private TableColumn<tblBill, String> itemIssColumn;
	@FXML
	private TableColumn<tblBill, String> itemRetColumn;

	@FXML
	private DatePicker billDate;
	@FXML
	private TextField billAmount;
	@FXML
	private TextField bookId;
	@FXML
	private TextField memberId;
	@FXML
	private TextField librarianId;
	@FXML
	private DatePicker dateIssued;
	@FXML
	private DatePicker dateReturned;

	@FXML
	Label check;

	Text txt = new Text("Message");

	ObservableList<tblBill> pbillList;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tableView.setEditable(true);

		itemBiNoColumn.setCellValueFactory(new PropertyValueFactory<tblBill, String>("billNo"));
		itemAmtColumn.setCellValueFactory(new PropertyValueFactory<tblBill, String>("billAmount"));
		itemBoIdColumn.setCellValueFactory(new PropertyValueFactory<tblBill, String>("bookId"));
		itemMeIdColumn.setCellValueFactory(new PropertyValueFactory<tblBill, String>("memberId"));
		itemLiIdColumn.setCellValueFactory(new PropertyValueFactory<tblBill, String>("librarianId"));
		itemIssColumn.setCellValueFactory(new PropertyValueFactory<tblBill, String>("dateIssued"));
		itemRetColumn.setCellValueFactory(new PropertyValueFactory<tblBill, String>("dateReturned"));
		itemBidNoColumn.setCellValueFactory(new PropertyValueFactory<tblBill, String>("billDate"));
		pbillList = new BankDAO().getBilldetails();
		tableView.setItems(pbillList);
		check.setTextFill(Color.web("#FF0000"));
		check.setFont(Font.font("20"));

	}


	public void createBill() throws Exception 
	{
		Bill bill = new Bill();
		txt.setFont(Font.font(20));

		if ((billAmount.getText().isEmpty()) || (billDate.getValue() == null) || (dateReturned.getValue() == null)) 
		{
			this.check.setText("All the fields are Mandataory");

		} 
		else if (tableView.getSelectionModel().isEmpty()) 
		{
			this.check.setText("Please select atleast one Row to update");
		}

		else {

			String pbillda = pbillList.get(tableView.getSelectionModel().getSelectedIndex()).getBillDate();
			String pdateis = pbillList.get(tableView.getSelectionModel().getSelectedIndex()).getDateIssued();
			String pdatere = pbillList.get(tableView.getSelectionModel().getSelectedIndex()).getDateReturned();
			Integer pno = Integer.parseInt(pbillList.get(tableView.getSelectionModel().getSelectedIndex()).getBillNo());
			//System.out.println("id is" + pno);
            Integer pbid = Integer.parseInt(pbillList.get(tableView.getSelectionModel().getSelectedIndex()).getBookId());
			//System.out.println("Name is :" + pbid);

			if (!(billAmount.getText().isEmpty())) 
			{
				bill.setBillAmount(Float.parseFloat(billAmount.getText()));
			}
			Integer plibid = Integer.parseInt(pbillList.get(tableView.getSelectionModel().getSelectedIndex()).getLibrarianId());
			//System.out.println("autor is is" + plibid);

			Integer pmid = Integer.parseInt(pbillList.get(tableView.getSelectionModel().getSelectedIndex()).getMemberId());
			//System.out.println("no of books is is" + pmid);

			if (pbillda == null) 
			{
				bill.setBillDate(java.sql.Date.valueOf(billDate.getValue()));
			}
			

			if (pdatere == null) 
			{
				bill.setDateReturned(java.sql.Date.valueOf(dateReturned.getValue()));
			}
			bill.setBillNo(pno);
			bill.setBookId(pbid);
			bill.setLibId(plibid);
			bill.setMemberId(pmid);
			bill.setDateIssued(java.sql.Date.valueOf(pdateis));
			BankDAO bd = new BankDAO();
			if (bd.createBill(bill)) 
			{
				this.check.setText("Bill created successfully");

			}
			else 
			{
				this.check.setText("Bill has already has been created for this ID");

			}

			pbillList.remove(tableView.getSelectionModel().getSelectedIndex());

			clearBill();
			tableView.setItems(pbillList);
		}

	}

	public void updatePostBill() 
	{
		Bill bill = new Bill();
		//System.out.println("Inside update1");
		txt.setFont(Font.font(18));
		
		if (tableView.getSelectionModel().isEmpty()) 
		{
			this.check.setText("Select one row from the table");

		} 
		else 
		{
			Integer pno = Integer.parseInt(pbillList.get(tableView.getSelectionModel().getSelectedIndex()).getBillNo());
			//System.out.println("ID is :" + pno);
			Integer pbid = Integer.parseInt(pbillList.get(tableView.getSelectionModel().getSelectedIndex()).getBookId());
			//System.out.println("Name is :" + pbid);
			Float pbillam = Float.parseFloat(pbillList.get(tableView.getSelectionModel().getSelectedIndex()).getBillAmount());

			Integer plibid = Integer.parseInt(pbillList.get(tableView.getSelectionModel().getSelectedIndex()).getLibrarianId());
			//System.out.println("autor is is" + plibid);

			Integer pmid = Integer.parseInt(pbillList.get(tableView.getSelectionModel().getSelectedIndex()).getMemberId());
			//System.out.println("Number of books is :" + pmid);

			String pbillda = pbillList.get(tableView.getSelectionModel().getSelectedIndex()).getBillDate();
			String pdateis = pbillList.get(tableView.getSelectionModel().getSelectedIndex()).getDateIssued();
			String pdatere = pbillList.get(tableView.getSelectionModel().getSelectedIndex()).getDateReturned();


			bill.setBillNo(pno);
			bill.setBillDate(java.sql.Date.valueOf(pbillda));
			bill.setBillAmount(pbillam);
			bill.setBookId(pbid);
			bill.setLibId(plibid);
			bill.setMemberId(pmid);
			bill.setDateIssued(java.sql.Date.valueOf(pdateis));
			bill.setDateReturned(java.sql.Date.valueOf(pdatere));

			if (pbillda != null) {
				bill.setBillDate(java.sql.Date.valueOf(billDate.getValue()));
				 }
			if (pdateis != null) {
				bill.setDateIssued(java.sql.Date.valueOf(dateIssued.getValue()));
				 }

			if (pdatere != null) {
				bill.setDateReturned(java.sql.Date.valueOf(dateReturned.getValue()));
				 }

			if (!(billAmount.getText().isEmpty())) {
				bill.setBillAmount(Float.parseFloat(billAmount.getText()));
			}

			if (!(bookId.getText().isEmpty())) {
				bill.setBookId(Integer.parseInt(bookId.getText()));
			}
			if (!(memberId.getText().isEmpty())) {
				bill.setMemberId(Integer.parseInt(memberId.getText()));
			}
			if (!(librarianId.getText().isEmpty())) {
				bill.setLibId(Integer.parseInt(librarianId.getText()));
			}

			pbillList.remove(tableView.getSelectionModel().getSelectedIndex());
			BankDAO.tblBill billtable;
			BankDAO bd = new BankDAO();
			billtable = bd.updateBill(bill);

			pbillList.add(billtable);

			clearPostBill();
			tableView.setItems(pbillList);
		}

	}

	public void issueBook() throws Exception 
	{

	

	}

	public void mainPage() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MainMenuPage.fxml"));
		
		AnchorPane root = (AnchorPane) loader.load();
		dialogStage.setTitle("Welcome to User Main Page");
		Scene scene = new Scene(root);
		MainMenuController controller = loader.getController();
		dialogStage.setScene(scene);
		controller.setDialogStage(dialogStage);
		dialogStage.show();
	}

	private void clearBill() {
		billDate.getEditor().clear();
		dateReturned.getEditor().clear();
		billAmount.clear();

	}

	private void clearPostBill() {
		memberId.clear();
		librarianId.clear();
		billDate.getEditor().clear();
		dateIssued.getEditor().clear();
		dateReturned.getEditor().clear();
		memberId.clear();
		billAmount.clear();
		this.check.setText("Data Row  has been updated successfully");

	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

}