package test;

import java.io.BufferedInputStream;
import java.io.IOException;

import mh_struct.Entry;
import qgb.Array;
import qgb.Spirit;
import qgb.T;
import qgb.net.Get;
import qgb.text.Regex;

public class Html {
	public static String cst = "Html ";
	public static boolean cbt=true;//test
	static Entry entry = new Entry();

	public static void main(String[] args) throws Exception{
		T.print(cst+" begain");
    	//entry=get_Entry("good");
    	 //entry.print(entry);
		String st_ip="http://113.247.242.14";
		String st_html="";
		
    	for (int i = 0; i < 256; i++) {
    		st_html=Get.html(st_ip);
    		T.write("ccsu/"+i+".html", st_html);
    		T.print(st_ip);
			T.exit();
    		
		}
    	
    	//T.print(T.repeat(9, "="));
    	//get_Entry("book");
    	T.print(cst+" end");    	
    }

	public static Entry get_Entry(String ast_word) throws Exception {
		String st_url = "http://dict.cn/mini.php?q=" + ast_word;
		String st_html = "";
		if (cbt) {
			st_html = get_html_test(ast_word);//only for test
		}else {
			st_html = Get.html(st_url);
		}
		//st_html = Get.html(st_url);
		if (st_html == null) {
			return null;
		}
		// T.print(st_html);
		String st_re = ""; // (?<=<span class="k">\n)\w*(?=\s*</span>)
		// ////////////////////////////word
		// all///////////////////////////////////////
		// st_re="[\\w\\W]*(?=<div id=\"t\">)";
		// String st_h1=Regex.get_text(st_re,st_html);
		// T.print(st_re);
		// ////////////////////////word
		st_re = "(?<=<span class=\"k\">\\n)\\w*(?=\\s*</span>)";
		// T.print("!qgb:"+st_html);
		entry.word.st_w = Regex.get_text(st_re, st_html);
		//T.print(entry.word.st_w);
		// /////////////////////////entry.word.voice.st_mark
		st_re = "(?<=onclick=\"ssplay\\(')[\\w]*(?='\\);return false)";
		entry.word.voice.st_mark = Regex.get_text(st_re, st_html);
		entry.word.voice.setBis_v(get_voice(entry.word.voice.st_mark));
		//T.print(entry.word.voice.st_mark);
		// //////////////////entry.phonetic
		st_re = "(?<=<span class=\"p\">\\n\\[).*(?=\\]\\s*</span>)";
		entry.phonetic = Regex.get_text(st_re, st_html);
		//T.print(entry.phonetic);

		// ////////////////////////////translations////////////////////////////////////
		st_re = "(?=<div id=\"e\">)[\\w\\W]*?(?=</div>)";// "(?=<div id=\"e\">)[\\w\\W]*";
		String st_tran = Regex.get_text(st_re, st_html);
		if (st_tran != null) {
			entry.translations = Spirit.HtmlTag(st_tran);
			//T.print(entry.translations);
		}
		// ////////////////////////////// sentence
		// //////////////////////////////////
		st_re = "(?<=<div id=\"s\">)[\\w\\W]*?(?=</div>)";
		// T.print(st_re);
		String st_sen = Regex.get_text(st_re, st_html);
		if (st_sen != null) {
			st_re = "(?=<i>\n)[\\w\\W]*?(?=</i>)";
			String[] sya;
			sya = st_sen.split(st_re);
			sya = Array.del_one_element(sya, 0);
			// T.print(sya);
			String st_re_v = "(?<=onclick=\"ssplay\\(')[\\w]*(?='\\);return false)";
			String st_re_e = "(?<=</i>\n\\.)[\\w\\W]*?(?=<a onmouseover)";
			String st_re_c = "(?<=<br/>\n)[\\w\\W]*?(?=<br/>)";
			// T.print(st_re);
			entry.initialize_sentence(sya.length);
			String sta = "";
			for (int i = 0; i < sya.length; i++) {
				if (sya[i] == null) {
					return null;
					// break;
				}
				entry.sentence[i].v.st_mark = Regex.get_text(st_re_v, sya[i]);
				//entry.sentence[i].v.bis_v = get_voice(entry.sentence[i].v.st_mark);
				entry.sentence[i].v.setBis_v(get_voice(entry.sentence[i].v.st_mark));
				//T.print(entry.sentence[i].v.st_mark);
				
				sta = Regex.get_text(st_re_e, sya[i]);
				sta=sta.trim();
				sta = sta.replace("      <em>\n", entry.cst_sen_e1);
				sta = sta.replace("      </em>\n", entry.cst_sen_e2);
				entry.sentence[i].ds.st_e = sta;
				sta = "";
				//T.print(entry.sentence[i].ds.st_e);

				entry.sentence[i].ds.st_c = Regex.get_text(st_re_c, sya[i]);
				entry.sentence[i].ds.st_c =entry.sentence[i].ds.st_c.trim();
				//T.print(entry.sentence[i].ds.st_c);
			}
		}
		return entry;

		/*
		 * static void trans_format(String ast){ if(ast.startsWith("n.")){
		 * entry.translations.st_n=entry.translations.st_n+ast+"/n"; }else if
		 * (ast.startsWith("n.")) {
		 * entry.translations.st_n=entry.translations.st_n+ast+"/n"; } }
		 */
	}

	public static BufferedInputStream get_voice(String ast_vm) throws Exception {
		return (Get.urlfile("http://dict.cn/mp3.php?q=" + ast_vm));
	}

	public static String get_html_test(String ast_word) throws IOException {
		return T.read_st("D:/TEST/"+ast_word+".html");
	}
}