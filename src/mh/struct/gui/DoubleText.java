package mh.struct.gui;

import mh.struct.entry.Entry.Sentence;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class Text txtTranslations; {
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
