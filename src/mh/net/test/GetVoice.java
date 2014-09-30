package mh.net.test;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

import qgb.T;
import qgb.interfaces.QRunable;
import qgb.net.Get;
import test.feature.Final;
import mh.gui.results.ResultsPanel;
import mh.struct.Voice;
import mh.struct.entry.C;
import mh.struct.entry.Entry;
import mh.struct.entry.Phon;

public class GetVoice implements QRunable {
	public static void main(String[] args) {
//		//Voice v=new Voice();
//		String stM=T.read_st("v/url.txt");
//		Entry e=new Entry();
//		e.word.ps.put(C.P, new Phon("buk",stM));
//		QRunable qr=new GetVoice(e.word.ps.get(C.P).v);
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
	
	protected final Voice gv;
	protected final ResultsPanel gRP;
	

	public GetVoice(Voice av, ResultsPanel gRP) {
		if (av==null) {
			T.argsError(av);
		}
		this.gv = av;
		this.gRP = gRP;
	}
	
	@Override
	public void run() {
	}

	protected boolean gbNotStop=true;
	@Override
	public void stop(){
		gbNotStop=false;
	}
	@Override
	public boolean isStop() {
		return !gbNotStop;
	}
	protected boolean gbIsDone=false;
	@Override
	public boolean isDone() {
		return gbIsDone;
	}



}
