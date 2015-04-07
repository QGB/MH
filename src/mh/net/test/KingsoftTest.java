package mh.net.test;

import java.io.InputStream;

import mh.gui.MainFrame;
import qgb.CharsetName;
import qgb.T;
import qgb.net.HttpRequest;

public class KingsoftTest {

	public static void main(String[] args) {
		//MainLoader.main(null);
	}
	public static String printUrl(String ast){
		String sUrl = "http://ct.dict-client.iciba.com/2012-12-07/index.php?c=client&word="
				+ ast
				+ "&dictlist=201,2,1,101,6,104,7,105,5,103,203,202,8,9,204,205,10,11,3,4,&tip_show=1,2,3,4,5,6,";
		T.print(sUrl);
		return sUrl;
	}
}
