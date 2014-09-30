package mh.gui;

import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import qgb.T;
import qgb.swing.QST;

public class R {
	public final static String sResPath="./res/";
	public final static String sResPDis="disable/";
	
	public static class Find {
		private static final String stP = R.sResPath+"find/";
		private static final String stPD = stP + R.sResPDis;
		
		public static final ImageIcon iconBack =
				QST.getScaledIcon(stP+"back.png",R.Icon.iMin,R.Icon.iMin);
		public final static ImageIcon iconBackDis = 
				QST.getScaledIcon(stPD + "back.png",R.Icon.iMin,R.Icon.iMin);

		public final static ImageIcon iconForward =
				QST.getScaledIcon(stP+"Forward.png",R.Icon.iMin,R.Icon.iMin);
		public final static ImageIcon iconForwardDis = 
				QST.getScaledIcon(stPD + "Forward.png",R.Icon.iMin,R.Icon.iMin);

		public final static ImageIcon iconFind = 
				QST.getScaledIcon(stP + "find.png",R.Icon.iMin+2,R.Icon.iMin+2);
		public final static ImageIcon iconFindDis = 
				QST.getScaledIcon(stPD + "find.png",R.Icon.iMin+2,R.Icon.iMin+2);
	
		public final static int iWidthMin=Btn.iMin*3+MigL.iCW_InputLineMin;
		public final static int iWidthMax=iWidthMin+MigL.iCW_InputLineDif+MigL.iMinDif;
		public static final int iHeightMin = 7+MigL.iMin+7;/**7 is Miglayout border**/
	}
	
	public static class Phon {
		private static final String stP = R.sResPath+"phon/";
		private static final String stPD = stP + R.sResPDis;
		
		public static final ImageIcon iconUK =
				QST.getScaledIcon(stP+"UK.png",R.Icon.iMin,R.Icon.iMin);
//		public final static ImageIcon iconUKDis = 
//				QST.getScaledIcon(stPD + "UK.png",R.Icon.iMin,R.Icon.iMin);

		public final static ImageIcon iconUS =
				QST.getScaledIcon(stP+"US.png",R.Icon.iMin,R.Icon.iMin);
//		public final static ImageIcon iconForwardDis = 
//				QST.getScaledIcon(stPD + "Forward.png",R.Icon.iMin,R.Icon.iMin);

		public final static ImageIcon iconP = 
				QST.getScaledIcon(stP + "p.png",R.Icon.iMin,R.Icon.iMin);
//		public final static ImageIcon iconFindDis = 
//				QST.getScaledIcon(stPD + "p.png",R.Icon.iMin+2,R.Icon.iMin+2);

		public final static int iWidthMin=Find.iWidthMin/3;
		//public final static int iWidthMax=iWidthMin+MigL.iCW_InputLineDif+MigL.iMinDif;
		public static final int iHeightMin = 7+MigL.iMin+7;/**7 is Miglayout border**/
	}
	
	public static class Senten {
		public static final String stP = R.sResPath+"senten/";
		//private static final String stPD = stP + R.sResPDis;

		public final static int iWidthMin=7+MigL.iMin*2+7;
		public static final int iHeightMin = 7+MigL.iMin*2+7;/**7 is Miglayout border**/
	}
	
	public static class Icon {
		public final static int iMin=30;
	}
	
	public static class Btn {
		public final static int iMin=Icon.iMin+2;
	}
	
	public static class ProBar {
		public final static int iwMin=Btn.iMin/3;
		public final static int ihMin=iwMin;
		public final static String sCHmin="h "+ihMin+":"+ihMin+":"+ihMin;
		public final static String sCHmax="h "+ihMin*2+":"+ihMin*2+":"+ihMin*2;
	}
	
	public static class Frame {
		public static void set(JFrame af) {
			Point p=af.getRootPane().getLocation();
			//SwingUtilities.convertPointToScreen(p, af);
			T.print(p);
			iwMin=p.x;
			ihMin=p.y;
		}
		public static int iwMin=10;
		public static int ihMin=15;
	}
	
	/**MigLayout**/
	public static class MigL {
		public final static int iMin=Btn.iMin;
		public final static int iMinDif=20;
		public final static int iMinL1=iMin+iMinDif;
		
		public final static int iCW_InputLineMin=180;
		public final static int iCW_InputLineDif=70;
		public final static int iCW_InputLineMax=iCW_InputLineMin+iCW_InputLineDif+999;
		
		
		
		/**layout Constraints **/
		public final static String sLmin="["+iMin+":"+iMin+":"+iMin+"]";
		public final static String sL2min="["+iMin*2+":"+iMin*2+":"+iMin*2+"]";
		public final static String sL_noBorder="insets 0 0 0 0"	;	
		public final static String sLW_find=
				"["+Find.iWidthMin+":"+Find.iWidthMax+":"+Find.iWidthMax+"]";
		
		/**Component constraints: height**/
		public final static String sCHmin="h "+iMin+":"+iMin+":"+iMin;
		public final static String sCH2min="h "+iMin*2+":"+iMin*2+":"+iMin*2;
		/**Component constraints: width**/
		public final static String sCWmin="w "+iMin+":"+iMin+":"+iMin;
		
		public final static String sCW_btnMinLong="w "+iMin+":"+iMinL1+":"+iMinL1;
		
		/**Component constraints**/
		public final static String sCmin=sCHmin+","+sCWmin;
		
		/**Component constraints**/
		public final static String sC_inputLine=sCHmin+",w "+iCW_InputLineMin+"::";
//				",w "+iCW_InputLineMin+":"+
//				(iCW_InputLineMin+iCW_InputLineDif)+":"+iCW_InputLineMax;
		
	}
	
	public static void main(String[] args) {
		FindPanel.main(null);
	}
}
