package mh.net.voice;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

import qgb.T;
import qgb.interfaces.QRunable;
import qgb.net.Get;
import mh.gui.results.ResultsPanel;
import mh.gui.results.WordPanel;
import mh.net.test.GetVoice;
import mh.net.test.NetGet;
import mh.struct.Voice;
import mh.struct.entry.C;
import mh.struct.entry.Entry;
import mh.struct.entry.Phon;

public class GetPhonV implements QRunable{
	public static void main(String[] args) {
		T.getCurrentMethod()
//		String stM=T.read_st("v/url.txt");
//		Entry e=new Entry();
//		e.word.ps.put(C.P, new Phon("buk",stM));
//		//QRunable qr=new GetPhonV(e.word.ps.get(C.P).v,);
//		Thread tpv=new Thread(qr);
//		tpv.start();
//		//T.sleep(123);
//		//e.word.ps.get(C.P).v.stM.set("Modify!");
//		T.sleep(233);
//		qr.stop();
//		if (qr.isDone()) {
//			e.word.ps.get(C.P).v.play();
//		}
//		T.print(e.word.ps.get(C.P));
	}
	
	ResultsPanel gRP;
	final Voice gv;
	C gc;
	public GetPhonV(Voice av,ResultsPanel arp,C ac) {
		if (av==null) {
			T.argsError(av);
		}
		this.gv=av;
		
		gRP=arp;
		gc=ac;
		
	}
	
	@Override
	public void run() {
		InputStream is=null;
		try {
			is=Get.urlfile(gv.stM.get());
		} catch (MalformedURLException e) {
			return;
		} catch (IOException e) {
			return;
		}
		if (gbNotStop) {
			gv.set(is);
			gRP.gWP.addVoice(gv,gc);
			gRP.gPP.go(gv.stM.get());
			gbIsDone=true;
			gbNotStop=false;
		}
		
	}
	
	private boolean gbNotStop=true;
	@Override
	public void stop(){
		gbNotStop=false;
	}
	@Override
	public boolean isStop() {
		return !gbNotStop;
	}
	private boolean gbIsDone=false;
	@Override
	public boolean isDone() {
		return gbIsDone;
	}
	
}
