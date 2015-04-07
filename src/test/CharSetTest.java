package test;

import qgb.T;
import qgb.file.F;

public class CharSetTest {

	public static void main(String[] args) {
		byte[] yb=F.hexToBytes("00AF");
		T.write("00AF", yb);
		String str=new String(yb);
		T.print(str);
		T.write("00AF.txt", str);

	}

}
