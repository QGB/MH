package qgb.net;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.JOptionPane;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import qgb.CharsetName;
import qgb.T;
import test.t2;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.TextPage;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.XmlSerializer;
import com.gargoylesoftware.htmlunit.javascript.host.Text;

/////////////////////////////////////////////////
public class Get {

	public class GetHtml implements Runnable {
		String st_url = "";
		String st_html = "";
		int i_sleep = 0;

		GetHtml(String ast_url, int ai_sleep) {
			st_url = ast_url;
			i_sleep = ai_sleep;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			final WebClient webClient = new WebClient();
			final HtmlPage htmlPage;
			// String st_url =
			// "http://dict.youdao.com/search?q=oo&keyfrom=dict.index";
			// "http://dict.cn/mini.php?q=skandal"
			// str_url="http://dict.cn/mini.php?q="+ast_word;'
			// /////////////////////////////////////////
			try {
				htmlPage = webClient.getPage(st_url);
				Thread.sleep(i_sleep);
				st_html = new XmlSerializer().asXml(htmlPage
						.getDocumentElement());
				// T.write("oo_y_utf8.html", st_html,"UTF-8");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// //////////////////////////////////////
			// T.print(st_html);

		}

		public String Get() {

			return st_html;
		}

	}

	public static String html(String ast_url)
			throws FailingHttpStatusCodeException {
		final WebClient wc = new WebClient(BrowserVersion.INTERNET_EXPLORER_8);
		wc.getOptions().setThrowExceptionOnFailingStatusCode(false);
		wc.getOptions().setThrowExceptionOnScriptError(false);
		// webClient.setThrowExceptionOnFailingStatusCode(false);
		// webClient.setThrowExceptionOnScriptError(false);
		// String str_url = "";
		// str_url="http://dict.cn/mini.php?q="+ast_word;
		Page p=null;
		try {
			p = wc.getPage(ast_url);
			T.sleep(200);
		} catch (MalformedURLException e) {
			T.error(e, Get.class + " ����� URL�������ڹ淶�ַ������Ҳ����κκϷ�Э�飬�����޷������ַ�����");
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
		
		try {
			HtmlPage hp=HtmlPage.class.cast(p);
			return hp.asXml();
		} catch (ClassCastException e) {}
		try {
			TextPage tp=TextPage.class.cast(p);
			return tp.getContent();
		} catch (ClassCastException e) {}
		
		T.msgbox(p);
		// HtmlElement he=htmlPage.getDocumentElement();
		String str = "";// hp.asXml();

		return str;
		
	}

	public static BufferedInputStream urlfile(String ast_url)
			throws MalformedURLException, IOException {
		int HttpResult = 0; // ���������ص�״̬
		URL url = new URL(ast_url); // ����URL
		URLConnection urlconn = url.openConnection(); // ��ͼ���Ӳ�ȡ�÷���״̬��urlconn.connect();
		HttpURLConnection httpconn = (HttpURLConnection) urlconn;
		try {
			HttpResult = httpconn.getResponseCode();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// System.out.println("HTTP Status-Code:"+HttpResult);

		if (HttpResult != HttpURLConnection.HTTP_OK) { // ������HTTP_OK˵�����Ӳ��ɹ�
			// System.out.print("fail");
			T.notify("url=" + ast_url + "\n	HttpResult=" + HttpResult);
			return null;
		} else {
			int filesize = urlconn.getContentLength(); // ȡ���ݳ���

			// System.out.println("qgbt_filesize:" + filesize);

			BufferedInputStream bis = new BufferedInputStream(
					urlconn.getInputStream());
			return bis;
			/*
			 * BufferedOutputStream bos=new BufferedOutputStream(new
			 * FileOutputStream(target)); byte[] buffer = new byte[1024];
			 * //��������������Ļ��� int num = -1; //������ֽ��� while (true) { num =
			 * bis.read(buffer); // ���뵽������ if (num ==-1){ bos.flush(); break;
			 * //�Ѿ����� } bos.flush(); bos.write(buffer,0,num); } bos.close();
			 * bis.close();
			 */
		}// else end
			// return null;

	}

	public static void main(String[] args) {
		String surl = "";
		surl = "13827";
		surl = "http://www.joces.org.cn/CN/abstract/abstract" + surl + ".shtml";

		try {
			jsoupDoc(surl, 1, 0);
		} catch (SocketTimeoutException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// surl= html("http://www.joces.org.cn/CN/abstract/abstract"+
		// surl+".shtml");
		// T.print(surl);
		// urlfile("http://www.joces.org.cn/CN/abstract/abstract"+
		// surl+".shtml");
		// T.print("test1 qgb");
		// T.write(surl + ".pdf",
		// urlfile("http://www.joces.org.cn/CN/article/downloadArticleFile.do?attachType=PDF&id="
		// + surl));
		// T.write(surl + ".shtml",
		// urlfile("http://www.joces.org.cn/CN/abstract/abstract"
		// + surl+".shtml"));

	}

	/**
	 * @throws java.net.SocketTimeoutException
	 *             if the connection times out
	 * @throws MalformedURLException
	 * @throws IOException
	 *             if a connection or read error occurs
	 **/
	public static Document jsoupDoc(String asUrl, int aiRetry, int timeoutMillis)
			throws IllegalArgumentException, IOException,
			SocketTimeoutException, MalformedURLException {
		if (aiRetry < 1 || timeoutMillis < 0) {
			T.argsError(asUrl, aiRetry, timeoutMillis);
		}

		URL url = new URL(asUrl);
		Document doc = null;
		try {
			doc = Jsoup.parse(url, timeoutMillis);
		} catch (SocketTimeoutException e) {
			if (aiRetry > 1) {
				doc = jsoupDoc(asUrl, aiRetry - 1, timeoutMillis);
			} else {
				throw e;
			}
		}
		return doc;
	}

}
