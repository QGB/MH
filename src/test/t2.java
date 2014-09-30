package test;

import qgb.T;

public class t2 {
	static int in=0;
	
	public static void main(String[] args)  {	
//		String st1;
//		st1=T.read_st("t.txt");
//		//String st2=new String();
//		T.print(st1.equals("正在加载的翻译解释"));
		in=in+1;
		if (in>10000) {
			System.exit(0);
		}
		T.print(in);
		main(null);
		
		//T.print(st1==st2);
	}
	
	
	
}
