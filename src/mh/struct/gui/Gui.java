package mh.struct.gui;

import mh_struct.Entry.Sentence;

public class Gui {
	public static String cyst_column[]={"","num","word","translations","progress", "reject", "lastReviewTime","AddTime","WAIT_DAY"};
	public static int cyi_column[]=       {40, 50     ,90        ,90                  ,20              ,20         ,90                           ,90              ,20                };
/////////////////////////////////////////////////////////
	public DoubleText[] DTsentence;
	public class DoubleText {
		public Text e;
		public Text c;

		///////////////////////////////////setBounds
		public DoubleText(Shell shell){
			e=new Text(shell, SWT.BORDER);
			c=new Text(shell, SWT.BORDER);
		}

		public void setBounds(int x, int y, int width, int height) {
			e.setBounds(x, y, width, height);
			c.setBounds(x, y + height, width, height);
		}

		public void setEditable(boolean editable) {
			e.setEditable(editable);
			c.setEditable(editable);
		}

		public void setText(Sentence sentence) {
			e.setText(sentence.ds.st_e);
			c.setText(sentence.ds.st_c);
		}

	}

}
/*
.�汾 2

gvty_Sqlfield [1] �� "number"

gvty_Sqlfield [2] �� "NAME"
gvty_Sqlfield [3] �� "PRONUNCIATION"
gvty_Sqlfield [4] �� "TEXT_A"

gvty_Sqlfield [5] �� "progress"
gvty_Sqlfield [6] �� "reject"
gvty_Sqlfield [7] �� "lastReviewTime"


gvty_Sqlfield [8] �� "PICTURE"
gvty_Sqlfield [9] �� "CLASS_A"
gvty_Sqlfield [10] �� "time_a"
gvty_Sqlfield [11] �� "SIMILAR"
gvty_Sqlfield [12] �� "WAIT_DAY"

*/