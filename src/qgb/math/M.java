package qgb.math;

import qgb.T;

public class M {

	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			T.print(ri(-5,5)); 
		}

	}

	public static Integer ri(int aimin,int aimax) {
		return (int)Math.rint(Math.random()*(aimax-aimin)+aimin);
	}
}
