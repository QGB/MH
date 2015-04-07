package test.feature;

import qgb.T;

public class Overload {

	public static void main(String[] args) {
		T.print(Overload.class);
		
		Object oa=new Object();
		
		o1(oa);
		oa="str";
		o1(oa);
		String sta=(String) new Object();
		o1(sta);
	}

	public static String o1(Object ao) {
		T.print(ao);
		return null;
	}
	
	public static String o1(String ao) {
		T.print(ao);
		return null;
	}
}
