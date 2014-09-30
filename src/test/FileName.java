package test;

import qgb.T;


public class FileName {
	
	public static void main(String[] args)  {	
		for (int i = 0; i < 1000; i++) {
			T.write(T.repeat(i, "-"), "");
			//file name max lengh 255.
		}
	}
	
	
	
}
