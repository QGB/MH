package mh.gui.results.phon;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import mh.gui.R;
import mh.gui.results.ResultsPanel;
import mh.struct.Voice;
import mh.struct.entry.C;
import mh.struct.entry.Phon;
import mh.struct.entry.Word;
import net.miginfocom.swing.MigLayout;
import qgb.T;
import qgb.interfaces.GetSet;
import qgb.swing.QST;
/**
 * String colConstraints="[][grow][]"**/
public class PhonPanel extends JPanel implements GetSet<Phon> {
	JButton btnVoice;
	JTextField txtPhon;
	public String gsC_migl="";

	public Phon gp;
	public C gc;

	/**在此类中 用不到Voice av.stM ,
	 * 但为了使调用者更易用，不采用 InputStream
	 * </p>不要求 在 EDT中调用**/
	public void addVoice(Voice av) {
		gp.v.set(av);
		btnVoice.setVisible(gp.v.notNull());
	}
	
	public PhonPanel(final Phon ap, final C ac) {
		super(new MigLayout(R.MigL.sL_noBorder, "[grow]0[]", R.MigL.sLmin));
		this.gp = ap;
		this.gc = ac;
		
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				CreateComponents();
				AddListener();
				switch (ac) {
				case P:
					btnVoice.setIcon(R.Phon.iconP);
					break;
				case UK:
					btnVoice.setIcon(R.Phon.iconUK);
					break;
				case US:
					btnVoice.setIcon(R.Phon.iconUS);
					break;
				default:
					break;
				}
				
				add(txtPhon,R.MigL.sCHmin+",growx");
				
				add(btnVoice,R.MigL.sCmin);
				
				//txtPhon.setText(gp.stP.get());
				addVoice(ap.v);
				PhonPanel.this.setVisible(true);
				QST.refreshAll(PhonPanel.this);
			}
		});

		gsC_migl="growx";
	
	}

	private void AddListener() {
		ActionListener actionVoice=new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gp.v.play();
			}
		};

		btnVoice.addActionListener(actionVoice);
//		btnFind.addActionListener(actionFind);

	}

	private void CreateComponents() {
		txtPhon =new JTextField(gp.stP.get());
		txtPhon.setEditable(false);
		//btnBack.setEnabled(false);

		btnVoice = new JButton();
		btnVoice.setVisible(false);
	}


	public static void main(String[] args) {
		// T.Thread(1);
		final Phon puk=new Phon("UK");
		final Voice voice=new Voice("m1", T.read_bis("v/o.mp3"));
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame f = new JFrame(PhonPanel.class.getName());
					f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					f.setLayout(new MigLayout(R.MigL.sL_noBorder, "[grow]", "[]"));
					
					final PhonPanel jp_f = new PhonPanel(puk,C.UK);
					QST.setAllBackground(jp_f, Color.BLACK);
					QST.setAllForeground(jp_f, Color.GREEN);
					f.getContentPane().add(jp_f,jp_f.gsC_migl);
					/************Phon panel test****************/
					JButton btnTest=new JButton();
					ActionListener actionVoice=new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							jp_f.addVoice(voice);
						}
					};
					btnTest.addActionListener(actionVoice);
					f.getContentPane().add(btnTest,"wrap");
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
	public Phon get() {
		return gp;
	}

	@Override
	public void set(final Phon ap) {
		this.gp = ap;	
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				txtPhon.setText(gp.stP.get());
				addVoice(ap.v);
				PhonPanel.this.setVisible(true);
				QST.refreshAll(PhonPanel.this);
			}
		});
	

	}

}
