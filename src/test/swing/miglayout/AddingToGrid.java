package test.swing.miglayout;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import qgb.T;

public class AddingToGrid extends JPanel {
	public static void main(String[] args) {
		T.Thread(1);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					final AddingToGrid jp_f = new AddingToGrid();
					WindowListener l = new WindowAdapter() {
						public void windowClosing(WindowEvent e) {
							System.exit(0);
						}

						public void windowDeiconified(WindowEvent e) {
						}

						public void windowIconified(WindowEvent e) {
						}
					};
					JFrame f = new JFrame(AddingToGrid.class.getName());
					f.addWindowListener(l);
					f.getContentPane().add("Center", jp_f);
					f.pack();
					Dimension screenSize = Toolkit.getDefaultToolkit()
							.getScreenSize();
					int w = 720;
					int h = 510;
					// w=h=99;
					f.setLocation(screenSize.width / 2 - w / 2,
							screenSize.height / 2 - h / 2);
					T.print("(%d,%d)", screenSize.width / 2 - w / 2,
							screenSize.height / 2 - h / 2);
					//f.setSize(w, h);
					//f.setResizable(false);
					f.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the panel.
	 */
	public AddingToGrid() {
		//super();//"", "", ""
		setLayout(new MigLayout());
		CreateComponents();
		
		
		for (int i = 0; i < gyjb.length; i++) {
//			if (i==2) {
//				add(gyjb[i],"wrap");
//				continue;
//			}else if (i==0) {
//				add(gyjb[i],"h 50:70:90");
//				continue;
//			}
			add(gyjb[i],"h 10:70:190,w 10:70:190");
			//gyjb[i].setVisible(true);
		}
		
		gyjb[1].setVisible(false);
	}
	JButton[] gyjb=new JButton[9];
	private void CreateComponents() {
		for (int i = 0; i < gyjb.length; i++) {
			gyjb[i]=new JButton("JB "+(i+1));
			
		}
	}

}
