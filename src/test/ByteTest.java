package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import qgb.T;

public class ByteTest {

	public static void main(String[] args) {
		byte[] yb2={1,2,3,4};
		byte[] yb3={1,2,3,4};
		byte[] yb1={1,2,3,4};
		T.print(Arrays.equals(yb3, yb1));
		T.exit();
		ArrayList<byte[]> y2b=new ArrayList<byte[]>();
		
		for (int i = 0; i < 44; i++) {
			byte[] yb={1,2,3,4};
			y2b.add(yb);
		}
		
		for(byte[] yb:y2b){
			T.print(yb.hashCode());
		}
		
//		T.print(yb1.hashCode());
//		T.print(yb2.hashCode());
//		T.print(yb3.hashCode());
	}

}
