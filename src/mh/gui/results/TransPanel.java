package mh.gui.results;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import net.miginfocom.swing.MigLayout;
import qgb.T;
import qgb.swing.QST;
import test.swing.component.JTextArea2;
import mh.gui.MainFrame;
import mh.gui.R;
import mh.struct.Voice;
import mh.struct.entry.Translations;
public class TransPanel extends JScrollPane {
	//Translations gtrans;
	public JTextArea gtxtArea = new JTextArea(5, 25);
	public static String gsC_migl="h 65::,growx,growy,wrap";
	public TransPanel() {
		super(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		gtxtArea.setFont(new Font("标楷体", Font.BOLD, 22));
		gtxtArea.setLineWrap(true);// 激活自动换行功能
		gtxtArea.setWrapStyleWord(true);// 激活断行不断字功能
		//gtxtArea.setEditable(false);
		//gtxtArea.setText(T.getSource(JTextArea2.class));
		setViewportView(gtxtArea);
	}
	/**不要求 在 EDT中调用。
	 * 自动刷新。**/
	@Override
	public void removeAll() {
		gtxtArea.setEditable(true);
		gtxtArea.setText("");

		QST.refreshAll(this);
	}
	/**不要求 在 EDT中调用**/
	public void showTrans(final Translations ats){
		//gtxtArea.setEditable(true);
		gtxtArea.setText(ats.toString());
		//TODO: 不能完美显示滚动条
		
//		T.print(gtxtArea.getPreferredSize());[width=475,height=155]
//		gtxtArea.setPreferredSize(new Dimension(1, 1));
		//gtxtArea.setEditable(false);
		QST.refreshAll(this);
	}
	/************Test Code**********************/
	public static void main(String[] args) {
		MainFrame.main(null);
		if (true) {
			return;
		}
		EventQueue.invokeLater(grun);
	}

	private static Runnable grun=new Runnable() {
		public void run() {
			try {
				final JFrame f = new JFrame(TransPanel.class.getName());
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				f.setLayout(new MigLayout(R.MigL.sL_noBorder, "[grow]", "[grow]0[]"));
				//f.setLayout(new GridLayout(4, 1));
				final TransPanel jp_f = new TransPanel();
				QST.setAllBackground(jp_f, Color.BLACK);
				QST.setAllForeground(jp_f, Color.GREEN);
				f.getContentPane().add(jp_f,jp_f.gsC_migl);
				/*****Test**********/
				final Voice voice=new Voice("m1", T.read_bis("v/g.mp3"));
				
				JButton btnP=new JButton("P");
				
				ActionListener actionP=new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						jp_f.removeAll();
						//QST.setAllBackground(jp_f, Color.RED);
					}
				};
				btnP.addActionListener(actionP);
				f.getContentPane().add(btnP,R.MigL.sCHmin+",split 3");
				//////////////////////////////////////////////
				JButton btnV=new JButton("V");
				ActionListener actionVoice=new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {

						//jp_f.addSenten(new Senten("vvvAAA", "BBBBBBB"));
					}
				};
				btnV.addActionListener(actionVoice);
				f.getContentPane().add(btnV,R.MigL.sCHmin);
				////////////////////////////////////////////////
				JButton btnT=new JButton("T");
				ActionListener actionT=new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						Translations t=new Translations(
								T.getSource(TransPanel.class));
						jp_f.showTrans(t);
					}
				};
				btnT.addActionListener(actionT);
				f.getContentPane().add(btnT,R.MigL.sCHmin);
				/*****Test End******/
				f.pack();
				f.setLocation(411-300, 73-16);
				f.setMinimumSize(new Dimension(R.Frame.iwMin +R.Find.iWidthMin,
						R.Frame.ihMin +R.Find.iHeightMin));
				f.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	};
}
