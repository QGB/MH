package mh.gui.auto;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import mh.struct.entry.Translations;
import mh.struct.entry.Word;
import qgb.T;

public class AFTableModel extends DefaultTableModel {
	public static void main(String[] args) {
		TablePanel.main(null);
	}

	// ////////////////////////////////////////////////////
//	public final static TableColumnModel gTColModel=new DefaultTableColumnModel();
//	static{
//		gTColModel.addColumn(new TableColumn(0, 9));
//		gTColModel.addColumn(new TableColumn(1, 50));
//		gTColModel.addColumn(new TableColumn(2, 99));
//	}
	String gsTest = "kanw";
	public static final String gsColNum=Number.class.getSimpleName();
	public static final String gsColWord=Word.class.getSimpleName();
	public static final String gsColTrans=Translations.class.getSimpleName();
	// Vector gVcolumn=new Vector<String>();
	public AFTableModel() {
		super(0,3);
		setColumnIdentifiers(getColumn());
	}

	protected static Vector<String> getColumn() {
		Vector<String> Vcolumn = new Vector<String>();
		Vcolumn.add(gsColNum);
		Vcolumn.add(gsColWord);
		Vcolumn.add(gsColTrans);
		return Vcolumn;
	}

	public int addWord(String ast) {
		String[] yst = new String[3];
		final int index=getRowCount();
		yst[0]=String.valueOf(index);
		yst[1] = ast;
		addRow(yst);
		return index;
	}

	
	// public int getRowCount() {
	// return 10;
	// }

	// public Object getValueAt(int r, int c) {
	// //T.msgbox(T.getCurrentClass());
	// switch (c) {
	// case 0:
	// return (r + 1) + ".";
	// case 1:
	// return "Some pithy quote #" + r;
	// case 2:
	// return gsTest;
	// // return data[r];
	// }
	//
	// return "Bad Column";
	// }

	// public Class getColumnClass(int c) {
	// return String.class;
	// }

	// Make Column 2 editable...
	@Override
	public boolean isCellEditable(int r, int c) {
		return c > 1;
	}

	// public void setValueAt(Object value, int r, int c) {
	//
	// // data[r] = (ColorName) value;
	// }
	public void changeTest(String ast) {
		gsTest = ast;
	}



}
