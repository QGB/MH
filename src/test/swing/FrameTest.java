package test.swing;

import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.alee.laf.WebLookAndFeel;

import qgb.T;

import java.awt.GridLayout;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class FrameTest extends JFrame {
	KeyListener keyEnter=new KeyListener() {
		@Override
		public void keyTyped(KeyEvent e) {}
		@Override
		public void keyReleased(KeyEvent e) {
			if (e.getKeyCode()==KeyEvent.VK_ENTER) {
				T.print("Enter Typed!");
			}
		}
		@Override
		public void keyPressed(KeyEvent e) {}
	};
	// 注意在1.4及其以前版本中可以编译,但是运行有问题
	// 5.0版本运行正常
	FrameTest() {
		super("Test");
		setBackground(Color.BLACK);
		int ix=2,iy=ix;
		setLayout(new GridLayout(ix,iy, 0,0)); // 1.4中问题 1
		for (int i = 0; i <ix-1; i++) {
			for (int j = 0; j < iy; j++) {
				JPanel jPanel = new JPanel();
				jPanel.setBackground(new Color((int) 
						Math.abs(
								Math.sin(0.0000013*i*j)*(1677721)
								)
						)
				);
				
				if (i==0&&j==0) {
					jPanel.setLayout(new GridLayout(1,1, 0,0));
					JTextArea txt=new JTextArea();
					jPanel.add(txt);
					txt.addKeyListener(keyEnter);
				}
				
				add(jPanel); // 1.4中问题 2				
			}
		}
		JTextArea txt=new JTextArea();
		add(txt);
		setSize(400, 400);
		setLocation(400, 200);
		setVisible(true);
		//addKeyListener(keyEnter);
		
	}

	public static void main(String[] args) {
		//WebLookAndFeel.install();
		//Action
		new FrameTest();
	}
}