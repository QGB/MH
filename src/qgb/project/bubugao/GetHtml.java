package qgb.project.bubugao;

import java.io.IOException;
import java.net.MalformedURLException;

import qgb.T;
import qgb.Y;
import qgb.net.Get;

public class GetHtml {

	public static void main(String[] args) {
//		T.setOutStream("e.txt");
//		for (int i = 1; i <256; i++) {
//			T.print(Get.html("http://124.232.133."+i+":80"));
//		}
//		
		//T.exit();
		T.cst_test_path=T.cst_test_path+"bubugao/";
		String stip=T.read_st("ip80.txt");
		String[] yst=stip.split("\r\n");
//		T.print(yst);
		for (int i = 0; i < yst.length; i++) {
				T.write(yst[i].substring(0, yst[i].length()-3)+".html", 
						Get.html("http://"+yst[i])
						);
				T.print(yst[i]);
			 
			
		}
	}

}
