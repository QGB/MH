package mh.net.kingsoft;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.concurrent.Callable;

import javax.security.auth.callback.CallbackHandler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

import qgb.CharsetName;
import qgb.T;
import qgb.interfaces.QRunable;
import qgb.text.Regex;
import test.thread.CallableDemo;
import mh.gui.FindPanel;
import mh.gui.MainFrame;
import mh.gui.ProgressPanel;
import mh.gui.results.ResultsPanel;
import mh.net.AbstractGetEntry;
import mh.net.GetEntry;
import mh.net.test.NetGet;
import mh.net.voice.GetPhonV;
import mh.net.voice.GetSentenV;
import mh.struct.DoubleSt;
import mh.struct.Voice;
import mh.struct.entry.C;
import mh.struct.entry.Entry;
import mh.struct.entry.Phon;
import mh.struct.entry.Senten;
import mh.struct.entry.Word;

/**
 * http://ct.dict-client.iciba.com/2012-12-07/index.php? c=client&//不能缺
 * word=book& dictlist=1,101,202,5,103,4,201,6,104,7,105,8,9,3,2,102,203,204,&
 * tip_show=1,2,3,4,5,6, bookes,ok,1,book
 * **/
public class KingSoft extends AbstractGetEntry {
	public static void main(String[] args) {
		MainFrame.main(null);
	}

	/*********** Test End *********/
	public KingSoft(ResultsPanel aRP) {
		super(aRP);
	}

	public Entry byWord(String ast) {
		gbIsDone=false;gbNotStop=true;
		if (ast == null || ast.length() < 2) {
			gRP.showMsg("待查找单词不能为空！");
			return null;
		}
		// T.print("["+ast+"]");
		String sUrl = "";// st_html="",
		sUrl = "http://ct.dict-client.iciba.com/2012-12-07/index.php?c=client&word="
				+ ast
				+ "&dictlist=201,2,1,101,6,104,7,105,5,103,203,202,8,9,204,205,10,11,3,4,&tip_show=1,2,3,4,5,6,";
		// T.print(sUrl);
		Document doc = null;
		try {
			if (gbNotStop)
				doc = qgb.net.Get.jsoupDoc(sUrl, giRetry, 500);
		} catch (SocketTimeoutException e1) {
			gRP.showMsg("Found [" + ast
					+ "] Time out！Please Retry or Check net Setting");
			return null;
		} catch (IOException e1) {
			gRP.showMsg("Found [" + ast + "] Error！Please Check net Setting");
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		Elements es = doc.select("div[id=dict_main]");
		Entry e = new Entry();
		String stUrl = "";
		final String stReUrl = "\\b(https?)://[-A-Z0-9+&@#/%?=~_|$!:,.;]*[A-Z0-9+&@#/%=~_|$]"
				.toLowerCase();

		/*** dictbar **********/
		Elements esBar = es.select("div[class=dictbar]");
		if (isStop())
			return null;
		e.word.stW.set(esBar.select("div[id=current_word]").text());
		if (e.word.stW.get().length() < 1) {
			T.write(System.currentTimeMillis() + "ks.html", doc.data());
			gRP.showMsg("Parse [" + ast + "] Error！Write Html to "
					+ T.gstTestPath);
			return null;
		} else {
			gRP.gPP.go("Finding [" + ast + "]...");
		}
		e.usually.set(esBar.select("div[ class*=usually_tip]").text());

		/****** prons and voice *****/
		Elements esProns = esBar.select("div[class=prons]");
		esProns = esProns.select("span[class=eg]");
		String stPron = "", stMark = "";
		if (isStop())
			return null;
		for (Element ele : esProns) {
			stPron = ele.text();
			/** java regex different from regexbuddy **/
			stUrl = Regex.get_first_text(stReUrl, ele.html().toLowerCase());
			if (stPron.contains("英")) {
				Phon p = new Phon(stPron, stUrl);
				getVoice(p.v, C.UK);
				e.word.ps.put(C.UK, p);
				continue;
			}
			if (stPron.contains("美")) {
				Phon p = new Phon(stPron, stUrl);
				getVoice(p.v, C.US);
				e.word.ps.put(C.US, p);
				continue;
			}
		}
		if (e.word.ps.size() == 0 && esProns.size() == 1) {
			Phon p = new Phon(stPron, stUrl);
			getVoice(p.v, C.P);
			e.word.ps.put(C.P, p);
		}
		gRP.gWP.showWord(e.word);

		/*** dictbar end **********/
		/*** dict_content **********/
		Elements esContent = es.select("div[class=dict_content]");
		Elements esTrans = esContent.select("div[class=group_pos]");
		if (isStop())
			return null;
		esTrans = esTrans.select("p");
		for (Element ele : esTrans) {
			if (ele.text().length() > 1) {
				e.trans.add(ele.text());
			}
		}
		gRP.gTP.showTrans(e.trans);

		Elements esInf = esContent.select("div[class=group_inf]");
		if (isStop())
			return null;
		esInf = esInf.select("li");
		for (Element ele : esInf) {
			e.inflection.add(ele.text());
		}

		/*** <div class="shuangyu"> **********/
		Elements esSentces = es.select("div[class=shuangyu]");
		if (isStop())
			return null;
		esSentces = esSentces.select("div[id=dict_tab_201]");
		esSentces = esSentces.select("dl[class=vDef_list]");
		for (Element ele : esSentces) {
			if (isStop())
				return null;
			/** DoubleSt 对象不可以在循环外创建，否则sentces中均指向最后一个引用 **/
			DoubleSt ds = new DoubleSt(ele.select("dt").text(), ele
					.select("dd").text());
			Senten s = new Senten(ds);
			stUrl = Regex.get_first_text(stReUrl.toLowerCase(), ele.html()
					.toLowerCase());
			s.v.stM.set(stUrl);
			e.sentces.add(s);

			gRP.gSsP.addSenten(s);
			getVoice(s.v, e.sentces.size() - 1);
		}
		// T.msgbox(e.inflection.get());
		// T.print(st_html);
		gRP.gFP.addHistory(e.word.stW.get());
		gbIsDone = true;

		return e;
	}
}
