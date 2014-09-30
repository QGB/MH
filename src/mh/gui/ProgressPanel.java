package mh.gui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import mh.net.kingsoft.KingSoft;
import mh.struct.entry.Entry;
import net.miginfocom.swing.MigLayout;
import qgb.T;
import qgb.swing.QST;

public class ProgressPanel extends JPanel {
	private JProgressBar gProBar;
	private JLabel gLabel;
	public String gsC_migl="growx";
	public ProgressPanel(int ai_max) {
		super(new MigLayout(R.MigL.sL_noBorder, "[grow]", "[]0[]"));
		if (ai_max<1) {
			T.argsError(ai_max);
		}
		gProBar=new JProgressBar(SwingConstants.HORIZONTAL, 0, ai_max);
		gLabel=new JLabel();
		
		
		int iw=ai_max*R.ProBar.iwMin+10;
		String sCW="w "+iw+":"+iw+":"+iw;
		
		//gsC_migl=R.ProBar.sCHmax+","+sCW;
		add(gLabel,R.ProBar.sCHmin+",growx,wrap");
		add(gProBar, R.ProBar.sCHmin+",growx");
		//add(CBInput,R.MigL.sC_inputLine);
		//add(btnFind, R.MigL.sCHmin+","+R.MigL.sCW_btnMinLong);
		
	}

	public void go(final String ast) {
		if (gProBar.getValue()>=gProBar.getMaximum()) {
			throw new IllegalStateException("out of "+T.getCurrentClass().getName()+
					"("+gProBar.getMaximum()+")");
		}
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				gProBar.setValue(gProBar.getValue()+1);
				gLabel.setText(ast);
			}
		});
	}
	public void reset() {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				gProBar.setValue(0);
				gLabel.setText("");
			}
		});
	}
	
	public int getiMax() {
		return gProBar.getMaximum();
	}
	/********************************************/
	private final static ProgressPanel gpp = new ProgressPanel(30);
	public static void main(String[] args) {
		EventQueue.invokeLater(grun);
		
//		KingSoft kingsoft = new KingSoft(gpp);
//		Entry e = kingsoft.ByWord("book");
//		e.print();
		//e.word.vUK.play();
		for (int i = 0; i < 26; i++) {
			gpp.go(qgb.text.QText.repeat(3, "12345"));
			T.sleep(444);
		}
		T.sleep(444);
		gpp.reset();
		T.sleep(9999);
		T.exit();
	}
	static Runnable grun=new Runnable() {
		public void run() {
			try {
				JFrame f = new JFrame(T.getCurrentClass().getName());
				f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
				f.setLayout(new MigLayout(R.MigL.sL_noBorder, "[grow]", ""));
				
				f.getContentPane().add(gpp,gpp.gsC_migl);
				f.pack();

				f.setLocation(411, 73);
//				f.setMinimumSize(new Dimension(36 +R.Find.iWidthMin,
//						30 +R.Find.iHeightMin));
				// f.setMaximumSize(new Dimension(36+32+32+400+32,
				// 30+7+32+7+9));
				f.setVisible(true);
				QST.setAllBackground(gpp, Color.BLACK);
				QST.setAllForeground(gpp, Color.GREEN);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	};
	public void showMsg(final String string) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				gProBar.setValue(0);
				//reset();
				gLabel.setText(string);
			}
		});
	}
}
