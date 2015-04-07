import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import qgb.T;

public class Addressbook {
	public static void main(String[] args) {
		Person[] yp = new Person[99];
		//T.print(yp);
		for (int i = 0; i < yp.length; i++) {
			yp[i] = new Person("nannme" + i, "tel" + i);
		}
		T.print(yp);
		if(yp.length>3)return;
		String strIn = T.msgbox("input a name");

		for (int i = 0; i < yp.length; i++) {
			if (yp[i].isName(strIn)) {
				T.print("找到" + yp[i]);
				break;
			}
			if(i==yp.length-1){
				T.print("没有找到" + strIn);
			}
		}

//		// p.search(T.msgbox(""));

	}
}
