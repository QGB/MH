package mh.struct.entry;

import java.util.HashMap;

import mh.struct.StrNotNull;

public class Translations{
	public Translations(){}
	public Translations(String asTran) {
		gst.set(asTran);
	}

	private volatile StrNotNull gst = new StrNotNull();
	private final String gSeparator = "\n";
	private HashMap<String, String> map = new HashMap<String, String>();

	public void add(String asWordClass, String asTrans) {
		if (map.containsKey(asWordClass)) {
			String stExist = map.get(asWordClass);
			if (stExist.equals(asTrans)) {
				return;
			} else {
				if (asTrans.startsWith(";") || stExist.endsWith(";")) {
					map.put(asWordClass, stExist + asTrans);
				} else {
					map.put(asWordClass, stExist + ";" + asTrans);
				}

			}
		} else {
			map.put(asWordClass.toLowerCase(), asTrans);
		}

	}

	public synchronized void add(String ast) {
			if (ast.contains(gSeparator) == false) {
				gst.set(gst+ast+ gSeparator);
			} else {
				gst.set(gst + ast);
			}
	}
	
	@Override
	public String toString() {
		return gst.get();
	}
}