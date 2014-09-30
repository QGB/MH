package qgb.project.tool;


import java.io.File;

import qgb.T;
import qgb.file.F;

public class AddPackageDeclation {
	static String gstPath="D:/java/eclipse/TIJ4/src/";
	
	public static void main(String[] args) {
		T.print(AddPackageDeclation.class.getName());
		String[] yst=F.getFilesStringArray(gstPath, ".java");
		String sta="",stb="";
		int ia=0;
		for (int i = 0; i < yst.length; i++) {
			sta=T.read_st(yst[i]);
			
			ia=yst[i].lastIndexOf("\\");
			stb=yst[i].substring(gstPath.length(),ia);
			stb= stb.replace("\\",".");
			stb="package "+stb+";";
			
			if (sta.contains(stb)) {
				T.print("!!!skip [%d]%s\n",i,yst[i]);
			}else {
				sta=stb+"\n"+sta;
				T.delFile(yst[i]);
				//T.msgbox(sta,yst[i]);
				
				T.write(yst[i], sta);
				T.print("[%d]%s\n",i,yst[i]);
			}
		}
		
	}

	
	
}
