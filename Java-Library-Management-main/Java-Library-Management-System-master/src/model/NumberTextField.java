package model;
/**
 * Number validations class
 */

import javafx.fxml.FXML;
import javafx.scene.control.TextField;


public class NumberTextField extends TextField {
	
	@FXML
	private TextField usernameTxtField;
	
	public NumberTextField(){
		this.setPromptText("Characters Not Allowed");
		
	}

	
	public void replaceText(int i, int i1, String string){
		if(string.matches("[0-9]") || string.isEmpty()){
			System.out.println("length is"+string.length());
			super.replaceText(i, i1, string);
		}
		
	}
	



	public void replaceSelection(String string){
		super.replaceSelection(string);
	}
}
