package mh.struct.entry;

import java.io.Serializable;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.Map;

import qgb.T;
import mh.struct.StrNotNull;
import mh.struct.Voice;
public class Word {
	
	public Word(String ast) {
		this.stW.set(ast);
	}

	public StrNotNull stW = new StrNotNull();
	//public Phonetic phons = new Phonetic();
	public Phons ps = new Phons();
	
	public static void main(String[] args) {
		Phon puk=new Phon();
		puk.stP.set("UK");
		puk.v.set("m1", T.read_bis("v/o.mp3"));
		
		Phon pus=new Phon();
		pus.stP.set("US");
		pus.v.set("m2", T.read_bis("v/g.mp3"));
		
		Phon ph=new Phon();
		ph.stP.set("haha");
		
		Word word=new Word("book");
		word.ps.put(C.UK, puk);
		word.ps.put(C.US, pus);
		word.ps.put(C.P, ph);
		
		for(Map.Entry<C, Phon> e:word.ps.entrySet()){
			Phon p=e.getValue();
			//T.print("%s=%s",e.getKey(),p.stP);
			if (p.v.isNotNull()) {
				//T.print(e.getValue().v.stM);
				e.getValue().v.play();
				//T.sleep(1999);
			}
		}
		
		//T.sleep(1999);
		Voice.stopPlay();
		//T.msgbox("233");
		T.print("233");
		Voice.stopPlay();
	}
	
//	public class Phonetic {
//		public StrNotNull stUK = new StrNotNull();
//		public StrNotNull stUS = new StrNotNull();
//		public StrNotNull stP = new StrNotNull();
//		
//		public Voice v ;
//		public Voice vUK ;
//		public Voice vUS ;
//	}
//	
}

