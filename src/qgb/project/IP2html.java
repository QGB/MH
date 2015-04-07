package qgb.project;

import qgb.T;
import qgb.text.QText;

public class IP2html {


	public static void main(String[] args) {
		if(args.length!=1){
			T.print(T.getCurrentClass().getName()+ " FileName");
			T.exit();
		}
		String stin=T.read_st("./"+args[0]).replaceAll(" ","");
		//T.write(s, ayb);
		T.print(stin);
		String[] yst;
		if(stin.contains(QText.gsWinNewline)){
			yst=stin.split(QText.gsWinNewline);
		}else {
			yst=stin.split("\n");
		}
		for (int i = 0; i < yst.length; i++) {
			
		}
	}
}
