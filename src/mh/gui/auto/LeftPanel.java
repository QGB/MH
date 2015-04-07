package mh.gui.auto;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import mh.MainLoader;
import mh.gui.FindPanel;
import mh.gui.R;
import mh.gui.results.phon.PhonPanel;
import mh.gui.results.phon.PhonPanels;
import mh.struct.Voice;
import mh.struct.entry.C;
import mh.struct.entry.Phon;
import mh.struct.entry.Word;
import net.miginfocom.swing.MigLayout;
import qgb.T;
import qgb.interfaces.QRunable;
import qgb.swing.QST;
import test.feature.Final;

public class LeftPanel extends JPanel {
	public static void main(String[] args) {
		MainLoader.main(null);
		if (true)
			return;
		EventQueue.invokeLater(grun);
	}
	private static Runnable grun=new Runnable() {
		public void run() {
			try {
				final JFrame f = new JFrame(TextPanel.class.getName());
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				f.setLayout(new GridLayout(1, 1));
				//f.setLayout(new GridLayout(4, 1));
				final LeftPanel jp_f = new LeftPanel(null,null);
//				QST.setAllBackground(jp_f, Color.BLACK);
//				QST.setAllForeground(jp_f, Color.GREEN);
				f.getContentPane().add(jp_f);
				f.pack();
				f.setLocation(411-300, 73-16);
				f.setMinimumSize(new Dimension(R.Frame.iwMin +R.Find.iWidthMin,
						R.Frame.ihMin +R.Find.iHeightMin));
				f.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	};
	/************Test Code End**********************/
	final TextPanel gTxtP=new TextPanel();
	final TablePanel gTableP;
	final FindPanel gFP;
	final AutoFindWords gQRun;
	private final JButton gBtnAF=new JButton("Auto Find!");
	private final JButton gBtnStop=new JButton("STOP!");
	public LeftPanel(FindPanel aFP,TablePanel aTablePanel) {
		super(new MigLayout(R.MigL.sL_noBorder, "[grow]","[grow]0[]"));
		gFP=aFP;
		gTableP=aTablePanel;
		
		//CreateComponents();
		gQRun=new AutoFindWords(aFP, this);
		AddListener();
		
		add(gTxtP, "growx,growy,wrap");
		add(gBtnAF,"growx,split 2");
		add(gBtnStop,"growx");
	}

	private void AddListener() {
		ActionListener actionAF = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gBtnAF.setEnabled(false);
				gQRun.stop();
				gQRun.setText(gTxtP.getText());
				new Thread(gQRun).start();
			}
		};
		
		ActionListener actionStop = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gQRun.stop();
				reset();
			}
		};
		gBtnAF.addActionListener(actionAF);
		gBtnStop.addActionListener(actionStop);
	}
	/** AutoFindWords 调用了此方法**/
	public void reset() {
		//
		gBtnAF.setEnabled(true);
		T.sleep(999);
		gFP.gRP.gkSoft.stop();
	}
	
}
