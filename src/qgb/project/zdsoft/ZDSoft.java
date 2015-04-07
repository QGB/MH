package qgb.project.zdsoft;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import qgb.CharsetName;
import qgb.T;
import qgb.jdbc.Mysql;
import qgb.net.HttpRequest;

public class ZDSoft {

	public static void main(String[] args) {
		//zdsoft();
		//T.exit();
		
		String str="";
		byte[] yb;
		yb=T.InputStreamToByte(HttpRequest.get("http://61.187.64.231/admin/manageUsers.aspx?bigClassId=1&currentPageIndex=1"));
		str=T.InputStreamToString(T.ByteToInputStream(yb), CharsetName.GST_GB2312);
		T.write("zdsoft1.html", T.ByteToInputStream(yb));
		T.print(str);
		// TODO Auto-generated method stub

	}

	
private static void zdsoft() {
	Mysql.connect();
	createTable();
	String stin=T.read_st("zdsoft.html", CharsetName.GST_GB2312);
	//T.print(stin);
	Document doc =Jsoup.parse(stin);
	Elements links = doc.select("a[href]");
	String sta="";
	int ia=-1;
    for (Element link : links) {
    	sta=link.attr("href");
    	if (sta.endsWith(".aspx")) {
    		ia=sta.indexOf("/");
    		if (ia!=-1) {
    			//T.print(sta);
    			sta=sta.substring(ia+1, sta.length());	
			}
    		sta="http://61.187.64.231/admin/"+sta;
    		T.print(sta);
    		Mysql.SQL_execute("INSERT INTO zdsoft (sturl) "
					+ "VALUES ('"
					+ sta
					+ "');");
    		//Mysql
    		
		}
    	
    	}
    

	}


	private static void createTable() {
		String sql = "CREATE TABLE zdsoft ("
				+ "sturl  varchar(255)  NOT NULL,"
				+ "PRIMARY KEY (sturl));";//

		Mysql.SQL_execute(sql);

	}
	
}
