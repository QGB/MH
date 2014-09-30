package test;

import qgb.T;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.XmlSerializer;

class MyThread extends Thread{
	private String name;
	public MyThread(String aname){
		this.name=aname;	
	}	
	public void run(){
			System.out.println(name+"运行");
	        final WebClient webClient = new WebClient();
	        final HtmlPage htmlPage;
	        String st_url = "http://dict.youdao.com/search?q=oo&keyfrom=dict.index";
	        //"http://dict.cn/mini.php?q=skandal"
	        String st_html = "";
	        //str_url="http://dict.cn/mini.php?q="+ast_word;'
			///////////////////////////////////////////
			try {
				htmlPage = webClient.getPage(st_url);
				Thread.sleep(200);
				st_html= new XmlSerializer().asXml(htmlPage.getDocumentElement());
				T.write("oo_y_utf8.html", st_html,"UTF-8");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			////////////////////////////////////////

			T.print(st_html);
	}

	public static String html(String ast_url) throws Exception{
        final WebClient webClient = new WebClient();
        //String str_url = "";
        //str_url="http://dict.cn/mini.php?q="+ast_word;
        
        final HtmlPage htmlPage = webClient.getPage(ast_url);
		return new XmlSerializer().asXml(htmlPage.getDocumentElement());
	}
}

public class ThreadTest{
	public static void main(String[] args) {
		MyThread mt1=new MyThread("线程A");
		//MyThread mt2=new MyThread("线程B");
		mt1.start();
		//mt2.start();
		//mt1.sleep(4);
	}
}