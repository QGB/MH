 package mh;

import qgb.T;
import mh.database.MDT;
import mh.gui.MainFrame;

public class MainLoader {
	public static void main(String[] args) {
		db();
		MainFrame.main(null);
	}
	/**加载数据库**/
	public static void db() {
		try {
			Class.forName(MDT.class.getName());
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		T.sleep(50);//等待数据库加载完成
	}
}
