package mh.net.test;

import java.io.InputStream;

import qgb.CharsetName;
import qgb.T;
import qgb.net.HttpRequest;

public class KingsoftTest {

	public static void main(String[] args) {
		String str="";
		InputStream is,is1;
		byte[] yb;
		is=HttpRequest.get("http://ct.dict-client.iciba.com/2012-12-07/index.php?c=client&word=good&dictlist=1,101,202,5,103,4,201,6,104,7,105,8,9,3,2,102,203,204,&zyid=28&nav_status=1&type=0&authkey=c155554f2dd0bfa29defed6b7019b176&uuid=573DACD2230842308BC5B0268DF5B6D5&v=2013.01.15.025&tip_show=3,1,2,4,5,6,&fontsize=0###");
		yb=T.InputStreamToBytes(is);
		is=T.BytesToInputStream(yb);
		is1=T.BytesToInputStream(yb);
		str=T.InputStreamToString(is, CharsetName.GST_UTF8);
		T.print(str);
		T.write("k.html", is1);
	}

}
