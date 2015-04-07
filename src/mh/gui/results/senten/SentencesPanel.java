package mh.gui.results.senten;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import net.miginfocom.swing.MigLayout;
import qgb.T;
import qgb.interfaces.StopTask;
import qgb.swing.QST;
import mh.MainLoader;
import mh.gui.MainFrame;
import mh.gui.R;
import mh.struct.Sentences;
import mh.struct.Voice;
import mh.struct.entry.Senten;

public class SentencesPanel extends JScrollPane implements StopTask {
	public static void main(String[] args) {
		MainLoader.main(null);
		// EventQueue.invokeLater(grun);
	}

	private static Runnable grun = new Runnable() {
		public void run() {
			try {
				final JFrame f = new JFrame(SentencesPanel.class.getName());
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				f.setLayout(new MigLayout(R.MigL.sL_noBorder, "[grow]", "[]0[]"));
				// f.setLayout(new GridLayout(4, 1));
				final SentencesPanel jp_f = new SentencesPanel();
				QST.setAllBackground(jp_f, Color.BLACK);
				QST.setAllForeground(jp_f, Color.GREEN);
				f.getContentPane().add(jp_f, jp_f.gsC_migl);
				/***** Test **********/
				final Voice voice = new Voice("m1", T.read_bis("v/g.mp3"));

				JButton btnP = new JButton("P");

				ActionListener actionP = new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						jp_f.removeAll();
						// QST.setAllBackground(jp_f, Color.RED);
					}
				};
				btnP.addActionListener(actionP);
				f.getContentPane().add(btnP, R.MigL.sCHmin + ",split 3");
				// ////////////////////////////////////////////
				JButton btnV = new JButton("V");
				ActionListener actionVoice = new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						T.print(jp_f.gSPs.size());
						for (int i = 0; i < jp_f.gSPs.size(); i++) {
							jp_f.addVoice(voice, i);
						}
						// jp_f.addSenten(new Senten("vvvAAA", "BBBBBBB"));
					}
				};
				btnV.addActionListener(actionVoice);
				f.getContentPane().add(btnV, R.MigL.sCHmin);
				// //////////////////////////////////////////////
				JButton btnT = new JButton("T");
				ActionListener actionT = new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						for (int i = 0; i < 22; i++) {
							Senten s = new Senten("AAA" + i, "BBBBBBB");
							jp_f.addSenten(s);
						}

					}
				};
				btnT.addActionListener(actionT);
				f.getContentPane().add(btnT, R.MigL.sCHmin);
				/***** Test End ******/
				f.pack();
				f.setLocation(411 - 300, 73 - 16);
				f.setMinimumSize(new Dimension(
						R.Frame.iwMin + R.Find.iWidthMin, R.Frame.ihMin
								+ R.Find.iHeightMin));
				f.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	};
	/************ Test End **********************/
	SentenPanels gSPs = new SentenPanels();
	JPanel gjp = new JPanel();
	public static String gsC_migl = "h 65::,growx,growy";
	private volatile int giS = 0, giV = 0;

	// 请谨慎使用 gbXXX 的值
	protected volatile boolean gbNotStop = true, gbIsDone = false;

	@Override
	public void stop() {
		gbNotStop = false;
	}

	@Override
	public boolean isStop() {
		return !gbNotStop;
	}

	@Override
	public boolean isDone() {
		return gbIsDone;
	}

	public SentencesPanel() {
		super(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		CreateComponents();
		AddListener();
	}

	private void AddListener() {

	}

	private void CreateComponents() {
		 gjp.setBackground(Color.GRAY);
		gjp.setLayout(new MigLayout(R.MigL.sL_noBorder, "[grow]"));
		setViewportView(gjp);
	}

	/**
	 * 不要求 在 EDT中调用。 自动刷新。
	 **/
	@Override
	public void removeAll() {
		Voice.stopPlay();
		gSPs.clear();
		gjp.removeAll();
		gjp.setPreferredSize(new Dimension(0, 0));
		QST.refreshAll(SentencesPanel.this);
		giS = 0;
		giV = 0;
		gbIsDone = false;
	}

	/** 不要求 在 EDT中调用 **/
	public void showSentences(final Sentences ass) {
		removeAll();
		gjp.setPreferredSize(new Dimension(64, ass.size() * (64 + 4)));
		for (int i = 0; i < ass.size(); i++) {
			gSPs.add(ass.get(i));
			gjp.add(gSPs.get(i), R.MigL.sCH2min + ",growx,wrap");
			giS++;
		}
		//QST.refreshAll(SentencesPanel.this);
		
		gbIsDone=true;
	}

	/** 不要求 在 EDT中调用 **/
	public int addSenten(final Senten as) {
		final int i = gSPs.add(as);
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				gjp.add(gSPs.get(i), R.MigL.sCH2min + ",growx,wrap");
				gjp.setPreferredSize(new Dimension(64, gSPs.size() * (64 + 4)));
				//QST.refreshAll(SentencesPanel.this);
			}
		});
		giS++;
		if (as.v.notNull()) {
			addVoice(as.v, i);
		}
		return i;
	}

	/**
	 * 要在addPhon()之后调用,否则无效果 </p>不要求 在 EDT中调用
	 **/
	public void addVoice(Voice av, int ai) {
		if (ai < 0 && ai > gSPs.size()) {
			T.argsError(av, ai);
		}
		// T.print(ai+",size:"+gSPs.size());
		//T.sleep(250);
		gSPs.get(ai).addVoice(av);
		if ((++giV) == giS) {
			gbIsDone = true;
		}
	}

}
