package qgb.project;

import java.io.IOException;

import qgb.Array;
import qgb.T;
import qgb.net.HttpRequest;
import qgb.text.Regex;
import qgb.text.Text;

/* second-level domain (SLD)
 * @Time 2014-3-2 18:39:16
 * */
public class SLD_info {	
	public static void main(String[] args) throws IOException  {
		SLD_info sInfo=new SLD_info("ccsu.cn");
//"\\w{1,20}\\.ccsu\\.cn"
	}
	
	String gst_html;
	/*
	 * 
	 * */
	public SLD_info(String ast_domain) throws IOException  {		
		String[] yst;
		//gst_html= HttpRequest.post("http://i.links.cn/subdomain/", "domain="+ast_domain+ "&b2=1&b3=1&b4=1");
		//T.print(gst_html);
		//T.write("sld.html", gst_html);
		yst=T.read_st("yst.txt").split("\n");
		//yst= Regex.get_text_array("\\w{1,20}\\.ccsu\\.cn", gst_html);
		yst=Array.del_muti(yst);
		//T.print("|\n|");
		T.write("ysld.txt",Array.ArrayToStr(yst,"\n"));
		T.print(yst);
	}

	
}
