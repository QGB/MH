package mh.database;

import mh.gui.FindPanel;
import mh.gui.results.ResultsPanel;
import mh.net.GetEntry;
import mh.net.kingsoft.KingSoft;
import mh.struct.entry.Entry;

public class DBEntry implements GetEntry {
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	/**********Test Code End************/
	int giRetry = 1;
	ResultsPanel gRP;
	FindPanel gFP;
	public DBEntry(ResultsPanel aRP ,FindPanel aFP) {
		this(3,aRP, aFP);
	}

	public DBEntry(int aiRetry,ResultsPanel aRP ,FindPanel aFP)
			throws IllegalArgumentException {
		if (aiRetry < 1) {
			throw new IllegalArgumentException(KingSoft.class.toString()
					+ "|aiRetry<1");
		}
		giRetry = aiRetry;
		gRP=aRP;
		gFP=aFP;
		//gpPanel = aPPanel;
	}
	//不可多次改变 gbXXX 的值
	private boolean gbNotStop=true,gbIsDone=false;

	@Override
	public void stop(){gbNotStop=false;}
	@Override
	public boolean isStop() {return !gbNotStop;}
	@Override
	public boolean isDone() {return gbIsDone;}
	@Override
	public Entry byWord(String ast) {
		return null;
	}


}
