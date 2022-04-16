package model;
/**
 * Number validations class
 */

import javafx.fxml.FXML;
import javafx.scene.control.TextField;


public class NumberLengthCheck extends TextField {
	
	@FXML
	private TextField usernameTxtField;
	
	public NumberLengthCheck(){
		this.setPromptText("ID Should be Number");
		
	}

	
	public void replaceText(int i, int i1, String string){
		if(string.matches("[0-9]") || string.isEmpty()){
			System.out.println("length is"+string.length());
			super.replaceText(i, i1, string);
		}
		else if(!(lengthCheck(string))){
			this.setText("Enter number are more");
		}
	}
	
	private boolean lengthCheck(String string) {
		// TODO Auto-generated method stub
		boolean check=false;
		if(string.length()<=4)
		{
			check=true;
		}
		
		
		return check;
	}


	public void replaceSelection(String string){
		super.replaceSelection(string);
	}
}
