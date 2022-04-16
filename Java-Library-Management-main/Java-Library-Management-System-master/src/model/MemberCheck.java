package model;
/**
 * Validation for character
 */

import javafx.fxml.FXML;
import javafx.scene.control.TextField;



public class MemberCheck extends TextField {
	
	

	
	public MemberCheck(){
		this.setPromptText("Enter either S or P Only");
	}

	
	public void replaceText(int i, int i1,String string){
		if(string.matches("[S]") || string.matches("[P]") || string.isEmpty()){
			super.replaceText(i, i1, string);

		}
		
	}
	public void replaceSelection(String string){
		super.replaceSelection(string);
	}
}
