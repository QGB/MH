package test;

import java.applet.Applet;
import java.io.IOException;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;

import qgb.T;

public class T_super extends JButton {
	
	
	public T_super() {
		super();
		// TODO Auto-generated constructor stub
	}

	public T_super(Action a) {
		super(a);
		// TODO Auto-generated constructor stub
	}

	public T_super(Icon icon) {
		super(icon);
		// TODO Auto-generated constructor stub
	}

	public T_super(String text, Icon icon) {
		super(text, icon);
		// TODO Auto-generated constructor stub
	}

	public T_super(String text) {
		super(text);
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args)  {	
		T.notify("");
		//super();
	}
	
	
	
}


class Father {
	public Father() {
		String name = null;
		int age = 0;
	}
}

class Son extends Father {
	public Son() {
		String name = "学生";
		super();
	}
}

 // 这样子的话就会把Son里面的name又变成了null 没有达到你想要的效果