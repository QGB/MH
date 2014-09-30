package mh.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
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

import mh.database.FindHistory;
import mh.gui.results.ResultsPanel;
import mh.struct.StrNotNull;
import mh.struct.entry.Word;
import net.miginfocom.swing.MigLayout;
import qgb.T;
import qgb.swing.QST;
/**
 * String colConstraints="[][grow][]"**/
public class FindPanel extends JPanel {
	public static void main(String[] args) {
		// T.Thread(1);
		MainFrame.main(null);
		if(true)return;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame f = new JFrame(FindPanel.class.getName());
					f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					f.setLayout(new MigLayout(R.MigL.sL_noBorder, "[grow]", "[]"));
					
					final FindPanel jp_f = new FindPanel(null);
					QST.setAllBackground(jp_f, Color.BLACK);
					QST.setAllForeground(jp_f, Color.GREEN);
					f.getContentPane().add(jp_f,jp_f.gsC_migl);
					f.pack();
					//R.Frame.set(f);
					f.setLocation(411-300, 73-16);
					T.print(f.getSize());
					f.setMinimumSize(new Dimension(R.Frame.iwMin +R.Find.iWidthMin,
							R.Frame.ihMin +R.Find.iHeightMin));
					
					f.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/*************Test End******************/
	public JButton btnFind;
	public String gsC_migl="growx,wrap";

	JButton btnForward;
	JButton btnBack;
	JComboBox CBInput;
	private ResultsPanel gRP;
	private DefaultComboBoxModel gCBModel=new DefaultComboBoxModel();
	private int giCBindex=0;
	/**
	 * Create the Panel.
	 */
	public FindPanel(ResultsPanel arp) {
		super(new MigLayout(R.MigL.sL_noBorder, "[]0[]0[grow]0[]", R.MigL.sLmin));
		
		gRP=arp;
		
		CreateComponents();
		AddListener();

		add(btnForward, R.MigL.sCmin);// "h 32:32:32,w 32:32:32"
		add(btnBack, R.MigL.sCmin);
		//add(CBInput,"growx");
		add(CBInput,R.MigL.sC_inputLine+",growx");
		add(btnFind, R.MigL.sCHmin+","+R.MigL.sCW_btnMinLong);
		
		//gsC_migl=R.MigL.sCHmin+",w 250:200:2000";
	}

	private void AddListener() {
		ActionListener actionBack=new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CBInput.getEditor().setItem(gCBModel.getElementAt(giCBindex));
				if (giCBindex<gCBModel.getSize()) {
					giCBindex++;
				}
				//CBInput.setEnabled(!CBInput.isEnabled());
				//T.print(FindPanel.this.getSize());
			}
		};
		ActionListener actionForward = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CBInput.getEditor().setItem(gCBModel.getElementAt(giCBindex));
				if (giCBindex>0) {
					giCBindex--;
				}
			}
		};
		ActionListener actionFind = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				find();
			}
		};
		KeyListener keyEnter=new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {}
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER) {
					find();
				}
			}
			@Override
			public void keyPressed(KeyEvent e) {}
		}; 
		CBInput.getEditor().getEditorComponent().addKeyListener(keyEnter);
		//CBInput.addKeyListener(keyEnter);
		
		btnBack.addActionListener(actionBack);
		btnForward.addActionListener(actionForward);
		btnFind.addActionListener(actionFind);
	}

	protected void find() {
		String stW=CBInput.getEditor().getItem().toString().trim();
//		if (stW.length()>0) {throw new IllegalArgumentException("qgb!");}
//		else {T.argsError(e);}
//		T.print("s=%s,id=%s,c=%s\ntos=%s","",e.getID(),e.getActionCommand(),e.toString());
//		if (true) {return;}
		btnFind.setEnabled(false);
		//String stW=CBInput.getEditor().getItem().toString().trim();
		CBInput.getEditor().setItem(stW);
		//Word w=new Word(CBInput.getEditor().getItem().toString());
		//w.stW.set(CBInput.getEditor().getItem().toString());
		//gRP.gwp.showWord(w);
		
		gRP.showResults(stW);
		
		CBInput.getEditor().selectAll();
		CBInput.requestFocus();		
	}

	private void CreateComponents() {
		btnBack = new JButton(R.Find.iconBack);
		btnBack.setDisabledIcon(R.Find.iconBackDis);
		btnBack.setFocusable(false);
		//btnBack.setEnabled(false);

		btnForward = new JButton(R.Find.iconForward);
		btnForward.setDisabledIcon(R.Find.iconForwardDis);
		btnForward.setFocusable(false);
		
		/**JComboBox 下拉选择第一页项数 8 **/
		CBInput = new JComboBox();
		for (String stW:FindHistory.getLastWords(50))
			gCBModel.addElement(stW);
		CBInput.setModel(gCBModel);
		
		CBInput.setEditable(true);
		CBInput.setFont(new Font(Font.DIALOG, Font.PLAIN, 30));
		CBInput.getEditor().setItem("");
		
		btnFind = new JButton(R.Find.iconFind);
		btnFind.setDisabledIcon(R.Find.iconFindDis);

	}

	public void addHistory(String astW) {
		if (astW==null&&astW.length()<2) {
			return;
		}
		FindHistory.addWord(astW);
		int in=gCBModel.getIndexOf(astW);
		if (in!=-1) gCBModel.removeElementAt(in);
		gCBModel.insertElementAt(astW, 0);
		CBInput.getEditor().setItem(astW);
	}

}
