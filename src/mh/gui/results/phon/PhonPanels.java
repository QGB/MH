package mh.gui.results.phon;

import java.util.EnumMap;
import mh.struct.entry.C;

public class PhonPanels extends EnumMap<C, PhonPanel> {
	public PhonPanels() {
		super(C.class);
	}
}