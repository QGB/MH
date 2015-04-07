package qgb.algorithm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import qgb.T;
import qgb.swing.QST;

public class EvoDisFrame extends JFrame {
	static EvoDisFrame EDframe;

	public static void main(String[] args) {
		EvolutionaryDistances.main(null);
		if (true)
			return;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EDframe = new EvoDisFrame(9, 2);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		// for (int i = 0; i < 9; i++) {
		// T.sleep(1000);
		// EDframe.setCoordinate(i,0, i*i-5*i);
		// }
		T.sleep(1000);
		// EDframe.test();
	}

	JButton[][] gLabels;

	public EvoDisFrame(int aix, int aiy) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new GridLayout(aiy, aix));
		gLabels = new JButton[aix][aiy];
		for (int j = 0; j < gLabels[0].length; j++) {
			for (int i = 0; i < gLabels.length; i++) {
				// T.print("i=%d,j=%d", i, j);
				// T.sleep(500);
				gLabels[i][j] = new JButton();
				// gLabels[i][j].setFont();
				gLabels[i][j].setForeground(Color.GREEN);
				gLabels[i][j].setBackground(Color.BLACK);
				gLabels[i][j].setVisible(true);
				getContentPane().add(gLabels[i][j]);
				// gLabels[i][j].setText(i+","+j);
				// gLabels[i][j].setText(""+(++in));
			}
		}
		setLocation(0, 0);
		setSize(aix * 50, aiy * 50 + 50);
		setVisible(true);
	}

	public void setCoordinate(final int ix, final int iy, final int iValue) {
		String stNew="" + iValue,stOld=gLabels[ix][iy].getText();
		if (stOld!=""&&!stNew.equals(stOld)) {
			T.msgbox("%d,%d|%s[%s]",ix,iy,stOld,stNew);
		}
		gLabels[ix][iy].setText(stNew);

		// EventQueue.invokeLater(new Runnable() {
		// public void run() {
		// try {
		// gLabels[ix][iy].setText("" + iValue);
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// }
		// });
		// T.sleep(1);
	}

	public void test() {
		if (true)
			return;
		int in = 0;
		// for (int i = 0; i < gLabels.length; i++) {
		// for (int j = 0; j < gLabels[i].length; j++) {
		// T.print("i=%d,j=%d",i,j);
		// T.sleep(500);
		//
		// gLabels[i][j].setText(""+(++in));
		//
		// }
		// }
		for (int i = 0; i < gLabels.length; i++) {
			for (int j = 0; j < gLabels[i].length; j++) {
				T.print("i=%d,j=%d", i, j);
				T.sleep(500);
				gLabels[i][j] = new JButton();
				// gLabels[i][j].setFont();
				gLabels[i][j].setForeground(Color.RED);
				gLabels[i][j].setBackground(Color.BLUE);
				gLabels[i][j].setVisible(true);
				getContentPane().add(gLabels[i][j]);
				gLabels[i][j].setText(i + "," + j);
				// gLabels[i][j].setText(""+(++in));
			}
		}

	}

	public static void main(final int i, final int j) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EDframe = new EvoDisFrame(i, j);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		// for (int i = 0; i < 9; i++) {
		// T.sleep(1000);
		// EDframe.setCoordinate(i,0, i*i-5*i);
		// }
		T.sleep(1000);
	}

	public static void show(int[][] yyi) {
		for (int i = 0; i < yyi.length; i++) {
			for (int j = 0; j < yyi[i].length; j++) {
				EDframe.gLabels[i][j].setText("" + yyi[i][j]);
			}
		}
	}
}
