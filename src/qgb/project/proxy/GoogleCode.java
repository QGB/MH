package qgb.project.proxy;

import java.io.InputStream;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import qgb.CharsetName;
import qgb.T;
import qgb.file.F;
import qgb.net.HttpRequest;
import qgb.net.html.JsoupTest;

public class GoogleCode {
	static String gsUrl;
	public static void main(String[] args) {
		svn_download();
	}

	private static void svn_download() {
		T.setProxy("127.0.0.1","8580");
		T.cst_test_path=T.cst_test_path+"save-data-dict/";
		T.print(T.cst_test_path);
		gsUrl="http://save-data-dict.googlecode.com/svn/";
		re_parse("",getDoc(gsUrl));
	}

	private static void re_parse(String asRePath,Document adoc) {
		Elements es= adoc.select("li");
		String sta="",sUrl="";
		
		for(Element e:es){
			sta=e.select("a[href]").html();
			if (sta.endsWith("..")) {
				continue;
			}			
			
			sUrl=gsUrl+asRePath+sta;
			T.print(sUrl);
			
			if (sta.endsWith("/")) {
				re_parse(asRePath+sta,getDoc(sUrl));
			}else {
				InputStream is=HttpRequest.get(sUrl);
				for (int i = 0; i < 3; i++) {
					if (is!=null) {
						T.write(asRePath+F.name(sta) ,is );	
						break;
					}else {
						continue;
					}
				}
			
				
			}
			
		}
	}

	private static Document getDoc(String asUrl) {
		InputStream is= HttpRequest.get(asUrl);
		return Jsoup.parse(T.InputStreamToString(is, CharsetName.GST_UTF8));
	}
}
