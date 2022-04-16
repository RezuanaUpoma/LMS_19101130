
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
import java.util.ArrayList;
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

public class ViewPeriodicalController implements Initializable {
	@FXML
	private Button SearchBook;

	private Stage dialogStage;

	@FXML
	private TableView<tblBook> tableView;
	@FXML
	private TableColumn<tblBook, String> itemIdColumn;
	@FXML
	private TableColumn<tblBook, String> itemNameColumn;
	@FXML
	private TableColumn<tblBook, String> itemAutColumn;
	@FXML
	private TableColumn<tblBook, String> itemNoColumn;
	@FXML
	private TableColumn<tblBook, String> itemTypColumn;
	@FXML
	private TableColumn<tblBook, String> itemStatColumn;
	@FXML
	private TableColumn<tblBook, String> itemLimiColumn;

	@FXML
	private TableColumn<tblBook, String> itemForColumn;
	@FXML
	private TableColumn<tblBook, String> itemSchColumn;
	@FXML
	private TableColumn<tblBook, String> itemEdiColumn;

	@FXML
	private TextField bookName;
	@FXML
	private TextField bookAuthor;
	@FXML
	private TextField noOfBooks;
	@FXML
	private TextField typeOfBooks;
	@FXML
	private TextField bookStatus;

	@FXML
	private TextField bookLimitDays;

	@FXML
	private TextField format;
	@FXML
	private TextField schedule;
	@FXML
	private TextField edition;
	@FXML
	private TextField memberId;
	@FXML
	private TextField librarianId;
	@FXML
	Label check;
	@FXML
	Label check1;
	@FXML
	DatePicker dateee;
	Integer id1;

	Text txt = new Text("Message");

	ObservableList<tblBook> bookList;
	ObservableList<tblBill> billList;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tableView.setEditable(true);

		itemIdColumn.setCellValueFactory(new PropertyValueFactory<tblBook, String>("bookId"));
		itemNameColumn.setCellValueFactory(new PropertyValueFactory<tblBook, String>("bookName"));
		itemAutColumn.setCellValueFactory(new PropertyValueFactory<tblBook, String>("bookAuthor"));
		itemNoColumn.setCellValueFactory(new PropertyValueFactory<tblBook, String>("noOfBooks"));
		itemTypColumn.setCellValueFactory(new PropertyValueFactory<tblBook, String>("typeOfBooks"));
		itemStatColumn.setCellValueFactory(new PropertyValueFactory<tblBook, String>("bookStatus"));
		itemLimiColumn.setCellValueFactory(new PropertyValueFactory<tblBook, String>("bookLimitDays"));

		itemForColumn.setCellValueFactory(new PropertyValueFactory<tblBook, String>("format"));
		itemSchColumn.setCellValueFactory(new PropertyValueFactory<tblBook, String>("schedule"));
		itemEdiColumn.setCellValueFactory(new PropertyValueFactory<tblBook, String>("edition"));

		bookList = new BankDAO().getAllReferenceProducts();

