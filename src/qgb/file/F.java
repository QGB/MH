package qgb.file;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import com.sun.org.apache.bcel.internal.generic.NEW;

import qgb.T;

public class F {

	//////////////////////////////////////////////
	/**
	 *Example1: getFilesStringArray("d:/",".txt");</p>
	 *Example2: getFilesStringArray("d:",".txt");</p>
	 * 
	 * @see java.nio.charset.Charset
	 * @see qgb.T#write(String, InputStream)
	 * @see java.lang.String#getBytes()
	 */
	public static String[] getFilesStringArray(String filePath, String astFileType){
		Object[] yo=getFilesStringList(filePath, astFileType).toArray();
		return T.yObjectToYStr(yo) ;
		
	}
	
	public static ArrayList<String> getFilesStringList(String filePath, String astFileType) {
		File root = new File(filePath);
		File[] files = root.listFiles();
		ArrayList<String> filelist = new ArrayList<String>();
		String sta="";
		for (File file : files) {
			if (file.isDirectory()) {
				/*
				 * 递归调用
				 */
				filelist.addAll(getFilesStringList(file.getAbsolutePath()
						, astFileType));
			} else {
				sta=file.getAbsolutePath();
				if (FileTypeFilter.accept(sta, astFileType)) {
					filelist.add(sta);
				}
			}
		}
		return filelist;
	}

	// /////////////////////////////////////////////
	public static byte[] hexToBytes(String ast) {
		if (ast.length()%2!=0) {
			T.notify("illegal input! |ast.length()="+ast.length());
			return null;
		}
		
		byte[] yb =new byte[ast.length()/2];
		String str;
		int ia=0;
		for (int i = 0; i < ast.length()/2; i++) {
			str=ast.substring(i*2,i*2+2);
			ia=Integer.valueOf(str, 16);
			yb[i]=(byte)ia;
			//T.print( str.substring(0, 1));
			
			//char c='1';
			//T.print(c);
			//T.msgbox(str);
		}
		
		
		
		return yb;
	}
	
	
	public static String toHexString(InputStream ais) {
		byte[] yb;
		String str = "";
		yb = T.InputStreamToBytes(ais);
		for (int i = 0; i < yb.length; i++) {
			Byte b = yb[i];
			str = str + Integer.toString(b.intValue(), 16);
		}
		return str.toUpperCase();
	}

	public static String name(String ast) {
		ast = ast.replaceAll("[\\\\\\/\\:\\*\\?\\\"\\<\\>\\|]", "");
		ast = ast.substring(0, Math.min(ast.length(), 200));
		while (ast.startsWith(".")) {
			ast=ast.substring(1, ast.length());
		}
		//T.print(ast);
		return ast;
	}

	public static void main(String[] args) throws IOException {
		//InputStream is = T.read_bis("c.jsp");
//		String str = "";
//		str = ToHex(is);
//		T.write("c.txt", str);
		//T.print(Integer.valueOf());
		T.print(new String(hexToBytes("3031323334353637")));
		//T.print(getFilesStringArray("d:/test/", ".txt"));
	}
	
	/**不能使用File.isDirectory()判断一个不存在的文件是否为目录**/
	public static boolean isDirectory(String asPath) {
		if (asPath.endsWith("/")||asPath.endsWith("\\")) {
			return true;
		}
		return false;
	}

}
