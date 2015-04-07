package mh.net.test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.lang.reflect.Field;

import qgb.T;
import qgb.net.HttpRequest;
import qgb.text.Text;

/**
 * 2012-12-07/index.php?c=client&word=book
 * **/
public class KS_UrlTest {
	public static void main(String[] args) {
		T.setOutStream("/MH/" + KS_UrlTest.class.getName() + "_404.txt");
		String sUrl="";
		for (int i = 0; i < 4581; i++) {
			sUrl="http://ct.dict-client.iciba.com/"+
					lastDay()+"/index.php?c=client&word=book";
			
			try {
				HttpRequest.get_withException(sUrl);
				System.err.println(sUrl);
			} catch (FileNotFoundException e) {
				T.print("!Not found "+sUrl);
			}
			
		}
	}
	
	private static GregorianCalendar ggc=new GregorianCalendar(
			2012,12-1, 7,0, 0);
	private static String lastDay() {
		ggc.add(Calendar.DAY_OF_MONTH, -1);
		int iyear=ggc.get(Calendar.YEAR);
		int imonth=ggc.get(Calendar.MONTH)+1;
		int iday=ggc.get(Calendar.DAY_OF_MONTH);
		return iyear+"-"+Text.padHead(imonth, 2, "0") +
				"-"+Text.padHead(iday, 2, "0");
	}
	
	private static String nextDay() {
		ggc.add(Calendar.DAY_OF_MONTH, 1);
		int iyear=ggc.get(Calendar.YEAR);
		int imonth=ggc.get(Calendar.MONTH)+1;
		int iday=ggc.get(Calendar.DAY_OF_MONTH);
		return iyear+"-"+Text.padHead(imonth, 2, "0") +
				"-"+Text.padHead(iday, 2, "0");
	}

	public static void CalendarTest() {
		T.setOutStream("/MH/" + KS_UrlTest.class.getName() + "_Out.txt");
		//gc.add(Calendar.DAY_OF_MONTH, 1);
		
		GregorianCalendar gc = new GregorianCalendar();
		Field[] yf = Calendar.class.getDeclaredFields();
		
		T.print(	Calendar.class.toString()+" Test");
		T.print(Text.repeat(33, "-"));
		T.print("%-20s|%-3s|%-9s","FieldName","Min","Max");
		T.print(Text.repeat(33, "-"));
		try {
			for (int i = 0; i < 16; i++) {
				T.print("%-20s|%-3s|%-9s", yf[i].getName(),
						gc.getActualMinimum((Integer) yf[i].get(null)),
						gc.getActualMaximum((Integer) yf[i].get(null)));
			}
			
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}
