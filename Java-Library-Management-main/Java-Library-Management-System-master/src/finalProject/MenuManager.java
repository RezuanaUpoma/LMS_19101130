package finalProject;


import java.io.IOException;
import java.util.logging.*;

import controller.ViewBookController;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.layout.AnchorPane;

/** Manages control flow for logins */
public class MenuManager {
  private Scene scene;

  public MenuManager(Scene scene) {
    this.scene = scene;
  }

  /**
   * Callback method invoked to notify that a user has been authenticated.
   * Will show the main application screen.
   */ 
  public void authenticated() {
    showMainView();
  }

  /**
   * Callback method invoked to notify that a user has logged out of the main application.
   * Will show the login application screen.
   */ 
  public void logout() {
    showLoginScreen();
  }
  
  public void showLoginScreen() {
    try {
      FXMLLoader loader = new FXMLLoader(
        getClass().getResource("LoginPage.fxml"));
      
      //Set window title

      //Create a scene with the inflated view
      Scene scene = new Scene(null);
      //Set the scene to the stage
      scene.setRoot((Parent) loader.load());
      
    } catch (IOException ex) {
      Logger.getLogger(MenuManager.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  private void showMainView() {
    try {
      FXMLLoader loader = new FXMLLoader(
        getClass().getResource("SearchBook.fxml")
      );
      AnchorPane root = (AnchorPane) loader.load();

      Scene scene = new Scene(root);

      scene.setRoot((Parent) loader.load());
     
    } catch (IOException ex) {
      Logger.getLogger(MenuManager.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
}
