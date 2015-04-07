package test.feature;

import qgb.T;
import qgb.project.ccsu.practicum2014.model.Teacher;


class P{
	String gsN;

	public P() {
		gsN="刘";
		System.out.print(gsN);
	}
	
}

class Tea extends P{
	String gsZ;

	public Tea(String gsZ) {
		this.gsZ = gsZ;
		T.print(gsZ);
	}
	
}
public class FatherClassTest {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Tea t1=new Tea("副教授");
	}

}
