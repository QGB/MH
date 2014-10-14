package qgb.algorithm;

import qgb.T;

public class IndexStr {

	public static void main(String[] args) {
		char[] sp="123".toCharArray(),st="ab313122321234b".toCharArray();
		st.toString().indexOf(str)
		T.prin  
	
	static int pIndex(char[] asp,char[] ast){
		return -1;
		
	}
	
	static int nIndex(char[] asp,char[] ast){
		//T.print("%d|%d",asp.length,ast.length);
		int ip=0,it=0;
		for (int i = 0; i < ast.length; i++) {
			ip=0;
			for (int j = 0; j < asp.length; j++) {
				if(asp[j]==ast[j+i]){
					ip++;
				}else break;
				if(ip==asp.length)return i;
			}
		}
		return -1;
	}
}
