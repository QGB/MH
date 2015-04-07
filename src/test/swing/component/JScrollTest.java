package test.swing.component;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JViewport;

import net.miginfocom.swing.MigLayout;
import qgb.T;
import qgb.swing.QST;
import mh.gui.R;
import mh.gui.R.Btn;
import mh.gui.results.senten.SentenPanel;
import mh.struct.entry.Senten;

public class JScrollTest extends JPanel {

	public JScrollTest() {
		super(new GridLayout(1, 1));

        JPanel jp = new JPanel();
        
        jp.setBackground(Color.blue);
        jp.setLayout(new MigLayout(R.MigL.sL_noBorder, "[grow]"));
        int imax=22;
        for (int i = 0; i < imax; i++) {
        	Senten s=new Senten("AAA"+i, "BBBBBBB");
        	jp.add(new SentenPanel(s,i),R.MigL.sCH2min+",growx,wrap");
		}
        jp.setPreferredSize(new Dimension(64, imax*(64+4)));
        
		JScrollPane jsp=new JScrollPane(jp,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		add(jsp);
	}
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame f = new JFrame(JScrollTest.class.getName());
					f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					f.setLayout(new MigLayout(R.MigL.sL_noBorder, "[grow]", "[grow]0[]"));
					
					final JScrollTest jp_f = new JScrollTest();
					//QST.setAllBackground(jp_f, Color.BLACK);
					//QST.setAllForeground(jp_f, Color.GREEN);
					f.getContentPane().add(jp_f,"growx,growy,wrap");
					/************Phon panel test****************/
					JButton btnTest=new JButton();
					ActionListener actionVoice=new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
						
						}
					};
					btnTest.addActionListener(actionVoice);
					f.getContentPane().add(btnTest,R.MigL.sCmin);
					/*********************************/
					f.pack();
					//R.Frame.set(f);
					f.setLocation(411-300, 73-16);
					T.print(f.getSize());
					f.setMinimumSize(new Dimension(99,100));
					// f.setMaximumSize(new Dimension(36+32+32+400+32,
					// 30+7+32+7+9));
					f.setSize(new Dimension(100, 300));
					
					f.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
