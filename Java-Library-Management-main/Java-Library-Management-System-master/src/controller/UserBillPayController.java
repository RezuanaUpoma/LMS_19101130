
package controller;

import finalProject.MenuManager;
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

public class UserBillPayController implements Initializable {
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
	private TableColumn<tblBill, String> itemStatColumn;

	@FXML
	Label check;

	Integer id1 = null;

	Text txt = new Text("Message");

	ObservableList<tblBill> billList;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	public void btnBillPay() {

		Bill bill = new Bill();

		System.out.println("Inside update1");
		txt.setFont(Font.font(20));

		if (tableView.getSelectionModel().isEmpty()) {
			this.check.setText("Please select any of the row first");

		} else if ((billList.get(tableView.getSelectionModel().getSelectedIndex()).getBillStatus().equals("P"))) {
			this.check.setText("Bill Has Been Paid Already!!!!!");

		} else {

			Integer no = Integer.parseInt(billList.get(tableView.getSelectionModel().getSelectedIndex()).getBillNo());
			System.out.println("id is" + no);

			Integer bid = Integer.parseInt(billList.get(tableView.getSelectionModel().getSelectedIndex()).getBookId());

			System.out.println("name is" + bid);

			Float billam = Float
					.parseFloat(billList.get(tableView.getSelectionModel().getSelectedIndex()).getBillAmount());

			Integer libid = Integer
					.parseInt(billList.get(tableView.getSelectionModel().getSelectedIndex()).getLibrarianId());
			System.out.println("autor is is" + libid);

			Integer mid = Integer
					.parseInt(billList.get(tableView.getSelectionModel().getSelectedIndex()).getMemberId());

			String billda = billList.get(tableView.getSelectionModel().getSelectedIndex()).getBillDate();
			String dateis = billList.get(tableView.getSelectionModel().getSelectedIndex()).getDateIssued();
			String datere = billList.get(tableView.getSelectionModel().getSelectedIndex()).getDateReturned();
			System.out.println("no of books is is" + mid);
			System.out.println("bill date" + billda);

			System.out.println("issued is is" + dateis);

			System.out.println("returned ooks is is" + datere);


			bill.setBillNo(no);
			bill.setBillDate(java.sql.Date.valueOf(billda));
			bill.setBillAmount(billam);
			bill.setBookId(bid);
			bill.setLibId(libid);
			bill.setMemberId(mid);
			bill.setDateIssued(java.sql.Date.valueOf(dateis));
			bill.setDateReturned(java.sql.Date.valueOf(datere));

			System.out.println("Inside update3");

			billList.remove(tableView.getSelectionModel().getSelectedIndex());

			
			BankDAO.tblBill billtable;
			BankDAO bd = new BankDAO();
			billtable = bd.payBill(bill);

			int books = bd.getLibraryBooksBill(bill);
			bd.setUserReturnedBooks(bill);
			System.out.println("Number of Books to be updated" + books);

			bd.PsetBooks(bill, books + 1);

			billList.add(billtable);

			tableView.setItems(billList);
			clearPostBillform();

			// tableView.setItems(bookList);
		}

	}

	public void viewPaybills(Integer id) {
		id1 = id;

		tableView.setEditable(true);

		itemBiNoColumn.setCellValueFactory(new PropertyValueFactory<tblBill, String>("billNo"));
		itemBidNoColumn.setCellValueFactory(new PropertyValueFactory<tblBill, String>("billDate"));
		itemAmtColumn.setCellValueFactory(new PropertyValueFactory<tblBill, String>("billAmount"));
		itemBoIdColumn.setCellValueFactory(new PropertyValueFactory<tblBill, String>("bookId"));
		itemMeIdColumn.setCellValueFactory(new PropertyValueFactory<tblBill, String>("memberId"));
		itemLiIdColumn.setCellValueFactory(new PropertyValueFactory<tblBill, String>("librarianId"));
		itemIssColumn.setCellValueFactory(new PropertyValueFactory<tblBill, String>("dateIssued"));
		itemRetColumn.setCellValueFactory(new PropertyValueFactory<tblBill, String>("dateReturned"));
		itemStatColumn.setCellValueFactory(new PropertyValueFactory<tblBill, String>("billStatus"));

		billList = new BankDAO().GetTransactionBill(id);

		tableView.setItems(billList);
		check.setTextFill(Color.web("#FF0000"));
		check.setFont(Font.font("20"));
	}

	public void mainPage() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/UserPage.fxml"));
		// Inflate the view using the loader
		AnchorPane root = (AnchorPane) loader.load();
		// Set window title
		dialogStage.setTitle("User Main Menu");
		// Create a scene with the inflated view
		Scene scene = new Scene(root);

		UserController controller = loader.getController();
		controller.setUsername(id1);

		dialogStage.setScene(scene);

		// Get the controller instance from the loader
		// Set the parent stage in the controller
		controller.setDialogStage(dialogStage);
		// Show the view
		dialogStage.show();
	}

	private void clearPostBillform() {
		// TODO Auto-generated method stub

		this.check.setText("Bill has been Paid!!!!Thank you");

	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

}