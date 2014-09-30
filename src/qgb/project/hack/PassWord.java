package qgb.project.hack;

import qgb.Array;
import qgb.T;
import qgb.text.Text;

public class PassWord {
	static String[] gyst36=new String[36];
	
	
	public static void main(String[] args) {
		T.print(PassWord.class);
		//create1();
		//create4();
		
		//T.exit();
		
		delMuti();
		//order();
		//createDigital();
		//mixUp();
	}

	private static void order() {
		String stname="p";
		String[] yst= T.read_st(stname+".txt").split("\r\n");
		T.print(yst.length);
		yst= Array.del_muti(yst);
		T.print(yst.length);
		StringBuilder sb=new StringBuilder();
		for (int i = 0; i < yst.length; i++) {
			sb.append(yst[i]+"\r\n");
		}
		T.write("delMuti-"+stname+".txt", sb.toString());
		
	}

	private static void delMuti() {
		String stname="p";
		String[] yst= T.read_st(stname+".txt").split("\r\n");
		T.print(yst.length);
		yst= Array.del_muti(yst);
		T.print(yst.length);
		StringBuilder sb=new StringBuilder();
		for (int i = 0; i < yst.length; i++) {
			sb.append(yst[i]+"\r\n");
		}
		T.write("delMuti-"+stname+".txt", sb.toString());
	}

	private static void create1() {
		String stn="0123456789",sta="abcdefghijklmnopqrstuvwxyz"
				,stb="`~!@#$%^&*()-_=+[{]};:'";
		String str="";
		str=stn+sta;
		for (int i = 0; i < gyst36.length; i++) {
			gyst36[i]=str.substring(i, i+1);
		}
		//T.print(stb.length());
		str="";
		stb=stn+sta+sta.toUpperCase()+stb;
		T.print(stb.length());
		
		for (int i = 0; i < stb.length(); i++) {
			str=str + stb.substring(i, i+1)+"\r\n";
		}
		
		
		T.write("1p.txt", str);
		//T.print(gyst36);
		//T.print(sta.length());
		
		
	}

	private static void create4() {
		StringBuilder sbd=new StringBuilder();
		
		for (int i1 = 0; i1 < gyst36.length; i1++) {
			for (int i2 = 0; i2 < gyst36.length; i2++) {
				//for (int i3 = 0; i3 < gyst36.length; i3++) {
					//for (int i4 = 0; i4 < gyst36.length; i4++) {
						sbd.append(gyst36[i1]+gyst36[i2]+"\r\n");
						
						
					//}
				//}
			}
		}
		
		T.write("2p.txt", sbd.toString());
		
	}

	private static void mixUp() {
		String sta="",stb="";
		sta= T.read_st("p.txt");
		stb=T.read_st("7d.txt");
		//Array.StrToArray();
	
	}

	private static void createDigital() {
		StringBuffer sb =new StringBuffer();
		for (int i = 0; i < 1234567; i++) {
			sb.append(i+"\n");
		}
		T.write("7d.txt", sb.toString());
	}
	
	
	
}
