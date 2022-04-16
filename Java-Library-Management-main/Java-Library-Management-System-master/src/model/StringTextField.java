package model;
/**
 * String validation class
 */

import javafx.scene.control.TextField;

public class StringTextField extends TextField {
	
	public StringTextField(){
		this.setPromptText("Enter String");
	}

	
	public void replaceText(int i, int i1, String string){
		if(string.matches("[a-zA-Z]") || string.isEmpty()){
			super.replaceText(i, i1, string);
		}
	}
	
	public void replaceSelection(String string){
		super.replaceSelection(string);
	}
}
