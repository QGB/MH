package test.swing;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JProgressBar;

import qgb.T;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ThreadTesst extends JFrame {

	private JPanel contentPane;
	JProgressBar progressBar = new JProgressBar();
	int gi_v=0;
	boolean gb_isInterrupted=false;
	
	Runnable ra=new Runnable() {	
		@Override
		public void run() {
			gb_isInterrupted=false;
			T.print("ra:"+T.Thread());
			for (int i = 0; i < 100; i++) {
				T.sleep(999);
				if (gb_isInterrupted) {
					return;
				}
				gi_v=gi_v+1;
				T.print(gi_v);
				EventQueue.invokeLater(rGui);
			}
			T.print("ra end!");
		}
	};

	Thread ta=new Thread(ra);
	
	Runnable rGui=new Runnable() {
		@Override
		public void run() {
			progressBar.setValue(gi_v);
		}
	};
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		T.print(T.Thread());
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ThreadTesst frame = new ThreadTesst();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		T.print(T.Thread()+" end!!!");
	}

	/**
	 * Create the frame.
	 */
	public ThreadTesst() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnStop = new JButton("STOP");
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gb_isInterrupted=true;
				ta.interrupt();
				
				//progressBar.setValue(22);
				//T.print(progressBar.getMaximum());
			}
		});
		btnStop.setBounds(322, 29, 95, 25);
		contentPane.add(btnStop);
		
		progressBar.setBounds(28, 92, 393, 16);
		contentPane.add(progressBar);
		
		JButton btnStart = new JButton("START");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//T.print(T.Thread());
				ta=new Thread(ra);
				ta.start();
				//EventQueue.invokeLater(ra);
				//ra.run();
			}
		});
		btnStart.setBounds(44, 29, 95, 25);
		contentPane.add(btnStart);
	}
}
