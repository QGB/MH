package qgb.project.ccsu.practicum2014.view;

import qgb.T;

public class AutoCode {
	public static void main(String[] args) {
		String st=T.getSource(Main.class);
		//T.print(st);
		int is=0,ie=0;
		String sa="actionPerformed(ActionEvent e) {";
		String sb="",sba = "";
		while(is!=-1){
			is=st.indexOf(sa,is+sba.length())+sa.length();
			if (is==31) {
				break;
			}
			ie=st.indexOf("}",is+1);
			sb=st.substring(is,ie);
			sba="\r\n				"+sb.trim()+
					"\r\n				loadData();"
					+ "\r\n			";
			st= st.replace(sb,sba);
			//T.msgbox(sb);
			//T.print(st);
			//T.msgbox();
//			if (st.length()>9999) {
//				T.msgbox(sb);
//			}
		}
		T.write("main.txt", st);
	}
}
