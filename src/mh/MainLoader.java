 package mh;

import qgb.T;
import mh.database.MDT;
import mh.gui.MainFrame;

public class MainLoader {
	public static void main(String[] args) {
		db();
		MainFrame.main(null);
	}
	/**�������ݿ�**/
	public static void db() {
		try {
			Class.forName(MDT.class.getName());
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		T.sleep(50);//�ȴ����ݿ�������
	}
}
