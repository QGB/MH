
package qgb.project.ccsu;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;


import qgb.T;

/**
 * @author Administrator
 * 
 */
public class Cet {

	public static void main(String[] args) throws IOException {
		T.cst_test_path="G:/cet/09xk/";
		T.write("start", T.BytesToInputStream("test".getBytes()));
		DownLoad_all();
	}

	private static void DownLoad_all() {
		String stin="",st_url="",stpath="";
		InputStream is;
		stpath="G:/cet/09xk/09xk.txt";
		stin =T.read_st(stpath);
		if (null==stin) {return;} 
		//T.print(stin);
		String[] yst=  stin.split("\n");

		
		for (int i = 0; i < yst.length; i++) {
			yst[i]=yst[i].replaceAll("\\W","");
			st_url="http://shekao.hneao.cn/cet_pic/43025/"
					+ yst[i].substring(0, 2)
					+ "/" + yst[i].substring(2, 6) 
					+ "/" + yst[i]
					+ ".jpg";
			//T.print(st_url);
			try {
				
				is = urlfile(st_url);
				if (is == null) {
					System.out.println(yst[i] + " null");
					continue;
				}
				T.write(yst[i] + ".jpg", is);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	

	}


	public static BufferedInputStream urlfile(String ast_url)
			throws IOException {
		int HttpResult = 0; // 服务器返回的状态
		URL url = new URL(ast_url); // 创建URL
		URLConnection urlconn = url.openConnection(); // 试图连接并取得返回状态码urlconn.connect();
		HttpURLConnection httpconn = (HttpURLConnection) urlconn;
		try {
			HttpResult = httpconn.getResponseCode();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// System.out.println("HTTP Status-Code:"+HttpResult);

		if (HttpResult != HttpURLConnection.HTTP_OK) { // 不等于HTTP_OK说明连接不成功
			// System.out.print("fail");
			System.out.println("HttpResult:" + HttpResult);
			return null;
		} else {
			int filesize = urlconn.getContentLength(); // 取数据长度

			System.out.println("qgbt_filesize:" + filesize);

			BufferedInputStream bis = new BufferedInputStream(
					urlconn.getInputStream());
			return bis;
			/*
			 * BufferedOutputStream bos=new BufferedOutputStream(new
			 * FileOutputStream(target)); byte[] buffer = new byte[1024];
			 * //创建存放输入流的缓冲 int num = -1; //读入的字节数 while (true) { num =
			 * bis.read(buffer); // 读入到缓冲区 if (num ==-1){ bos.flush(); break;
			 * //已经读完 } bos.flush(); bos.write(buffer,0,num); } bos.close();
			 * bis.close();
			 */
		}// else end

	}

	

}
