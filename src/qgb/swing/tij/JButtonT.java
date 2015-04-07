package qgb.swing.tij;

import java.awt.*;

import javax.swing.*;
import static net.sw

public class JButtonT extends JFrame {
	JButton jb1=new JButton("jb1"),jb2=new JButton("jb2");
	
	
	public JButtonT() {
		setLayout(new FlowLayout());
		add(jb1);
		add(jb2);
	}




	public static void main(String[] args) {
		run(new JButtonT(),333,333);

	}

}
