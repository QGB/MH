package mh.gui.test;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;

import javax.swing.JButton;

import mh.struct.Voice;
import mh.struct.entry.C;
import mh.struct.entry.Phon;
import mh.struct.entry.Word;
import qgb.T;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VoiceT extends JFrame {

	private JPanel contentPane;

	static Phon puk=new Phon();
	static Phon pus=new Phon();
	static Word word=new Word();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VoiceT frame = new VoiceT();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		puk.stP.set("UK");
		puk.v.set("m1", T.read_bis("v/o.mp3"));
		
		pus.stP.set("US");
		pus.v.set("m2", T.read_bis("v/g.mp3"));
		
		Phon p=new Phon();
		p.stP.set("P");
		//T.print(p.v.isNotNull());
		//p.v.set("m2", T.read_bis("v/1.mp3"));
		
		word.ps.put(C.UK, puk);
		word.ps.put(C.US, pus);
		//word.ps.put(C.P, p);
	
	}

	/**
	 * Create the frame.
	 */
	public VoiceT() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnV = new JButton("V 0");
		btnV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				word.ps.get(C.UK).v.play();
			}
		});
		btnV.setBounds(10, 10, 95, 25);
		contentPane.add(btnV);
		
		JButton btnV_1 = new JButton("V 1");
		btnV_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				word.ps.get(C.US).v.play();
			}
		});
		btnV_1.setBounds(117, 10, 95, 25);
		contentPane.add(btnV_1);
		
		JButton btnV_2 = new JButton("V 2");
		btnV_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				word.ps.get(C.P).v.play();
			}
		});
		btnV_2.setBounds(234, 10, 95, 25);
		contentPane.add(btnV_2);
		
		JButton btnStop = new JButton("Stop");
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Voice.stopPlay();
			}
		});
		btnStop.setBounds(10, 45, 95, 25);
		contentPane.add(btnStop);
	}
}
