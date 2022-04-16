package model;
/**
 * Validation for character
 */

import javafx.fxml.FXML;
import javafx.scene.control.TextField;



public class CharacterTextField extends TextField {
	
	

	
	public CharacterTextField(){
		this.setPromptText("Enter either J or C Only");
	}

	
	public void replaceText(int i, int i1, String string){
		if(string.matches("[J]") || string.matches("[C]") ||  string.isEmpty()){
			super.replaceText(i, i1, string);

		}
	}
	
	public void replaceSelection(String string){
		super.replaceSelection(string);
	}
}
