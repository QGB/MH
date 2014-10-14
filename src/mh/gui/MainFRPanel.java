package mh.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import qgb.T;
import mh.gui.results.ResultsPanel;
import net.miginfocom.swing.MigLayout;

public class MainFRPanel extends JPanel {
	public static void main(String[] args) {
//		MainFrame.main(null);
//		if (true)
//			return;
		EventQueue.invokeLater(grun);
	}
	static Runnable grun = new Runnable() {
		public void run() {
			try {
				JFrame f = new JFrame(T.getCurrentClass().getName());
				f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
				f.getContentPane().setLayout(new GridLayout(1, 1));

				f.getContentPane().add(new MainFRPanel());
				f.pack();
				f.setLocation(411, 73);
				f.setVisible(true);
				f.getContentPane().setBackground(Color.BLACK);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	};
	/******************* Test End ***********************/
	public MainFRPanel() {
		setLayout(new MigLayout(R.MigL.sL_noBorder, "[grow]", "[]0[grow]0[]"));

		final ProgressPanel jp_pp=new ProgressPanel(50);
		final ResultsPanel jp_rp=new ResultsPanel(jp_pp);
		final FindPanel jp_fp = new FindPanel(jp_rp);
		jp_rp.setPanel(jp_fp);
		
		add(jp_fp, jp_fp.gsC_migl);
		add(jp_rp, jp_rp.gsC_migl);
		add(jp_pp,jp_pp.gsC_migl);
		
		//setLocation(411, 73);
		setMinimumSize(new Dimension(36 + R.Find.iWidthMin, 30 + 338-73-9));
		setVisible(true);
	}

}
