
package controller;

/**
 * Post Bill Controller
 */
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

public class PostBillController implements Initializable {
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

	ObservableList<tblBill> billList;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
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

		billList = new BankDAO().getCreatedBillDetails();

		tableView.setItems(billList);
		check.setTextFill(Color.web("#FF0000"));
		check.setFont(Font.font("20"));

	}

	public void updateCreatedBill() 
	{

		Bill bill = new Bill();
		txt.setFont(Font.font(20));
		if (tableView.getSelectionModel().isEmpty()) {
			this.check.setText("please select atleast one row to update bill");
		} 
		else 
		{

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

			String status = billList.get(tableView.getSelectionModel().getSelectedIndex()).getBillStatus();

			System.out.println("bill date" + billda);

			System.out.println("issued is is" + dateis);

			System.out.println("returned ooks is is" + datere);

			System.out.println("Status in Post bill is" + status);


			bill.setBillNo(no);
			bill.setBillDate(java.sql.Date.valueOf(billda));
			bill.setBillAmount(billam);
			bill.setBookId(bid);
			bill.setLibId(libid);
			bill.setMemberId(mid);
			bill.setDateIssued(java.sql.Date.valueOf(dateis));
			bill.setDateReturned(java.sql.Date.valueOf(datere));
			bill.setBillStatus(status);

			if (billDate.getValue() != null) {
				bill.setBillDate(java.sql.Date.valueOf(billDate.getValue()));

			}

			if (!(billAmount.getText().isEmpty())) {
				bill.setBillAmount(Float.parseFloat(billAmount.getText()));
			}

			System.out.println("Inside update3");

			billList.remove(tableView.getSelectionModel().getSelectedIndex());
			BankDAO.tblBill billtable;
			BankDAO bd = new BankDAO();
			billtable = bd.updateBill(bill);
			billList.add(billtable);
			tableView.setItems(billList);
			clearPostBillform();
		}

	}

	public void mainPage() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MainMenuPage.fxml"));
		// Inflate the view using the loader
		AnchorPane root = (AnchorPane) loader.load();
		// Set window title
		dialogStage.setTitle("User Main Menu");
		// Create a scene with the inflated view
		Scene scene = new Scene(root);
		MainMenuController controller = loader.getController();
		dialogStage.setScene(scene);
		controller.setDialogStage(dialogStage);
		dialogStage.show();
	}

	private void clearPostBillform() 
	{
		// TODO Auto-generated method stub
		billDate.getEditor().clear();
		billAmount.clear();
		this.check.setText("Data row updated successfully");

	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

}