package mh.gui;

import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentSkipListSet;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import mh.MainLoader;
import mh.database.FindHistory;
import mh.gui.auto.AFDialog;
import mh.gui.results.ResultsPanel;
import mh.net.test.KingsoftTest;
import mh.struct.StrNotNull;
import mh.struct.entry.Word;
import net.miginfocom.swing.MigLayout;
import qgb.T;
import qgb.swing.QST;
import qgb.text.QText;
import sun.font.FontManager;

/**
 * String colConstraints="[][grow][]"
 **/
public class FindPanel extends JPanel {
	public static void main(String[] args) {
		// T.Thread(1);
		MainLoader.main(null);
		if (true)
			return;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame f = new JFrame(FindPanel.class.getName());
					f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					f.setLayout(new MigLayout(R.MigL.sL_noBorder, "[grow]",
							"[]"));

					final FindPanel jp_f = new FindPanel(null);
					QST.setAllBackground(jp_f, Color.BLACK);
					QST.setAllForeground(jp_f, Color.GREEN);
					f.getContentPane().add(jp_f, jp_f.gsC_migl);
					f.pack();
					// R.Frame.set(f);
					f.setLocation(411 - 300, 73 - 16);
					T.print(f.getSize());
					f.setMinimumSize(new Dimension(R.Frame.iwMin
							+ R.Find.iWidthMin, R.Frame.ihMin
							+ R.Find.iHeightMin));

					f.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/************* Test End ******************/
	public JButton btnFind;
	public String gsC_migl = "growx,wrap";

	JButton btnForward;
	JButton btnBack;
	JComboBox CBInput;
	volatile public ResultsPanel gRP;
	private DefaultComboBoxModel gCBModel = new DefaultComboBoxModel();
	private int giCBindex = 0;
	volatile private boolean gbIsControlDown = false;
	volatile private Frame gFrame;
	volatile private long glTime;

	/**
	 * Create the Panel.
	 */
	public FindPanel(ResultsPanel arp) {
		super(
				new MigLayout(R.MigL.sL_noBorder, "[]0[]0[grow]0[]",
						R.MigL.sLmin));
		gRP = arp;

		CreateComponents();
		AddListener();

		add(btnForward, R.MigL.sCmin);// "h 32:32:32,w 32:32:32"
		add(btnBack, R.MigL.sCmin);
		// add(CBInput,"growx");
		add(CBInput, R.MigL.sC_inputLine + ",growx");
		add(btnFind, R.MigL.sCHmin + "," + R.MigL.sCW_btnMinLong);
		// gsC_migl=R.MigL.sCHmin+",w 250:200:2000";
	}

	private void AddListener() {
		ActionListener actionBack = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (gbIsControlDown) {
					//T.run("cmd start "+KingsoftTest.printUrl(getWord()));
					//T.run("cmd");
					Runtime r=Runtime.getRuntime();
					T.print(((float)r.freeMemory()/r.totalMemory()));
					return;
				}
				
				if (giCBindex < (gCBModel.getSize() - 1)) {
					giCBindex++;
				}
				CBInput.getEditor().setItem(gCBModel.getElementAt(giCBindex));
				// CBInput.setEnabled(!CBInput.isEnabled());
				// T.print(FindPanel.this.getSize());
			}
		};
		ActionListener actionForward = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (giCBindex > 0) {
					giCBindex--;
				}
				CBInput.getEditor().setItem(gCBModel.getElementAt(giCBindex));
			}
		};
		ActionListener actionFind = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (gbIsControlDown) {
					// AFDialog dialog =
					//T.print("gbIsC "+gbIsControlDown);
					new AFDialog(gFrame,FindPanel.this);
					return;
				}
				find();
			}
		};

		btnBack.addActionListener(actionBack);
		btnForward.addActionListener(actionForward);
		btnFind.addActionListener(actionFind);

		AWTEventListener aKeyTab = new AWTEventListener() {
			@Override
			public void eventDispatched(AWTEvent e) {
				KeyEvent kEvent = ((KeyEvent) e);
				gbIsControlDown = kEvent.isControlDown();
				
				if (kEvent.getID() == KeyEvent.KEY_RELEASED) {
					int ikey = kEvent.getKeyCode();
					if (ikey == KeyEvent.VK_TAB) {
						select();
					}
					if (ikey == KeyEvent.VK_ENTER) {
						find();
					}
				}

			}
		};
		QST.gT.addAWTEventListener(aKeyTab, AWTEvent.KEY_EVENT_MASK);

	}

	protected void find() {
		btnFind.setEnabled(false);
		String stW=getWord();
		CBInput.getEditor().setItem(stW);
		gRP.showResults(stW);
		select();
	}
	private String getWord() {
		String stW = CBInput.getEditor().getItem().toString().trim();
		stW = QText.delChars(stW, '|', '`', '~', '!', '@', '#', '$', '%', '^',
				'&', '*', '(', ')');
		return stW;
	}
	
	//////////////////////////////////////////////////
	private void CreateComponents() {
		btnBack = new JButton(R.Find.iconBack);
		btnBack.setDisabledIcon(R.Find.iconBackDis);
		btnBack.setFocusable(false);
		// btnBack.setEnabled(false);

		btnForward = new JButton(R.Find.iconForward);
		btnForward.setDisabledIcon(R.Find.iconForwardDis);
		btnForward.setFocusable(false);

		/** JComboBox 下拉选择第一页项数 8 **/
		CBInput = new JComboBox();
		for (String stW : FindHistory.getLastWords(50))
			gCBModel.addElement(stW);
		CBInput.setModel(gCBModel);

		CBInput.setEditable(true);
		CBInput.setFont(new Font(Font.DIALOG, Font.PLAIN, 30));
		CBInput.getEditor().setItem("");

		btnFind = new JButton(R.Find.iconFind);
		btnFind.setDisabledIcon(R.Find.iconFindDis);

	}

	public void addHistory(String astW) {
		if (astW == null && astW.length() < 2) {
			return;
		}
		FindHistory.addWord(astW);
		int in = gCBModel.getIndexOf(astW);
		if (in != -1)
			gCBModel.removeElementAt(in);
		gCBModel.insertElementAt(astW, 0);
		CBInput.getEditor().setItem(astW);
		select();
	}

	public void select() {
		CBInput.getEditor().selectAll();
		CBInput.requestFocus();
	}
	/**for AutoFind**/
	public void setFrame(Frame aFrame) {
		if (aFrame == null) {
			T.argsError(aFrame);
		}
		gFrame = aFrame;
	}
	public Frame getFrame() {
		if (gFrame == null) {
			throw new IllegalStateException("setFrame 没有执行");
		}
		return gFrame;
	}
}
