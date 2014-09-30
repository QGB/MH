package mh.struct.entry;

import java.util.AbstractMap;
import java.util.Set;

import qgb.T;

public class SentencesMap extends AbstractMap<Integer,Senten> {

	public static void main(String[] args) {
		SentencesMap ss=new SentencesMap();
		Integer i1=new Integer(1);
		ss.put(null,null);
		
		T.print(ss.get(1));
		
	}

	@Override
	public Set<java.util.Map.Entry<Integer, Senten>> entrySet() {
		return null;
	}

	@Override
	public Senten put(Integer key, Senten value) {
		return super.put(key, value);
	}


}
