package mh.struct.entry;

import java.util.EnumMap;

public class Phons extends EnumMap<C, Phon> {
	public Phons() {
		super(C.class);
	}
}