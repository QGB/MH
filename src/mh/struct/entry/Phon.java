package mh.struct.entry;

import qgb.T;
import mh.struct.StrNotNull;
import mh.struct.Voice;

public class Phon {
	public final StrNotNull stP = new StrNotNull();
	public final Voice v=new Voice() ;
	/***********************/
	public Phon(String asp) {
		stP.set(asp);
	}
	
	public Phon(String asp,String as_VstM) {
		stP.set(asp);
		v.stM.set(as_VstM);
	}
	/***********************/
	@Override
	public String toString() {
		String str=super.toString();
		return str+"[stP=" + stP + ",\n"+
			T.repeat(str.length()+1," ")+v.toString() + "]";
	}
	@Override
	public boolean equals(Object obj) {
		return false;
	}
	public boolean equals(Phon ap) {
		return ap.stP.equals(this.stP)&&ap.v.equals(this.v);
	}
	
}