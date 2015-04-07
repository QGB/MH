package test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import qgb.T;

public class CopyTest {

	public static void main(String[] args) {
		try {
			FileInputStream fis=new FileInputStream(T.msgbox("原路径:"));
			FileOutputStream fos=new FileOutputStream(T.msgbox("目标路径:"));
			byte[] yb=new byte[1];
			while (fis.read(yb)!=-1) {
				fos.write(yb);
			}
			fos.flush();
			fis.close();
			fos.close();
		} catch (FileNotFoundException e) {
			T.print("文件未找到！");
			
			return;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
