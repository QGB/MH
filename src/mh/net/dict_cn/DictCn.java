package mh.net.dict_cn;

import java.util.concurrent.Callable;

import qgb.T;
import mh.net.GetEntry;
import mh.struct.entry.Entry;

public class DictCn implements GetEntry {

	public static void main(String[] args) {
		Entry e=new DictCn().ByWord("book");
		e.print();
	}

	
	@Override
	public Entry ByWord(String ast) {
		String st_html=qgb.net.Get.html( "http://dict.cn/mini.php?q=" + ast);
		Entry entry=new Entry();
		//T.print(st_html);
		return entry;
	}
	

}
