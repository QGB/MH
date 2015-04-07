package qgb.project.ccsu.practicum2014.view;

import javax.swing.JOptionPane;

import qgb.T;

public class Utils {
	public static void main(String[] args) throws ClassNotFoundException {
		msgbox("111");
		T.print(input("text")); 
	}
	//////////////////////////////////////////////////////
	public static String input(Object ast) {
		return input(ast, "");
	}
	public static String input(Object ast,Object initialSelectionValue) {
		String st=(String) JOptionPane.showInputDialog(null, ast, title(), JOptionPane.OK_OPTION, null, null, initialSelectionValue);
		if(st==null||st.length()<1){
			throw new IllegalStateException("用户没有输入！");
		}
		return st;
	}
	
	public static int msgbox(Object message){
		return JOptionPane.showConfirmDialog(null, message, title(),JOptionPane.PLAIN_MESSAGE);
	}
	
	
	public static String title() {
		//T.print(T.getCurrentClass().getSimpleName());
		StackTraceElement[] yste = Thread.currentThread().getStackTrace();
		if (yste.length<2) {
			return "";
		}
		//T.print(yste);
		/**getCurrentClass**/
		String str="";
		Class<?> c = null;
		for (int i = 0; i < yste.length; i++) {
			if(yste[i].getMethodName().equals("title")){
				try {
					c= Class.forName(yste[i+1+1].getClassName());
				} catch (ClassNotFoundException e) {	e.printStackTrace();}
			}
		}
		//T.print(c);
		return c.getSimpleName();
	}	
	
}
