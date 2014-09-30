package qgb.project.discuz;

import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import java.sql.Date;
import java.sql.Time;
import java.util.IllegalFormatException;
import java.util.zip.DataFormatException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;
import org.jsoup.select.NodeTraversor;
import org.jsoup.select.NodeVisitor;

import qgb.Array;
import qgb.CharsetName;
import qgb.Convert;
import qgb.T;
import qgb.jdbc.Mysql;
import qgb.net.Get;
import qgb.net.HttpRequest;
import qgb.text.Regex;
import qgb.time.TimeConvert;

public class Bbs_kuaibo_com {
	final static Date gDate0=
			new Date(TimeConvert.toLong(2014, 6, 2, 20, 10, 0, 0));
	static String  stitle="";
	static boolean bimg=false;
	static boolean bdigest=false;
	static String icommen="";
	static String iview="";
	static String sauthor="";
	static String sturl="";
	static String tsubmit="";
	static String tlast="";
	static String slastName="";
	//table columns 
	static String sql="";
	public static void main(String[] args) {
		Document da=Jsoup.parse("&nbsp;");
		T.print(da);
		T.print(da.text());
		//da.
		//parserPage(Jsoup.parse(T.read_st("1.html")));

	}
	

    
    
    
    
    
    
	private static void getPages() {
		String str = "", sthtml = "";

		for (int i = 1; i <=76; i++) {
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
		String stinput = "http://bbs.kuaibo.com/forum-36-" + ai+".html", sthtml = "";
		InputStream is;
		is = HttpRequest.get(stinput);
		byte[] bytes;
		bytes=T.InputStreamToYByte(is);
		is=T.ByteToInputStream(bytes);

		try {
			sthtml = T.InputStreamToString(is, CharsetName.GST_UTF8);
			T.print(Bbs_kuaibo_com.class+":"+ ai);
			T.write("discuz/"+ai+".html",T.ByteToInputStream(bytes));
			// str= Get.html(sturl);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (0==0) {
			return "";
		}
//		T.exit();
		
		
		parserPage(Jsoup.parse(sthtml));
			
		sql = "INSERT INTO wooyun (stitle,icommen,iview,sauthor,sturl,tsubmit,tlast,slastName) "
					+ "VALUES ('"
					+ stitle
					+ "',"
					+ icommen
					+ ","
					+ iview
					+ ",'"
					+ sauthor
					+ "','"
					+ sturl
					+ "','"
					+ tsubmit
					+ "','"
					+ tlast
					+ "','"
					+ slastName
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
			return sthtml;
			
		
	}

	private static void parserPage(Document adPage) {
		Elements eall;
		eall= adPage.select("tbody[id*=thread_]");
		int i=0;
		Elements esby;
		for (Element src :eall) {	
			if (i<15-1) {
				i++;
				continue;
			}
			
			stitle= src.select("a[class=s xst]").text();
			bimg=src.select("img[title=图片附件]").toArray().length==1;
			bdigest=src.select("img[title*=精华]").toArray().length==1;
			
			esby=src.select("td[class=by]");
			if (esby.size()==2) {
				sauthor=esby.select("cite").get(0).text();
				tsubmit=convertTime( esby.select("em").get(0).text());
				slastName=esby.select("cite").get(1).text();
				
			}
		
			
			T.print(stitle); 
			T.print(bimg);
			T.print(bdigest);
			T.print(sauthor);
			T.print(tsubmit);
			T.print(slastName);
			//T.print(esby.size());
			//T.print(esby.html());
			i++;
			//T.write("/jsoup/bool.txt", src.html());
			T.exit();
			if (i==3) {
				T.exit();
			}
			
			//T.print((i++)+ src.text()+"\n	"+src.html().replace("\n",""));
		}
		
	}

	private static String convertTime(String ast) {
		//T.print(ast);
		ast=ast.replace(Jsoup.parse("&nbsp;").text(), " ");
		return ast;
		//return null;
	}

	private static void createTable() {
		String sql = "CREATE TABLE bbs_kuaibo_com ("
				+ "stitle  varchar(255)  NOT NULL,"// 标题
				+ "icommen integer       NOT NULL,"// 评论
				+ "iview   integer       NOT NULL,"// 关注
				+ "sauthor varchar(20)   NOT NULL,"// 作者
				+ "sturl   varchar(30)   NOT NULL,"// 地址
				+ "tsubmit DATETIME      NOT NULL,"// 提交
				+ "tlast   DATETIME      NOT NULL,"// 公开
				+ "slastName varchar(20) NOT NULL,"// 地址
				+ "PRIMARY KEY (sturl));";//

		Mysql.SQL_execute(sql);

	}

	
	
	
}
