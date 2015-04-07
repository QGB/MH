package test;

import qgb.T;


public class loop {
	
	public static void main(String[] args)  {	

		for (int i = 0; i < 50; i++) {
			if (i==2) {
				continue;
			}
			
			if (i==4) {
				break;
			}
			
			T.print(i);
		}
		
		T.print("end!");
	}
	
	
	
}
