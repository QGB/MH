package mh.gui.results;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import mh.MainLoader;
import mh.database.MDT;
import mh.gui.FindPanel;
import mh.gui.ProgressPanel;
import mh.gui.R;
import mh.gui.results.senten.SentencesPanel;
import mh.net.kingsoft.KingSoft;
import mh.struct.StrNotNull;
import mh.struct.entry.Entry;
import net.miginfocom.layout.AC;
import net.miginfocom.layout.ConstraintParser;
import net.miginfocom.swing.MigLayout;
import qgb.T;
import qgb.interfaces.StopTask;

public class ResultsPanel extends JPanel implements StopTask {
	private final static ProgressPanel gspp = new ProgressPanel(9);
	private final static ResultsPanel gsrp = new ResultsPanel(gspp);// new
																	// ProgressPanel(5)

	public static void main(String[] args) {
		MainLoader.main(null);
		if (true)
			return;
		EventQueue.invokeLater(grun);
	}

	static Runnable grun = new Runnable() {
		public void run() {
			try {
				JFrame f = new JFrame(T.getCurrentClass().getName());
				f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
				f.getContentPane().setLayout(new GridLayout(1, 1));

				f.getContentPane().add(gsrp, gsrp.gsC_migl);
				f.pack();
				f.setLocation(411, 73);
				f.setVisible(true);
				f.getContentPane().setBackground(Color.BLACK);
				// QST.setAllBackground(gsrp.gSsP, Color.BLUE);
				// QST.setAllBackground(gsrp.gTP, Color.RED);
				gsrp.setBackground(Color.GREEN);
				// QST.setAllForeground(gsrp, Color.GREEN);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	};
	/******************* Test End ***********************/
	ArrayList<JPanel> galPanels = new ArrayList<JPanel>();
	public final String gsC_migl = "growx,growy,wrap";
	public final ProgressPanel gPP;
	public FindPanel gFP;
	public final WordPanel gWP = new WordPanel();
	public final SentencesPanel gSsP = new SentencesPanel();
	public final TransPanel gTP = new TransPanel();
	public final KingSoft gkSoft;
	private final StrNotNull gstMsg = new StrNotNull();

	public ResultsPanel(ProgressPanel app) {
		// super(new MigLayout(R.MgL.sL_noBorder,,
		// "[]0[grow]0[grow]"));
		super(new MigLayout(
				ConstraintParser.parseLayoutConstraint(R.MigL.sL_noBorder),
				ConstraintParser.parseColumnConstraints("[grow]"),// AC colConstraints
				getRowAC()// AC rowConstraints
				));
		gPP = app;
		gkSoft = new KingSoft(this);

		add(gWP, gWP.gsC_migl);
		add(gTP, gTP.gsC_migl);
		add(gSsP, gSsP.gsC_migl);
	}

	private static AC getRowAC() {
		AC ac = ConstraintParser.parseRowConstraints("[]0[]0[]").grow(25, 1);
		ac = ac.grow(15, 2);
		return ac;
	}

	private void CreateComponents() {
	}

	private void AddListener() {
//		gWP.addKeyListener(gFP.gKeyTab);
//		gWP.txtWord.addKeyListener(gFP.gKeyTab);
//		gTP.gtxtArea.addKeyListener(gFP.gKeyTab);
	}
	/**自动停止上一次查询**/
	public synchronized void showResults(final String astWord) {
		stop();
		//T.sleep(70);
		gbNotStop = true;
		gbIsDone = false;

		Runnable run = new Runnable() {
			public void run() {
				final Entry e = gkSoft.byWord(astWord);
				int ic = 0;
				while (gSsP.isDone() == false && gkSoft.isStop() == false) {
					if (isStop() || ic > 333) {
						break;
					}
					T.sleep(30);
					ic++;
				}

				if (e != null&& e.setting.gbIsFromDB==false&& isStop() == false) {
					gPP.showMsg("[" + e.word.stW.get() + "] done!");
					MDT.save(e);
					gbIsDone = true;
				}
				gFP.btnFind.setEnabled(true);
				// else if() stop();
			}
		};
		Thread t = new Thread(run);
		t.start();
		return ;
	}

	public synchronized void showMsg(final String ast) {
		stop();
		gstMsg.set(ast);
		gPP.showMsg(gstMsg + " " + T.getCurrentTime());
	}

	// 请谨慎使用 gbXXX 的值
	protected volatile boolean gbNotStop = true, gbIsDone = false;
	/***TODO:存在卡死问题***/
	//static int giStop=0;
	@Override
	public synchronized void stop() {
		//giStop++;
		gkSoft.stop();
		// this.setVisible(false);
//		if (giStop) {
//			
//		}
		gPP.reset();
		gWP.removeAll();
		gTP.removeAll();
		gSsP.removeAll();
		
		gFP.btnFind.setEnabled(true);
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

	public void setPanel(FindPanel aFP) {
		if (aFP == null) {
			T.argsError(aFP);
		}
		gFP = aFP;
	}

}
