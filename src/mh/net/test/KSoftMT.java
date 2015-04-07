package mh.net.test;

import java.io.IOException;
import java.util.concurrent.Callable;

import qgb.T;
import mh.gui.results.ResultsPanel;
import mh.net.GetEntry;
import mh.struct.entry.C;
import mh.struct.entry.Entry;
import mh.struct.entry.Phon;
import mh.struct.entry.Word;

public class KSoftMT<E> implements GetEntry, Callable<Entry> {
	public static void main(String[] args) throws Exception {
		KSoftMT<Entry> kscall = new KSoftMT<Entry>(null);
		T.print(T.time());
		kscall.call();
		T.print(T.time());
	}
	int giRetry = 1;
	ResultsPanel gRP;
	Entry gentry;

	public KSoftMT(ResultsPanel aRP) throws IllegalArgumentException{
		this(3, aRP);
	}

	public KSoftMT(int aiRetry, ResultsPanel aRP)
			throws IllegalArgumentException {
		if (aiRetry < 1) {
			throw new IllegalArgumentException(KSoftMT.class.toString()
					+ "|aiRetry<1");
		}
		giRetry = aiRetry;
		gRP = aRP;
	}
	@Override
	public Entry call() throws Exception {
		gentry=new Entry();
		gentry.word.stW.set("test");
		
		Phon puk=new Phon();
		puk.stP.set("UK");
		puk.v.set("m1", T.read_bis("v/o.mp3"));
		
		Phon pus=new Phon();
		pus.stP.set("US");
		pus.v.set("m2", T.read_bis("v/g.mp3"));
		
		gentry.word.ps.put(C.UK, puk);
		gentry.word.ps.put(C.US, pus);
		T.sleep(99);
		for (int i = Integer.MIN_VALUE; i < Integer.MAX_VALUE; i++) {
			i=i;
			int it=0;
			it=i;
		}
		T.print("Call done!"+T.time());
		return gentry;
	}

	@Override
	public Entry byWord(String ast) throws IOException,
			IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Word getWord() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
