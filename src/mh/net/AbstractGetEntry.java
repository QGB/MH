package mh.net;

import java.util.ArrayList;

import mh.gui.FindPanel;
import mh.gui.results.ResultsPanel;
import mh.net.kingsoft.KingSoft;
import mh.net.voice.GetPhonV;
import mh.net.voice.GetSentenV;
import mh.struct.Voice;
import mh.struct.entry.C;
import mh.struct.entry.Entry;
import qgb.interfaces.QRunable;

public abstract class AbstractGetEntry implements GetEntry {
	protected int giRetry = 1;
	protected ResultsPanel gRP;
	protected FindPanel gFP;
	public AbstractGetEntry(ResultsPanel aRP ,FindPanel aFP) {
		this(3,aRP, aFP);
	}

	public AbstractGetEntry(int aiRetry,ResultsPanel aRP ,FindPanel aFP)
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
	protected volatile boolean gbNotStop=true,gbIsDone=false;
	/**throws SocketTimeoutException, IllegalArgumentException, 
	 * MalformedURLException, IOException **/
	private ArrayList<QRunable> gqruns=new ArrayList<QRunable>();
	@Override
	public void stop(){
		gbNotStop=false;
		for(QRunable run:gqruns){
			run.stop();
		}
	}
	@Override
	public boolean isStop() {return !gbNotStop;}
	@Override
	public boolean isDone() {return gbIsDone;}
	
	/**Senten voice**/
	protected void getVoice(Voice av,int aindex) {
		GetSentenV getV=new GetSentenV(av,gRP, aindex);
		gqruns.add(getV);
		Thread tpv=new Thread(getV);
		tpv.start();
	}
	/**Phon voice**/
	protected void getVoice(Voice av,C ac) {
		GetPhonV getV=new GetPhonV(av,gRP, ac);
		gqruns.add(getV);
		Thread tpv=new Thread(getV);
		tpv.start();
	}
	
	abstract public Entry byWord(String ast);

}
