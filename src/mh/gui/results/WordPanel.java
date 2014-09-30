package mh.gui.results;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import mh.gui.R;
import mh.gui.results.ResultsPanel;
import mh.gui.results.phon.PhonPanel;
import mh.gui.results.phon.PhonPanels;
import mh.struct.Voice;
import mh.struct.entry.C;
import mh.struct.entry.Phon;
import mh.struct.entry.Word;
import net.miginfocom.swing.MigLayout;
import qgb.T;
import qgb.swing.QST;
/**
 * String colConstraints="[][grow][]"**/
public class WordPanel extends JPanel {
	JTextField txtWord;
	PhonPanels gps=new PhonPanels();
	private JPanel gpsPanel;
	/**dock north 始终将此Panel至于顶上方，并独占**/
	public String gsC_migl="growx,wrap";
	
	/**不要求 在 EDT中调用。
	 * 自动刷新。**/
	@Override
	public void removeAll() {
		Voice.stopPlay();
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				txtWord.setText("");
				gpsPanel.removeAll();
				for(Entry<C, PhonPanel> e:gps.entrySet()){
					e.getValue().setVisible(false);
				}
				QST.refreshAll(WordPanel.this);
			}
		});
	}
	/**不要求 在 EDT中调用**/
	public void showWord(final Word aw){
		removeAll();
		for(Entry<C, Phon> e:aw.ps.entrySet()){
			addPhon(e.getValue(), e.getKey());
		}
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				txtWord.setText(aw.stW.get());
				QST.refreshAll(WordPanel.this);
			}
		});
	}
	/**不要求 在 EDT中调用**/
	public void addPhon(Phon ap, final C ac) {
		PhonPanel pp=null;
		if (gps.containsKey(ac)) {
			pp=gps.get(ac);
			pp.set(ap);
			//T.print(pp==gps.get(ac));//true
//			gpsPanel.add(pp);
//			return;
		}else {
			pp=new PhonPanel(ap, ac);
			gps.put(ac, pp);
		}
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				//gps.get(ac).setVisible(true);
				gpsPanel.add(gps.get(ac));
				QST.refreshAll(WordPanel.this);				
			}
		});
	}
	/**要在addPhon()之后调用,否则无效果
	 * </p>不要求 在 EDT中调用**/
	public void addVoice(Voice av, C ac) {
		if (gps.containsKey(ac)==false) {
			return;
		}
		gps.get(ac).addVoice(av);
	}
	
	public WordPanel() {
		super(new MigLayout(R.MigL.sL_noBorder, "[grow]","[]0[]"));
		
		CreateComponents();
		AddListener();
		
		add(txtWord, R.MigL.sC_inputLine+",growx,wrap");
		add(gpsPanel,R.MigL.sC_inputLine+",growx");

	}

	private void AddListener() {
//		ActionListener actionFind = new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//
//			}
//		};

		//btnFind.addActionListener(actionFind);
	}

	private void CreateComponents() {
		txtWord = new JTextField();
		txtWord.setFont(new Font(Font.DIALOG, Font.PLAIN, 30));
		gpsPanel=new JPanel(new GridLayout(1, 3));

	}
	/************Test Code**********************/
	public static void main(String[] args) {
		EventQueue.invokeLater(grun);
	}
	private static Word getTestWord() {
		Phon puk=new Phon("UK");
		puk.v.set("m1", T.read_bis("v/o.mp3"));
		
		Phon pus=new Phon("US");
		pus.v.set("m2", T.read_bis("v/g.mp3"));
		
		Phon ph=new Phon("haha");
		
		Word word=new Word("book");
		word.ps.put(C.UK, puk);
		word.ps.put(C.US, pus);
		word.ps.put(C.P, ph);
		return word;
	}
	private static Runnable grun=new Runnable() {
		public void run() {
			try {
				final JFrame f = new JFrame(WordPanel.class.getName());
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				f.setLayout(new MigLayout(R.MigL.sL_noBorder, "[grow]", "[]0[]"));
				//f.setLayout(new GridLayout(4, 1));
				final WordPanel jp_f = new WordPanel();
				QST.setAllBackground(jp_f, Color.BLACK);
				QST.setAllForeground(jp_f, Color.GREEN);
				f.getContentPane().add(jp_f,jp_f.gsC_migl);
				/*****Test**********/
				final Phon puk=new Phon("UK");
				final Voice voice=new Voice("m1", T.read_bis("v/1.mp3"));
				
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
						//T.print(puk.v.isNotNull());
						T.print(EventQueue.isDispatchThread());
						//T.print(jp_f.gps);
						//T.print(jp_f.gpsPanel.getComponents());
						
						
						jp_f.addVoice(voice,C.UK);
						jp_f.addVoice(voice,C.P);
						//QST.refreshAll(f);							
					}
				};
				btnV.addActionListener(actionVoice);
				f.getContentPane().add(btnV,R.MigL.sCHmin);
				////////////////////////////////////////////////
				JButton btnT=new JButton("T");
				ActionListener actionT=new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						jp_f.showWord(getTestWord());
//						jp_f.addPhon(puk, C.UK);
//						jp_f.addPhon(puk, C.P);
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
