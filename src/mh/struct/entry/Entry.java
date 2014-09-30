package mh.struct.entry;

//import mh_struct.Word;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

import mh.gui.ProgressPanel;
import mh.struct.Sentences;
import mh.struct.StrNotNull;
import mh.struct.Voice;
import qgb.T;
import qgb.interfaces.GetSet;
import test.swing.kingSoft;

public class Entry extends Object {
	public Word word = new Word("");
	public Translations trans = new Translations("");
	public Sentences sentces = new Sentences();
	//public ArrayList<Senten> alSes=new ArrayList<Senten>();
	public Inflection inflection = new Inflection();
	public Usually usually = new Usually();

	public class Inflection implements GetSet<String> {
		public String gst = "";
		private final String gSeparator = "|";

		@Override
		public String get() {
			return gst;
		}

		@Override
		public void set(String ast) {
			if (ast != null) {
				gst = ast;
			}
		}

		public void add(String ast) {
			if (ast != null || ast != "") {
				if (gst=="") {
					gst=ast;
					return;
				}
				gst = gst + gSeparator + ast;
			}
		}
	}

	public class Usually implements GetSet<String>{
		private String stTip = "";

		@Override
		public String toString() {
			return stTip;
		}

		@Override
		public String get() {
			if (stTip==null||stTip=="") {
				return "";
			}
			return stTip;
		}

		@Override
		public void set(String ast) {
			stTip=ast;
		}
	}

	// ////////////////////////////////////
	public void print() {
		print(this, "e");
	}
	/** is print?**/
	public static Boolean print(Object ae, String astName) {
		Field[] yf = ae.getClass().getDeclaredFields();
		boolean bool=false;
		for (int i = 0; i < yf.length; i++) {
			//T.msgbox(yf[i].toString());
			Object object = null;
			try {
				object = yf[i].get(ae);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				continue;
			}
			if (object == null) {
				continue;
			}
			if (String.class == yf[i].getType()) {
				String sta = null;
				try {
					sta = (String) yf[i].get(ae);
				} catch (IllegalArgumentException e1) {
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					e1.printStackTrace();
				}
				if (sta != null) {
					T.print("%s=\"%s\"", astName + "." + yf[i].getName(), sta);
					bool=true;
				}
				/** TODO 无法输出null值 **/
				// else {
				// T.print("%s=null", astName + "." + yf[i].getName());
				// }
				continue;
			} else {
				if (Entry.class == yf[i].getType()) {
					continue;
				}
				if (print(object, astName + "." + yf[i].getName())==false) {
					String stmp=object.toString();
					if (stmp.startsWith("mh.struct.")) {
						continue;
					}
					T.print("%s.toString()=\"%s\"", astName + "." + yf[i].getName(),stmp);
				} 
			}
			
//			if (Voice.class == yf[i].getType()) {
//				Voice voice = null;
//				try {
//					voice = (Voice) yf[i].get(ae);
//				} catch (IllegalArgumentException e1) {
//					e1.printStackTrace();
//				} catch (IllegalAccessException e1) {
//					e1.printStackTrace();
//				}
//				if (voice != null) {
//					voice.play();
//					//T.msgbox();
//				}
//			}
		}
		return bool;
	}

	/** class define end **/

	public static void main(String[] args) {
		// T.print(new String("public java.lang.String mh.struct.").length());
		ProgressPanel.main(null);
	}
}
