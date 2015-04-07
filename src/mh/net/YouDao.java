package mh.net;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import qgb.T;

public class YouDao {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		YouDao yDao=new YouDao();
		yDao.tranSouce="book";
		T.print(yDao.setURL());
	}
	////////////////////////////////////////////////
	private String name = "youdaofanyi1111";
	private String key = "1065174416";
	private String tranSouce;
	private String setURL(){
		String url = null;
		String tranSouceUTF = null;
		try {
			tranSouceUTF = URLEncoder.encode(this.tranSouce, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		url = "http://fanyi.youdao.com/openapi.do?keyfrom=" + this.name + "&key=" + this.key + "&type=data&doctype=xml&version=1.1&q=" + tranSouceUTF;
		return url;
	}
}
