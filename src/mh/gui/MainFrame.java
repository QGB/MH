package mh.gui;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;

import qgb.T;
import qgb.swing.QST;
import mh.gui.results.ResultsPanel;
import mh.gui.results.TransPanel;
import net.miginfocom.swing.MigLayout;

public class MainFrame extends JFrame {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**********Test End*********/
	public MainFrame() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new MigLayout(R.MigL.sL_noBorder, "[grow]", "[]0[grow]0[]"));

		final ProgressPanel jp_pp=new ProgressPanel(50);
		final ResultsPanel jp_rp=new ResultsPanel(jp_pp);
		final FindPanel jp_fp = new FindPanel(jp_rp);
		jp_rp.setPanel(jp_fp);
		
		getContentPane().add(jp_fp, jp_fp.gsC_migl);
		getContentPane().add(jp_rp, jp_rp.gsC_migl);
		getContentPane().add(jp_pp,jp_pp.gsC_migl);
		pack();
		
		setLocation(411, 73);
		setMinimumSize(new Dimension(36 + R.Find.iWidthMin, 30 + 338-73-9));
		setVisible(true);
	}

}
