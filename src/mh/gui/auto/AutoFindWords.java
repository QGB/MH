package mh.gui.auto;

import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

import mh.MainLoader;
import mh.database.MDT;
import mh.gui.FindPanel;
import mh.struct.entry.Entry;

import qgb.T;
import qgb.interfaces.QRunable;
import qgb.text.QText;

public class AutoFindWords implements QRunable{
	public static void main(String[] args) {
		//T.setOutStream("sqlErr.txt");
		MainLoader.main(null);
		if (true) {
			return;
		}
		//byWord("writable");
		for (String stw : textToList(T.read_st("e.tx  t"))) {
			T.write("", "");
		}
	}
////////////////////Test Code End////////////////////////////////////
	public static Collection<String> textToList(String ast) {
		Set<String> sStr = new TreeSet<String>();
		{
			ast = ast.replace(QText.gsWinNewline, " ");
			ast=QText.replaceChars(ast," ","1234567890?/:;\n\"\\“”,.|`~!@#$%^&*_=+()[]{}<>");
			ast = QText.replaceAll(ast, "  ", " ").toLowerCase();
			String[] yst = ast.split(" ");// Y.StrToArray(stin, " ");
			// T.print(yst);
			for (int i = 0; i < yst.length; i++) {
				if (yst[i].length() > 2) {
					sStr.add(yst[i]);
				}
			}
		}
		return sStr;
	}
/////////////////////////////////////////////
	private Collection<String> gWordList;
	final LeftPanel gLP;
	final TablePanel gTP;
	final FindPanel gFP;
	
	public AutoFindWords(FindPanel aFP, LeftPanel aLeftPanel) {
		gFP=aFP;
		gLP=aLeftPanel;
		gTP=gLP.gTableP;
	}
	
	public void setText(String ast) {
		gWordList=textToList(ast);
	}
	@Override
	public void run() {
		gbIsDone=false;
		gbNotStop=true;
	
		
		for(String stw:gWordList){
			gTP.addWord(stw);
		}
		
		
		String str="";
		int index=-1;
		gFP.getFrame().setEnabled(false);
		for(String stw:gWordList){
			//T.msgbox(stw);
			if (isStop()) {gLP.reset();return;}
			index++;
			if (index%11==0) {
				System.gc();
			}
			Entry e=null;
			if(MDT.EntryExists(stw)){
				gTP.addTrans(index, MDT.getTrans(stw));
				//T.msgbox(MDT.getTrans(stw));
				continue;
			}else {
				//gFP.gRP.stop();
				//gFP.getFrame().setEnabled(false);
				//gFP.gRP.gkSoft.stop();
				//T.sleep(50);
//				T.print("Begain[%s]",stw);
//				if (stw.equals("quora")||stw.equals("quora's")) {
//					T.print("ka");
//				}
				e=gFP.gRP.gkSoft.byWord(stw);
				//T.sleep(50);
				//gFP.getFrame().setEnabled(true);
				//T.print("End[%s]",stw);
			}
			if (e==null) {
				continue;
			}else {
				//T.print("[%s]%s",MDT.EntryExists(stw),stw);
				MDT.save(e);
			}
			str=e.trans.toString();

			gTP.addTrans(index, str);
			
			e.word.ps.playOneVoice();
			
			//if (isStop()) {gLP.reset();return;}
			
		}
		gLP.reset();
		gFP.getFrame().setEnabled(true);
		gbIsDone=true;
	}

	
	
	//请谨慎使用 gbXXX 的值
	protected volatile boolean gbNotStop=true,gbIsDone=false;
	@Override
	public void stop(){gbNotStop=false;}
	@Override
	public boolean isStop() { return !gbNotStop;}
	@Override
	public boolean isDone() {return gbIsDone;}
}
