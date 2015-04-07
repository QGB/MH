package qgb.project.website;

import java.io.IOException;
import java.net.MalformedURLException;

import qgb.T;
import qgb.net.Get;

public class QQHtml {

	public static void main(String[] args) {
		T.print(T.getCurrentTime());
		try {
			T.write("www.qq.com.qgb", Get.urlfile("http://www.qq.com"));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