		tableView.setItems(new BankDAO().getAllReferenceProducts());

	}

	public void addReferenceBook() throws Exception {

		Book book = new Book();
		Periodical periodical = new Periodical();

		if ((bookName.getText().isEmpty()) || (bookAuthor.getText().isEmpty()) || (noOfBooks.getText().isEmpty())
				|| (typeOfBooks.getText().isEmpty()) || (bookStatus.getText().isEmpty())
				|| (bookLimitDays.getText().isEmpty()) || (format.getText().isEmpty()) || (schedule.getText().isEmpty())
				|| (edition.getText().isEmpty())) {
			this.check.setText("Please fill all the fields");

		}

		else {

			if (!(bookName.getText().isEmpty())) {
				book.setBookName(bookName.getText());
			}
			if (!(bookAuthor.getText().isEmpty())) {
				book.setBookAuthor(bookAuthor.getText());
			}
			if (!(noOfBooks.getText().isEmpty())) {
				book.setNoOfBooks(Integer.parseInt(noOfBooks.getText()));
			}
			if (!(typeOfBooks.getText().isEmpty())) {
				book.setTypeOfBooks(typeOfBooks.getText());
			}
			if (!(bookStatus.getText().isEmpty())) {
				book.setBookStatus(bookStatus.getText());
			}
			if (!(bookLimitDays.getText().isEmpty())) {
				book.setBookLimitDays(Integer.parseInt(bookLimitDays.getText()));
			}
			if (!(format.getText().isEmpty())) {
				periodical.setFormat(format.getText());
				// novel.setGenre(genre.getText());
			}
			if (!(schedule.getText().isEmpty())) {
				periodical.setSchedule(schedule.getText());
			}

			if (!(edition.getText().isEmpty())) {
				periodical.setEdition(Integer.parseInt(edition.getText()));
			}

			BankDAO.tblBook booktable;
			BankDAO bd = new BankDAO();
			booktable = bd.AddReference(book, periodical);

			bookList.add(booktable);

			tableView.setItems(bookList);
			clearform();

		}

		System.out.println("Inside update3");

	}

	public void updateRerenceBook() {

		Book book = new Book();
		Periodical periodical = new Periodical();

		System.out.println("Inside update1");
		txt.setFont(Font.font(20));

		if (tableView.getSelectionModel().isEmpty()) {
			this.check.setText("Please select any of the row first");

		} else {

			Integer id = Integer.parseInt(bookList.get(tableView.getSelectionModel().getSelectedIndex()).getBookId());
			System.out.println("id is" + id);

			String name = bookList.get(tableView.getSelectionModel().getSelectedIndex()).getBookName();

			System.out.println("name is" + name);

			String auth = bookList.get(tableView.getSelectionModel().getSelectedIndex()).getBookAuthor();
			System.out.println("autor is is" + auth);

			Integer no = Integer
					.parseInt(bookList.get(tableView.getSelectionModel().getSelectedIndex()).getNoOfBooks());
			System.out.println("no of books is is" + no);

			String type = bookList.get(tableView.getSelectionModel().getSelectedIndex()).getTypeOfBooks();
			System.out.println("type is" + type);

			String status = bookList.get(tableView.getSelectionModel().getSelectedIndex()).getBookStatus();
			System.out.println("status is" + status);

			Integer limit = Integer
					.parseInt(bookList.get(tableView.getSelectionModel().getSelectedIndex()).getBookLimitDays());
			System.out.println("limit is is" + limit);

			String form = bookList.get(tableView.getSelectionModel().getSelectedIndex()).getFormat();
			System.out.println("genre is is" + form);

			String sched = bookList.get(tableView.getSelectionModel().getSelectedIndex()).getSchedule();
			System.out.println("language  is is" + sched);

			Integer edit = Integer
					.parseInt(bookList.get(tableView.getSelectionModel().getSelectedIndex()).getEdition());
			System.out.println("pages  is is" + edit);
			// LocalDate valu=published.getValue();

			book.setBookId(id);
			book.setBookName(name);
			book.setBookAuthor(auth);
			book.setNoOfBooks(no);
			book.setTypeOfBooks(type);
			book.setBookStatus(status);
			book.setBookLimitDays(limit);
			periodical.setFormat(form);
			periodical.setSchedule(sched);
			periodical.setEdition(edit);

			System.out.println("bookName is" + bookName.getText());
			System.out.println("bookAuthor is" + bookAuthor.getText());
			System.out.println("noOfBooks is" + noOfBooks.getText());
			System.out.println("typeofbooks is" + typeOfBooks.getText());
			System.out.println("bookstatus is" + bookStatus.getText());
			System.out.println("booklimitdays is" + bookLimitDays.getText());

			if (!(bookName.getText().isEmpty())) {
				book.setBookName(bookName.getText());
			}
			if (!(bookAuthor.getText().isEmpty())) {
				book.setBookAuthor(bookAuthor.getText());
			}
			if (!(noOfBooks.getText().isEmpty())) {
				book.setNoOfBooks(Integer.parseInt(noOfBooks.getText()));
				txt.setText("Enter integer Value");
			}
			if (!(typeOfBooks.getText().isEmpty())) {
				book.setTypeOfBooks(typeOfBooks.getText());
			}
			if (!(bookStatus.getText().isEmpty())) {
				book.setBookStatus(bookStatus.getText());
			}
			if (!(bookLimitDays.getText().isEmpty())) {
				book.setBookLimitDays(Integer.parseInt(bookLimitDays.getText()));
			}

			if (!(format.getText().isEmpty())) {
				periodical.setFormat(format.getText());
				// novel.setGenre(genre.getText());
			}
			if (!(schedule.getText().isEmpty())) {
				periodical.setSchedule(schedule.getText());
			}

			if (!(edition.getText().isEmpty())) {
				periodical.setEdition(Integer.parseInt(edition.getText()));
			}

			System.out.println("Inside update3");

			bookList.remove(tableView.getSelectionModel().getSelectedIndex());

			BankDAO.tblBook b;
			BankDAO bd = new BankDAO();
			b = bd.updateReference(book, periodical);

			bookList.add(b);

			clearform();
			tableView.setItems(bookList);

		}

	}

	public void issueBook() throws Exception {

		Bill bill = new Bill();
		Book book = new Book();
		Periodical periodical = new Periodical();

		ArrayList<Integer> member;

		BankDAO bd1 = new BankDAO();
		boolean membercheck = true;

		member = bd1.getUser();

		if (tableView.getSelectionModel().isEmpty()) {
			this.check.setText("Please select any of the row first to issue the book");

		} else if ((memberId.getText().isEmpty()) || (librarianId.getText().isEmpty())) {
			this.check.setText("Please select all the details to issue the book");

		} else if (!(memberfail(member, memberId.getText()))) {

			this.check.setText("Member doesnt exist");

		} else if (!(Librarianfail(librarianId.getText()))) {
			this.check.setText("Librarian doesnt exist");

		} else {

			Integer id = Integer.parseInt(bookList.get(tableView.getSelectionModel().getSelectedIndex()).getBookId());
			System.out.println("id is" + id);

			String name = bookList.get(tableView.getSelectionModel().getSelectedIndex()).getBookName();

			System.out.println("name is" + name);

			String auth = bookList.get(tableView.getSelectionModel().getSelectedIndex()).getBookAuthor();
			System.out.println("autor is is" + auth);

			Integer no = Integer
					.parseInt(bookList.get(tableView.getSelectionModel().getSelectedIndex()).getNoOfBooks());
			System.out.println("no of books is is" + no);

			String type = bookList.get(tableView.getSelectionModel().getSelectedIndex()).getTypeOfBooks();
			System.out.println("type is" + type);

			String status = bookList.get(tableView.getSelectionModel().getSelectedIndex()).getBookStatus();
			System.out.println("status is" + status);

			Integer limit = Integer
					.parseInt(bookList.get(tableView.getSelectionModel().getSelectedIndex()).getBookLimitDays());
			System.out.println("limit is is" + limit);

			String form = bookList.get(tableView.getSelectionModel().getSelectedIndex()).getFormat();
			System.out.println("genre is is" + form);

			String sched = bookList.get(tableView.getSelectionModel().getSelectedIndex()).getSchedule();
			System.out.println("language  is is" + sched);

			Integer edit = Integer
					.parseInt(bookList.get(tableView.getSelectionModel().getSelectedIndex()).getEdition());
			System.out.println("pages  is is" + edit);
			// LocalDate valu=published.getValue();

			book.setBookId(id);
			book.setBookName(name);
			book.setBookAuthor(auth);
			book.setNoOfBooks(no);
			book.setTypeOfBooks(type);
			book.setBookStatus(status);
			book.setBookLimitDays(limit);
			periodical.setFormat(form);
			periodical.setSchedule(sched);
			periodical.setEdition(edit);

			bill.setBookId(id);

			System.out.println("book Id is" + id);

			if (!(memberId.getText().isEmpty())) {
				bill.setMemberId(Integer.parseInt(memberId.getText()));
			}
			if (!(librarianId.getText().isEmpty())) {
				bill.setLibId(Integer.parseInt(librarianId.getText()));
			}

			System.out.println("Inside update3");

			boolean result = false;

			int bookcheck;

			BankDAO bd = new BankDAO();

			bookcheck = bd.getSumBooks(bill);
			bd.setUserIssuedBooks(bill);

			System.out.println("number of bookcheck is is " + bookcheck);

			if (bookcheck > 0) {
				result = bd.issueBook(bill);
				// bd.setNumberOfBooks(bookcheck-1,book,novel);

				bookList.remove(tableView.getSelectionModel().getSelectedIndex());

				bookList.add(bd.setNumberOfReferenceBooks(bookcheck - 1, book, periodical));

				// clearform();
				tableView.setItems(bookList);
			}

			if (result) {
				this.check.setText("Book Issued Successfully");

			} else {
				this.check.setText("Book couldnt be issued");

			}

		
			clearBillform();

			
		}

	}

	private void clearBillform() {
		// TODO Auto-generated method stub
		memberId.clear();
		librarianId.clear();

	}

	private void clearform() {
		bookName.clear();
		bookAuthor.clear();
		bookStatus.clear();
		bookLimitDays.clear();
		noOfBooks.clear();
		typeOfBooks.clear();
		format.clear();
		schedule.clear();
		edition.clear();

		this.check.setText("");

	}

	private boolean memberfail(ArrayList<Integer> member, String str) {

		boolean check = false;
		for (int i = 0; i < member.size(); i++) {

			int memberidtext = Integer.parseInt(str);
			if (memberidtext == member.get(i))

				check = true;
		}

		return check;

	}

	private boolean Librarianfail(String text) {
		// TODO Auto-generated method stub
		boolean check = false;

		int id = Integer.parseInt(text);

		if (id == 1) {
			check = true;
		}

		return check;
	}

	public void mainPage() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MainMenuPage.fxml"));
		// Inflate the view using the loader
		AnchorPane root = (AnchorPane) loader.load();
		// Set window title
		dialogStage.setTitle("Admin Main Menu");
		// Create a scene with the inflated view
		Scene scene = new Scene(root);

		MainMenuController controller = loader.getController();

		dialogStage.setScene(scene);

		// Get the controller instance from the loader
		// Set the parent stage in the controller
		controller.setDialogStage(dialogStage);
		// Show the view
		dialogStage.show();
	}

	public void setMemberId(Integer id) {
		id1 = id;
		tableView.setEditable(true);

		itemIdColumn.setCellValueFactory(new PropertyValueFactory<tblBook, String>("bookId"));
		itemNameColumn.setCellValueFactory(new PropertyValueFactory<tblBook, String>("bookName"));
		itemAutColumn.setCellValueFactory(new PropertyValueFactory<tblBook, String>("bookAuthor"));
		itemNoColumn.setCellValueFactory(new PropertyValueFactory<tblBook, String>("noOfBooks"));
		itemTypColumn.setCellValueFactory(new PropertyValueFactory<tblBook, String>("typeOfBooks"));
		itemStatColumn.setCellValueFactory(new PropertyValueFactory<tblBook, String>("bookStatus"));
		itemLimiColumn.setCellValueFactory(new PropertyValueFactory<tblBook, String>("bookLimitDays"));

		itemForColumn.setCellValueFactory(new PropertyValueFactory<tblBook, String>("format"));
		itemSchColumn.setCellValueFactory(new PropertyValueFactory<tblBook, String>("schedule"));
		itemEdiColumn.setCellValueFactory(new PropertyValueFactory<tblBook, String>("edition"));

		bookList = new BankDAO().getAllReferenceProducts();

		tableView.setItems(new BankDAO().getAllReferenceProducts());
	}

	public void userPage() throws IOException {
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

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

}