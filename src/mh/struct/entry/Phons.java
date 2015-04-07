package mh.struct.entry;

import java.util.EnumMap;
import java.util.Iterator;
import java.util.Map;

import qgb.T;
import mh.struct.Voice;

public class Phons extends EnumMap<C, Phon> {
	public Phons() {
		super(C.class);
	}

	public void playOneVoice() {
		if (size()<1)return;
		for(Map.Entry<C, Phon> mEntry:entrySet()){
			Voice v=mEntry.getValue().v;
			if(v.notNull()){
				//v.play();
				v.playAndWait();
				return;
			}
		}
	}
}