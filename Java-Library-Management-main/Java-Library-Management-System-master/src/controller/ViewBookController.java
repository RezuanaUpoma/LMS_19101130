
package controller;

/**
 * Novel Books Controller
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

public class ViewBookController implements Initializable {
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
	private TableColumn<tblBook, String> itemGenColumn;
	@FXML
	private TableColumn<tblBook, String> itemLanColumn;
	@FXML
	private TableColumn<tblBook, String> itemPubColumn;
	@FXML
	private TableColumn<tblBook, String> itemPagColumn;

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
	private TextField genre;
	@FXML
	private TextField bookLimitDays;
	@FXML
	private TextField language;
	@FXML
	private DatePicker published;
	@FXML
	private TextField pages;
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
		itemGenColumn.setCellValueFactory(new PropertyValueFactory<tblBook, String>("genre"));
		itemLanColumn.setCellValueFactory(new PropertyValueFactory<tblBook, String>("language"));
		itemPubColumn.setCellValueFactory(new PropertyValueFactory<tblBook, String>("published"));
		itemPagColumn.setCellValueFactory(new PropertyValueFactory<tblBook, String>("pages"));

		bookList = new BankDAO().getAllGeneralProducts();

		tableView.setItems(new BankDAO().getAllGeneralProducts());

	}

	@SuppressWarnings({ "deprecation", "unused" })
	public void addGeneralBook() throws Exception {

		Book book = new Book();
		Novel novel = new Novel();
		DateFormat format = new SimpleDateFormat("YYYY-MM-DD", Locale.ENGLISH);
		LocalDate value = published.getValue();

		if ((bookName.getText().isEmpty()) || (bookAuthor.getText().isEmpty()) || (noOfBooks.getText().isEmpty())
				|| (typeOfBooks.getText().isEmpty()) || (bookStatus.getText().isEmpty())
				|| (bookLimitDays.getText().isEmpty()) || (genre.getText().isEmpty()) || (value == null)
				|| (language.getText().isEmpty()) || (pages.getText().isEmpty())) {
			txt.setText("Please input book name");
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
			if (!(genre.getText().isEmpty())) {
				novel.setGenre(genre.getText());
			}
			if (!(pages.getText().isEmpty())) {
				novel.setPages(Integer.parseInt(pages.getText()));
			}
			if (value != null) {
				novel.setPublished(java.sql.Date.valueOf(published.getValue()));
			}
			if (!(language.getText().isEmpty())) {
				novel.setLanguage(language.getText());
			}

			BankDAO.tblBook booktable;
			BankDAO bd = new BankDAO();
			booktable = bd.addBooks(book, novel);

			bookList.add(booktable);

			tableView.setItems(bookList);
			clearAddform();

		}

		System.out.println("Inside update3");

	}

	public void updateGeneralBook() {

		Book book = new Book();
		Novel novel = new Novel();
		DateFormat format = new SimpleDateFormat("YYYY-MM-DD", Locale.ENGLISH);
		LocalDate value = published.getValue();

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

			String gen = bookList.get(tableView.getSelectionModel().getSelectedIndex()).getGenre();
			System.out.println("genre is is" + gen);

			String lang = bookList.get(tableView.getSelectionModel().getSelectedIndex()).getLanguage();
			System.out.println("language  is is" + lang);

			Integer pag = Integer.parseInt(bookList.get(tableView.getSelectionModel().getSelectedIndex()).getPages());
			System.out.println("pages  is is" + pag);
			// LocalDate valu=published.getValue();

			String valu = bookList.get(tableView.getSelectionModel().getSelectedIndex()).getPublished();

			System.out.println("valu is:" + valu);
			System.out.println("novel value is" + java.sql.Date.valueOf(valu));
			book.setBookId(id);
			book.setBookName(name);
			book.setBookAuthor(auth);
			book.setNoOfBooks(no);
			book.setTypeOfBooks(type);
			book.setBookStatus(status);
			book.setBookLimitDays(limit);
			novel.setGenre(gen);
			novel.setLanguage(lang);
			novel.setPages(pag);
			novel.setPublished(java.sql.Date.valueOf(valu));

			System.out.println("novel value after setting is" + novel.getPublished());

			System.out.println("bookName is" + bookName.getText());
			System.out.println("bookAuthor is" + bookAuthor.getText());
			System.out.println("noOfBooks is" + noOfBooks.getText());
			System.out.println("typeofbooks is" + typeOfBooks.getText());
			System.out.println("bookstatus is" + bookStatus.getText());
			System.out.println("booklimitdays is" + bookLimitDays.getText());
			System.out.println("genre is" + genre.getText());
			System.out.println("bookpages is" + pages.getText());
			System.out.println("booklanguage is" + language.getText());

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

			if (!(genre.getText().isEmpty())) {
				novel.setGenre(genre.getText());
			}
			if (!(pages.getText().isEmpty())) {
				novel.setPages(Integer.parseInt(pages.getText()));
			}
			if (value != null) {
				novel.setPublished(java.sql.Date.valueOf(published.getValue()));
			}
			if (!(language.getText().isEmpty())) {
				novel.setLanguage(language.getText());
			}

			System.out.println("Inside update3");

			bookList.remove(tableView.getSelectionModel().getSelectedIndex());

			BankDAO.tblBook b;
			BankDAO bd = new BankDAO();
			b = bd.Update(book, novel);

			bookList.add(b);

			clearUpdateform();
			tableView.setItems(bookList);

		}

	}

	public void issueBook() throws Exception {

		Bill bill = new Bill();

		Book book = new Book();
		Novel novel = new Novel();
		ArrayList<Integer> member;

		BankDAO bd1 = new BankDAO();
		boolean membercheck = true;

		member = bd1.getUser();

		System.out.println("member check before" + membercheck);

		System.out.println("number of members" + member.size());
		System.out.println("member check after" + membercheck);

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
			// to set
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

			String gen = bookList.get(tableView.getSelectionModel().getSelectedIndex()).getGenre();
			System.out.println("genre is is" + gen);

			String lang = bookList.get(tableView.getSelectionModel().getSelectedIndex()).getLanguage();
			System.out.println("language  is is" + lang);

			Integer pag = Integer.parseInt(bookList.get(tableView.getSelectionModel().getSelectedIndex()).getPages());
			System.out.println("pages  is is" + pag);

			String valu = bookList.get(tableView.getSelectionModel().getSelectedIndex()).getPublished();

			System.out.println("valu is:" + valu);
			System.out.println("novel value is" + java.sql.Date.valueOf(valu));

			// to set
			book.setBookId(id);
			book.setBookName(name);
			book.setBookAuthor(auth);
			book.setNoOfBooks(no);
			book.setTypeOfBooks(type);
			book.setBookStatus(status);
			book.setBookLimitDays(limit);
			novel.setGenre(gen);
			novel.setLanguage(lang);
			novel.setPages(pag);
			novel.setPublished(java.sql.Date.valueOf(valu));

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

				bookList.remove(tableView.getSelectionModel().getSelectedIndex());

				bookList.add(bd.setNumberOfBooks(bookcheck - 1, book, novel));

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

	private boolean Librarianfail(String text) {
		// TODO Auto-generated method stub
		boolean check = false;

		int id = Integer.parseInt(text);

		if (id == 1) {
			check = true;
		}

		return check;
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
		genre.clear();
		pages.clear();
		published.getEditor().clear();
		language.clear();
		this.check.setText("");
		tableView.getSelectionModel().clearSelection();
		memberId.clear();
		librarianId.clear();

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
		itemGenColumn.setCellValueFactory(new PropertyValueFactory<tblBook, String>("genre"));
		itemLanColumn.setCellValueFactory(new PropertyValueFactory<tblBook, String>("language"));
		itemPubColumn.setCellValueFactory(new PropertyValueFactory<tblBook, String>("published"));
		itemPagColumn.setCellValueFactory(new PropertyValueFactory<tblBook, String>("pages"));

		bookList = new BankDAO().getAllGeneralProducts();

		tableView.setItems(new BankDAO().getAllGeneralProducts());
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

	
	
private void clearUpdateform() {
		
		
	bookName.clear();
	bookAuthor.clear();
	bookStatus.clear();
	bookLimitDays.clear();
	noOfBooks.clear();
	typeOfBooks.clear();
	genre.clear();
	pages.clear();
	published.getEditor().clear();
	language.clear();
	this.check.setText("Novel has been updated successfully");
	tableView.getSelectionModel().clearSelection();
	memberId.clear();
	librarianId.clear();

		
	}

private void clearAddform() {
	
	
	bookName.clear();
	bookAuthor.clear();
	bookStatus.clear();
	bookLimitDays.clear();
	noOfBooks.clear();
	typeOfBooks.clear();
	genre.clear();
	pages.clear();
	published.getEditor().clear();
	language.clear();
	this.check.setText("Novel has been added successfullly");
	tableView.getSelectionModel().clearSelection();
	memberId.clear();
	librarianId.clear();

	
}
	
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

}