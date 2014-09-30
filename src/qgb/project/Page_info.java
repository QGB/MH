package qgb.project;

import java.io.IOException;

import qgb.Array;
import qgb.CharsetName;
import qgb.T;
import qgb.net.Get;
import qgb.net.HttpRequest;
import qgb.text.Regex;

/* second-level domain (SLD)
 * @Time 2014-3-2 18:39:16
 * */
public class Page_info {	
	public static void main(String[] args) throws Exception  {
		Page_info sInfo=new Page_info("ccsu.cn");
//"\\w{1,20}\\.ccsu\\.cn"
	}
	
	String gst_html;
	/*
	 * 
	 * */
	public Page_info(String ast_domain) throws Exception {		
		try {
			gst_html= Get.html("http://nic.ccsu.cn/structure/zq/zq.htm");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//T.print(gst_html);
		T.write("sld.html", gst_html,CharsetName.gst_utf8);
		String[] yst= Regex.get_text_array("\\w{1,20}\\.ccsu\\.cn", gst_html);
		Array.del_muti(yst);
		T.print(yst);
	}

	
}
