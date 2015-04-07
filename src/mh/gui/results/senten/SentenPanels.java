package mh.gui.results.senten;

import java.util.ArrayList;

import qgb.T;
import mh.struct.entry.Senten;
/**具有缓存功能，能提供更高的效率？**/
public class SentenPanels extends ArrayList<SentenPanel>{
	private volatile int gi=0;
//	@Override
//	public boolean add(SentenPanel e) {
//		return super.add(e);
//	}
	@Override
	public void clear() {
		gi=0;
		for(SentenPanel sp:this){
			sp.setVisible(false);
		}
	}
	@Override
	public int size() {
		return gi;
	}
	/**@see   mh.gui.results.senten.SentenPanel#SentenPanel(Senten, int)**/
	public int add(Senten as) {
		gi+=1;
		if (gi<=super.size()) {
			get(gi-1).set(as);
			get(gi-1).setVisible(true);
		}else if (gi==super.size()+1) {
			super.add(new SentenPanel(as, gi-1));
		}else {
			T.argsError(as);
		}
		return gi-1;
	}
	
}