package mh.gui.results;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import mh.gui.FindPanel;
import mh.gui.MainFrame;
import mh.gui.ProgressPanel;
import mh.gui.R;
import mh.gui.results.senten.SentencesPanel;
import mh.net.GetEntry;
import mh.net.kingsoft.KingSoft;
import mh.struct.StrNotNull;
import mh.struct.entry.Entry;
import net.miginfocom.layout.AC;
import net.miginfocom.layout.ConstraintParser;
import net.miginfocom.swing.MigLayout;
import qgb.T;;

public class ResultsPanel extends JPanel {
	private final static ProgressPanel gspp = new ProgressPanel(9);
	private final static ResultsPanel gsrp = new ResultsPanel(gspp);// new
																	// ProgressPanel(5)
	public static void main(String[] args) {
		MainFrame.main(null);
		if (true) return;
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
//				QST.setAllBackground(gsrp.gSsP, Color.BLUE);
//				QST.setAllBackground(gsrp.gTP, Color.RED);
				gsrp.setBackground(Color.GREEN);
				//QST.setAllForeground(gsrp, Color.GREEN);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	};
/*******************Test End***************************************************/
	ArrayList<JPanel> galPanels = new ArrayList<JPanel>();
	public String gsC_migl = "growx,growy,wrap";
	public ProgressPanel gPP;
	public FindPanel gFP;
	public WordPanel gWP=new WordPanel();
	public SentencesPanel gSsP=new SentencesPanel();
	public TransPanel gTP=new TransPanel();
	private GetEntry gkSoft;
	private StrNotNull gstMsg=new StrNotNull();
	public ResultsPanel(ProgressPanel app) {
//		super(new MigLayout(R.MigL.sL_noBorder,,
//				"[]0[grow]0[grow]"));
		super(new MigLayout(ConstraintParser.parseLayoutConstraint(R.MigL.sL_noBorder),
				ConstraintParser.parseColumnConstraints("[grow]"),//AC colConstraints
				getRowAC()//AC rowConstraints
				));
		gPP = app;
		gkSoft = new KingSoft(this,gFP);

		add(gWP,gWP.gsC_migl);
		add(gTP,gTP.gsC_migl);
		add(gSsP,gSsP.gsC_migl);
	}

	private static AC getRowAC() {
		AC ac=ConstraintParser.parseRowConstraints("[]0[]0[]").grow(5,1);
		ac=ac.grow(15,2);
		return ac;
	}

	private void CreateComponents() {
	}

	private void AddListener() {

	}

//	public void showWord(Word aword) {
//		gWP.showWord(aword);
//	}

	public void showResults(final String astWord) {
		stop();
		Runnable run=new Runnable() {
			public void run() {
				Entry e=gkSoft.byWord(astWord);
				int ic=0;
				while (gSsP.isDone()==false&&gkSoft.isStop()==false) {
					T.sleep(500);
					ic++;
					if(ic>33){
						//stop();
						break;
					}
				}
				gFP.btnFind.setEnabled(true);
				if(e!=null)gPP.showMsg("["+e.word.stW.get()+"] done!");
				//else if() stop();
			}
		};
		Thread t=new Thread(run);
		t.start();
		
	}

	public void showMsg(final String ast) {
		stop();
		gstMsg.set(ast);
		gPP.showMsg(gstMsg+" "+T.getCurrentTime());
	}
	public void stop() {
		gkSoft.stop();
		//this.setVisible(false);
		gPP.reset();
		gWP.removeAll();
		gTP.removeAll();
		gSsP.removeAll();
		gFP.btnFind.setEnabled(true);
	}

	public void setPanel(FindPanel aFP) {
		if (aFP==null) {
			T.argsError(aFP);
		}
		gFP=aFP;
	}
	
}
