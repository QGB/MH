package qgb.algorithm;

import java.io.IOException;

import qgb.T;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
/**
 * BASE64º”Ω‚√‹
 * 
 */
public class Base64 {

	public static void main(String[] args) {
		//encrypt(key);
		//T.cst_test_path=T.cst_test_path+"base64/";
		T.printProperties();
		String sta="nnrsbcufcqc3wlx0xin5ur4v";
		T.write(sta, decrypt(sta));
		T.print(encrypt(T.read_byteArray(sta)));
		T.write("e", encrypt(T.read_byteArray(sta)));
		T.write("sta", sta);
		T.print(sta);
		
		T.print(encrypt(T.read_byteArray(sta)).equals(sta));
	}
	

	public static byte[] decrypt(String key)  {
		try {
			return (new BASE64Decoder()).decodeBuffer(key);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


	public static String encrypt(byte[] key) {
		return (new BASE64Encoder()).encodeBuffer(key);
	}

}
