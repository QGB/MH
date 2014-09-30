package qgb;

import qgb.T;

public class Convert {

	public static void main(String[] args) {
		HexToByteArray("3A4b5c6d");
	}
	
	
	public static void ByteArrayToHex(Byte[] ast) {
		String stemp="";
		stemp= ast.toString();
		T.print(stemp);

	}
	
	public static byte[] HexToByteArray(String ast) {
		if (ast.length()%2!=0) {
			T.notify("illegal input!");
			return null;
		}
		ast=ast.toUpperCase();
		
		String stemp="";
		for (int i = 0; i < ast.length(); i++) {
			if (i%2==0) {
				stemp=ast.substring(i,i+2);
				//T.print(stemp);
			}
		}
		stemp= ast.toString();
		return null;
		

	}
	
	
}
