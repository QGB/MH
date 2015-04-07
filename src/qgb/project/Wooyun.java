package qgb.project;

import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import java.util.IllegalFormatException;
import java.util.zip.DataFormatException;

import qgb.CharsetName;
import qgb.T;
import qgb.jdbc.Mysql;
import qgb.net.Get;
import qgb.net.HttpRequest;
import qgb.text.Regex;

public class Wooyun {
	// http://wooyun.org/bugs/new_public/page/858
	public static void main(String[] args) {
		Mysql.connect("jdbc:mysql://localhost:3306/web", "root", "123456");
		//createTable();
		getPages();

	}

	private static void getPages() {
		String str = "", sthtml = "";

		for (int i = 736; i < 870; i++) {
			try {
				getPage(i);
			} catch (DataFormatException e) {
				// TODO Auto-generated catch block
				T.write(i+"Exception.txt",e.getClass()+"\n"+T.getCurrentTime() );
				e.printStackTrace();
				continue;
			}catch (NullPointerException e) {
				T.write(i+"Exception.txt",e.getClass()+"\n"+T.getCurrentTime() );
				e.printStackTrace();
			}catch (SocketException e) {
				T.write(i+"Exception.txt",e.getClass()+"\n"+T.getCurrentTime() );
				e.printStackTrace();
			}catch (IOException e) {
				T.write(i+"Exception.txt",e.getClass()+"\n"+T.getCurrentTime() );
				e.printStackTrace();
			}
			
			
			
			
			//T.print(getPage(i));
		}

		//T.write("862.html", getPage(862), CharsetName.GST_UTF8);

	}

	private static String getPage(int ai) throws DataFormatException,SocketException,IOException,NullPointerException {
		String stinput = "http://wooyun.org/bugs/new_public/page/" + ai, sthtml = "";
		InputStream is;
		is = HttpRequest.get(stinput);

		try {
			sthtml = T.InputStreamToString(is, CharsetName.GST_UTF8);
			// T.print(sthtml);
			// T.write(i+".html",HttpRequest.get(sturl));
			// str= Get.html(sturl);

		} catch (Exception e) {
			e.printStackTrace();
		}
		sthtml=Regex.get_first_text("(?<=<tbody>)[\\w\\W]*(?=</tbody>)", sthtml);
		String[] yst=Regex.get_text_array("(?<=<tr>)[\\w\\W]*?(?=</tr>)", sthtml);
		String[] ystitle,yst_3;//1<td>,3<th>
		//String[] ystr=new String[4];
		String stitle="",sicommen="",sifollow="",sauthor="",sturl="",stsubmit="";
		int ititle=0,icf=0;
		String sql="";
		
		for (int i = 0; i < yst.length; i++) {
			yst_3=Regex.get_text_array("(?<=<th>)[\\w\\W]*?(?=</th>)", yst[i]);
			ystitle=Regex.get_text_array("(?<=<td><a href=\")[\\w\\W]*?(?=</a>)", yst[i]);
			if (yst_3.length!=3|ystitle.length!=1) {
				throw new DataFormatException(ai+" format error!");
			}
			//T.print(yst_3);
			//T.msgbox("");
			ititle= ystitle[0].indexOf("\">");
			stitle=ystitle[0].substring( ititle+2,ystitle[0].length());
			sturl="http://wooyun.org/"+ystitle[0].substring(0, ititle);
			
			stsubmit=yst_3[0];
			yst_3[1]=Regex.get_first_text("(?<=#comment\">)[\\w\\W]*?(?=</a>)", yst_3[1]);
			icf=yst_3[1].indexOf('/');
			//T.print("yst_3[1]:"+yst_3[1]);
			sicommen=yst_3[1].substring(0, icf);
			sifollow=yst_3[1].substring(icf+1,yst_3[1].length());
			sauthor=Regex.get_first_text("(?<=<a title=\")[\\w\\W]*?(?=\" href=\")", yst_3[2]);
			
			
			
			sql = "INSERT INTO wooyun (stitle,icommen,ifollow,sauthor,sturl,tsubmit) "
					+ "VALUES ('"
					+ stitle
					+ "',"
					+ sicommen
					+ ","
					+ sifollow
					+ ",'"
					+ sauthor
					+ "','"
					+ sturl
					+ "','"
					+ stsubmit
					+ "');";;
			
			T.print(sql);
			Mysql.SQL_execute(sql);
			//yst_3[0]
			//T.print(yst[0]);
//			T.print(stitle);
//			T.print(sicommen);
//			T.print(sifollow);
//			T.print(sauthor);	
//			T.print(sturl);	
//			T.print(stsubmit);	
//			T.print("--------"+i	+ "----------");
			//stitle
			
		}
		
		if (yst.length==0) {
			throw new IllegalArgumentException(ai+ " null !");
		}
		//T.print(ystr);
		return sthtml;
	}

	private static void createTable() {
		String sql = "CREATE TABLE wooyun ("
				+ "stitle  varchar(255)  NOT NULL,"// 标题
				+ "icommen integer       NOT NULL,"// 评论
				+ "ifollow integer       NOT NULL,"// 关注
				+ "sauthor varchar(20)   NOT NULL,"// 作者
				+ "sturl   varchar(50)   NOT NULL,"// 地址
				+ "tsubmit DATETIME      NOT NULL,"// 提交
				+ "topened DATETIME,"// 公开
				+ "dnotify DATE,"// 通知
				+ "dverify DATE,"// 确认
				+ "dneglec DATE,"// 忽视
				+ "dcore   DATE,"// 核心 whitehat
				+ "dcommon DATE,"// 普通 whitehat
				+ "dIntern DATE,"// 实习 whitehat
				+ "PRIMARY KEY (sturl));";//

		Mysql.SQL_execute(sql);

	}

}
