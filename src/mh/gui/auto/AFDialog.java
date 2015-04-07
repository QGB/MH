package mh.gui.auto;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

import com.alee.laf.WebLookAndFeel;

import qgb.T;
import qgb.swing.QST;
import mh.MainLoader;
import mh.gui.FindPanel;
import mh.gui.MainFrame;
import mh.gui.R;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AFDialog extends JDialog {
	public static void main(String[] args) {
		MainLoader.main(null);
		if (true) {
			return;
		}
		try {
			AFDialog dialog = new AFDialog(null,null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**********Test Code End*******************/
	private final Frame gFrame;
	private final FindPanel gFP;
	public AFDialog(Frame af,FindPanel aFP) {
		super(af,AutoFindWords.class.getName(),false);
		gFrame=af;
		gFrame.setSize(500, 400);
		gFrame.setLocation(QST.getBRLocation(gFrame.getSize()));

		gFP=aFP;
		setBounds(new Rectangle(0, 0, 800,740));
		getContentPane().setLayout(new BorderLayout());
		getContentPane().setLayout(new MigLayout(R.MigL.sL_noBorder, "[]0[grow]","[grow]"));
		TablePanel tableP= new TablePanel();
		
		getContentPane().add(new LeftPanel(gFP,tableP),"w 111:222:333,growy");
		getContentPane().add(tableP,"growx,growy");
		
		//T.msgbox(T.getCurrentThreadName());
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	private void AddListener() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				gFrame.setEnabled(true);
			}
		});
	}
}
