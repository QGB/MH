package mh.gui.auto;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collection;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import mh.MainLoader;
import mh.database.MDT;
import mh.gui.R;
import mh.gui.results.TransPanel;
import mh.struct.Voice;
import mh.struct.entry.Translations;
import net.miginfocom.swing.MigLayout;
import qgb.T;
import qgb.swing.QST;

public class TablePanel extends JScrollPane {
	public static void main(String[] args) {
//		 MainLoader.main(null);
//		 if (true) {
//		 return;
//		 }
		EventQueue.invokeLater(grun);
	}

	private static Runnable grun = new Runnable() {
		public void run() {
			try {
				final JFrame f = new JFrame(T.getCurrentClass().getName());
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				f.setLayout(new MigLayout(R.MigL.sL_noBorder, "[grow]",
						"[grow]0[]"));
				// f.setLayout(new GridLayout(4, 1));
				final TablePanel jp_f = new TablePanel();
				QST.setAllBackground(jp_f, Color.BLACK);
				QST.setAllForeground(jp_f, Color.GREEN);
				f.getContentPane().add(jp_f, jp_f.gsC_migl);
				/***** Test **********/
				final Voice voice = new Voice("m1", T.read_bis("v/g.mp3"));

				JButton btnP = new JButton("P");

				ActionListener actionP = new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						//jp_f.gTableModel.setValueAt("---", 9,1);
						//jp_f.gTable.setDragEnabled(false);
						for (String stw : AutoFindWords.textToList(T.read_st("e.txt"))) {
							jp_f.addTrans(jp_f.addWord(stw), "T "+stw);
							
						}
					}
				};
				btnP.addActionListener(actionP);
				f.getContentPane().add(btnP, R.MigL.sCHmin + ",split 3");
				// ////////////////////////////////////////////
				JButton btnV = new JButton("V");
				ActionListener actionVoice = new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						jp_f.gTable.getColumn(AFTableModel.gsColNum).setMaxWidth(60);
						QST.refresh(jp_f);
					}
				};
				btnV.addActionListener(actionVoice);
				f.getContentPane().add(btnV, R.MigL.sCHmin);
				// //////////////////////////////////////////////
				JButton btnT = new JButton("T");
				ActionListener actionT = new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						jp_f.removeAll();
						T.print();
					}
				};
				btnT.addActionListener(actionT);
				f.getContentPane().add(btnT, R.MigL.sCHmin);
				/***** Test End ******/
				f.pack();
				f.setLocation(411 - 300, 73 - 16);
				f.setMinimumSize(new Dimension(200, 99));
				f.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	};
	/************ Test Code End **********************/
	private final JTable gTable;
	private final AFTableModel gTableModel;
	public final static String gsC_migl = "h 65::,growx,growy,wrap";

	public TablePanel() {
		super(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		gTableModel=new AFTableModel();
		//gTableModel.
		gTable = new JTable(gTableModel);
		gTable.getColumn(AFTableModel.gsColNum).setPreferredWidth(30);
		gTable.getColumn(AFTableModel.gsColNum).setMaxWidth(60);
		gTable.getColumn(AFTableModel.gsColWord).setPreferredWidth(100);
		gTable.getColumn(AFTableModel.gsColWord).setMaxWidth(500);
		addListener();
		setViewportView(gTable);
	}

	private void addListener() {
		gTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(final MouseEvent e) {
				new Thread(new Runnable() {
					@Override
					public void run() {
						int rowI  = gTable.rowAtPoint(e.getPoint());// 得到table的行号
					    if (rowI > -1)
					    	MDT.getPhons(gTableModel.getValueAt(rowI, 1).toString()).playOneVoice();
					}
				}).start();
			}
		});
	}

	/**
	 * 不要求 在 EDT中调用。 自动刷新。
	 **/
	@Override
	public synchronized void removeAll() {
		gTableModel.setRowCount(0);
		QST.refreshAll(this);
	}
	
	public synchronized int addWord(String ast) {
		return gTableModel.addWord(ast);
	}
	
	public synchronized void addTrans(int aindex,Object aTrans) {
		gTableModel.setValueAt(aTrans, aindex,2);
	}
	
}