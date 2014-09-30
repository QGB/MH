package test.feature;

import qgb.T;

public class Final {

	public static void main(String[] args) {
		final String str;//="123";
		if (0==0) {
			str="123";
		}
		
		T.print(str);
		if (0==0) {
			str="0";
		}
		
		T.print(str);
	}

}
