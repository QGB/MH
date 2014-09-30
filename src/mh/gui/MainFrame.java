package mh.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new MigLayout(R.MigL.sL_noBorder, "[grow]", "[]0[grow]0[]"));

		final ProgressPanel jp_pp=new ProgressPanel(40);
		final ResultsPanel jp_rp=new ResultsPanel(jp_pp);
		final FindPanel jp_fp = new FindPanel(jp_rp);
		jp_rp.setPanel(jp_fp);
		
		getContentPane().add(jp_fp, jp_fp.gsC_migl);
		getContentPane().add(jp_rp, jp_rp.gsC_migl);
		getContentPane().add(jp_pp,jp_pp.gsC_migl);
		//jp_rp.gTP.gtxtArea.addKeyListener(jp_fp.keyEnter);
		//addKeyListener(jp_fp.keyEnter);
		//jp_fp.addKeyListener(l);
		
//		QST.setAllBackground(jp_rp.gSsP, Color.BLUE);
//		QST.setAllBackground(jp_rp.gTP,Color.BLACK);
//		QST.setAllForeground(jp_rp.gTP, Color.GREEN);
//		jp_rp.setBackground(Color.PINK);
//		QST.setAllBackground(jp_pp, Color.GREEN);
//
//		getContentPane().setBackground(Color.RED.darker());
//		JButton jb = new JButton(QST.getScaledIcon("res/find/back.png", 89, 89));
//		getContentPane().add(jb, "h 99:99:99,w 533::");
		
		pack();
		
		addListener();
		
		setLocation(411, 73);
		setMinimumSize(new Dimension(36 + R.Find.iWidthMin, 30 + 338-73-9));
//		 setMaximumSize(new Dimension(36+R.Find.iWidthMax+22,
//		 30+7+32+7+9));
		setVisible(true);

		//QST.setAllBackground(this, Color.BLACK);
		//QST.setAllForeground(this, Color.GREEN);
	}

	private void addListener() {
//		this.addMouseMotionListener(new MouseMotionListener() {
//			
//			@Override
//			public void mouseMoved(MouseEvent e) {
//				Point p = getLocation();
//				T.print(p);
//			}
//			
//			@Override
//			public void mouseDragged(MouseEvent e) {
//				
//			}
//		});

//		if(getWidth()>533){
//			setSize(500, getHeight());
//		}
	
	
	}
}
