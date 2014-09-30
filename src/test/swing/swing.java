package test.swing;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import qgb.T;

import com.gargoylesoftware.htmlunit.javascript.host.MouseEvent;

public class swing {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					swing window = new swing();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public swing() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnNewButton = new JButton(" button");
		Component locate = btnNewButton.locate(20, 20);
		btnNewButton.setSize(80, 20);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.print("swing");
			}
		});
		frame.getContentPane().add(btnNewButton, BorderLayout.CENTER);
		btnNewButton.addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseDragged(java.awt.event.MouseEvent e) {
				Point mousePoint = MouseInfo.getPointerInfo().getLocation();
				T.print(mousePoint);
			}

			@Override
			public void mouseMoved(java.awt.event.MouseEvent e) {

			}
		});
	}

}
