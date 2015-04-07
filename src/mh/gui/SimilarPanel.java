package mh.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

import qgb.T;

public class SimilarPanel extends JPanel {
	public static void main(String[] args) {
//		MainLoader.main(null);
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

				f.getContentPane().add(new SimilarPanel());
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
	String label[] = { "Zero", "One", "Two", "Three", "Four", "Five", "Six",
			"Seven", "Eight", "Nine", "Ten", "Eleven" };
	JList list;

	public SimilarPanel() {
		this.setLayout(new BorderLayout());
		list = new JList(label);
		JScrollPane pane = new JScrollPane(list);
		JButton button = new JButton("Print");
		button.addActionListener(new PrintListener());

		add(pane, BorderLayout.CENTER);
		add(button, BorderLayout.SOUTH);
	}

	// An inner class to respond to clicks on the Print button
	class PrintListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int selected[] = list.getSelectedIndices();
			System.out.println("Selected Elements:  ");

			for (int i = 0; i < selected.length; i++) {
				String element = (String) list.getModel().getElementAt(
						selected[i]);
				System.out.println("  " + element);
			}
		}
	}

}
