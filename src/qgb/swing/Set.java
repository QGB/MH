package qgb.swing;

import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.JButton;


public class Set {  
	  
	 public static JButton UnifiedLook(JButton ajButton,Dimension aDimension){
		ajButton.setPreferredSize(aDimension);
		ajButton.setMargin(new Insets(0, 0, 0, 0));
		return ajButton;
	}
	
    public static void main(String [] args) {  
    	
    }
    

    }