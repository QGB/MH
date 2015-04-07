package qgb.project;

import java.util.Calendar;
import java.util.GregorianCalendar;

import qgb.T;
import qgb.text.QText;
public class CalenderForMon {
	public static void main(String[] args) {
		T.print();
		String stYear=T.msgbox("������ݣ�");
		String stMon=T.msgbox("�����·ݣ�");
		int iYear=-1, iMon=-1;
		try {
			iYear = Integer.valueOf(stYear);
			iMon = Integer.valueOf(stMon);
		} catch (NumberFormatException e) {
			T.print("���뷶Χ̫��");
			return;
		}
		
		//int iYear=2014,iMon=10;
		if(iMon<1||iMon>12){T.print("�������");return;}
		GregorianCalendar gc=new GregorianCalendar(iYear, iMon-1, 1);
		T.print("SUN MON THE WED THU FRI SAT");
		System.out.print(QText.repeat(gc.get(Calendar.DAY_OF_WEEK)-1, "    "));
		for (int i = 0; i <gc.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
			int iday=gc.get(Calendar.DAY_OF_MONTH);
			if (i>20&&iday==1) {
				return;
			}
			System.out.print(QText.pad(iday,4));
			if (gc.get(Calendar.DAY_OF_WEEK)==7) {
				T.print("");
			}
			gc.add(Calendar.DAY_OF_MONTH, 1);
		}
	}
}
