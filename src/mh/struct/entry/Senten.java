package mh.struct.entry;

import mh.struct.DoubleSt;
import mh.struct.Voice;

public class Senten {
	public DoubleSt ds=new DoubleSt("", "");
	public Voice v=new Voice();

	public Senten(String ast1, String ast2) {
		this.ds.st1.set(ast1);
		this.ds.st2.set(ast2);
	}
	public Senten(DoubleSt ads) {
		this.ds.set(ads);
	}
	
	public Senten(DoubleSt ads, Voice av) {
		this.ds.set(ads);
		av.set(av);
	}
	@Override
	public String toString() {
		return ds.toString();
	}
}
