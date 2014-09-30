package mh.gui.results.senten;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import mh.gui.R;
import mh.struct.Voice;
import mh.struct.entry.Senten;
import net.miginfocom.swing.MigLayout;
import qgb.T;
import qgb.interfaces.GetSet;
import qgb.swing.QST;
/**
 * String colConstraints="[][grow][]"**/
public class SentenPanel extends JPanel implements GetSet<Senten> {
	JButton btnVoice;
	JTextField txtEn;
	JTextField txtCn;
	public String gsC_migl="";

	public Senten gs;
	public int gi;

	private JPanel gDsPanel;
	/**在此类中 用不到Voice av.stM ,
	 * 但为了使调用者更易用，不采用 InputStream
	 * </p>不要求 在 EDT中调用**/
	public void addVoice(Voice av) {
		gs.v.set(av);
		btnVoice.setVisible(gs.v.notNull());
//		T.print(btnVoice);
//		T.print(gs.v);
//		T.msgbox();
	}
	
	public SentenPanel(final Senten as, final int ai) {
		super(new MigLayout(R.MigL.sL_noBorder, "[grow]0[]", R.MigL.sL2min));
		this.gs = as;
		this.gi = ai;
		
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				CreateComponents();
				AddListener();

				add(gDsPanel,R.MigL.sCH2min+",growx");
				add(btnVoice,R.MigL.sCH2min+","+R.MigL.sCWmin);
				
				addVoice(gs.v);

				SentenPanel.this.setVisible(true);
				QST.refreshAll(SentenPanel.this);
			}
		});

		gsC_migl="growx";
	
	}

	private void AddListener() {
		ActionListener actionVoice=new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gs.v.play();
			}
		};
		btnVoice.addActionListener(actionVoice);
	}

	private void CreateComponents() {
		txtEn =new JTextField(gs.ds.st1.get());
		txtCn =new JTextField(gs.ds.st2.get());
		gDsPanel=new JPanel(new GridLayout(2, 1));
		gDsPanel.add(txtEn);
		gDsPanel.add(txtCn);
		
		btnVoice = new JButton();
		/**2014-09-15 13:23:12**/
		ImageIcon icon =QST.getScaledIcon(R.Senten.stP+((gi+1)%10)+".png",R.Icon.iMin-2,R.Icon.iMin*2);
		btnVoice.setIcon(icon);
		btnVoice.setVisible(false);
	}


	public static void main(String[] args) {
		//T.print(T.getCurrentMethod());
		final Senten sen=new Senten("111", "aaa");
		final Voice voice=new Voice("m1", T.read_bis("v/o.mp3"));
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame f = new JFrame(T.getCurrentClass().getName());
					f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					f.setLayout(new MigLayout(R.MigL.sL_noBorder, "[grow]", "[]0[]"));
					
					final SentenPanel jp_f = new SentenPanel(sen,0);
					QST.setAllBackground(jp_f, Color.BLACK);
					QST.setAllForeground(jp_f, Color.GREEN);
					f.getContentPane().add(jp_f,jp_f.gsC_migl+",wrap");
					/************Phon panel test****************/
					JButton btnTest=new JButton();
					ActionListener actionVoice=new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							jp_f.addVoice(voice);
						}
					};
					btnTest.addActionListener(actionVoice);
					f.getContentPane().add(btnTest,R.MigL.sCmin);
					/*********************************/
					f.pack();
					//R.Frame.set(f);
					f.setLocation(411-300, 73-16);
					T.print(f.getSize());
					f.setMinimumSize(new Dimension(R.Frame.iwMin +R.Phon.iWidthMin,
							R.Frame.ihMin +R.Phon.iHeightMin));
					// f.setMaximumSize(new Dimension(36+32+32+400+32,
					// 30+7+32+7+9));
					//f.setSize(f.getMinimumSize());
					
					f.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public Senten get() {
		return gs;
	}

	@Override
	public void set(final Senten as) {
		this.gs = as;	
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				txtEn.setText(gs.ds.st1.get());
				txtCn.setText(gs.ds.st2.get());
				addVoice(gs.v);

				SentenPanel.this.setVisible(true);
				QST.refreshAll(SentenPanel.this);
			}
		});
	

	}

}
