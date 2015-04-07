package qgb.project.tool;


import java.io.File;

import mh.gui.MainFrame;
import qgb.T;
import qgb.file.F;
import qgb.text.QText;

public class ReplaceTexts {
	static String gstPath="D:/java/eclipse/MH/src/qgb/project/ccsu/practicum2014/";
	public final static String stOld="Techer",stNew="Teacher";
	public static void main(String[] args) {
		T.print(ReplaceTexts.class.getName());
		String[] yst=F.getFilesStringArray(gstPath, ".java");
		String sta="";
		int ia=0;
		for (int i = 0; i < yst.length; i++) {
			//T.msgbox(yst[i]);
			sta=T.read_st(yst[i]);
			
			if (sta.contains(stOld)) {
				T.print("Replace [%d]%s/n",i,yst[i]);
				sta=QText.replaceAll(sta, stOld, stNew);
				//T.print(sta);
				//T.msgbox();
				T.write(yst[i], sta);
			}
			/**Ìæ»»ÎÄ¼þÃû*/
			final int iSeparator=yst[i].lastIndexOf("\\")+1; 
			String sFP=yst[i].substring(0,iSeparator),sFN=yst[i].substring(iSeparator, yst[i].length());
			//T.msgbox(sFP+"\n"+sFN);
			if (sFN.contains(stOld)) {
				F.rename(sFP,sFN,sFN.replaceAll(stOld,stNew));
				//T.msgbox(sFN);
				T.print("Rename:"+yst[i]);
			}
		}
		
	}

	
	
}
