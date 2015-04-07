package mh.gui.test;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.InputStream;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import mh.gui.results.MH;
import mh.net.test.IO;
import mh.struct.entry.Entry;
import qgb.T;
import qgb.media.MP3player;
//import java.font.CStrike;

public class Search {
	public static final String cst="Search";
	JFrame frame;
	private Entry entry;
	private JTextField txtBook;
	private JTextField txtWord;
	private JTextField txtPhonetic;
	private JTextArea txtTranslations;
	private JTextField[] txten;//test
	private JTextField[] txtcn;
	private JButton[] btnen;
	JButton btnQgbButton ;
	JButton btnRead;
	JCheckBox btnAuto;
	private JTextField textField;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		//T.print("1");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//T.print("2");
					Search window = new Search();
					
					window.frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Search() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	private void initialize() {
		//T.print("3");
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setBackground(Color.BLACK);
		frame.getContentPane().addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				Resize();	
			}
		});
		//T.print("4");
		frame.setBounds(100, 100, 628, 380);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);		
		frame.setTitle("Search Entry");
		/////////////////
//		JButton btnNewButton = new JButton("test");
//		btnNewButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//			}
//		});
//		btnNewButton.setBounds(82, 41, 111, 29);
//		frame.getContentPane().add(btnNewButton);
//		
//		textField = new JTextField();
//		textField.addKeyListener(new KeyAdapter() {
//			@Override
//			public void keyReleased(KeyEvent e) {
//			}
//		});
//		textField.setBounds(82, 6, 134, 28);
//		frame.getContentPane().add(textField);
//		textField.setColumns(10);
//		
//		JCheckBox chckbxNewCheckBox = new JCheckBox("New check box");
//		chckbxNewCheckBox.setBounds(231, 41, 128, 23);
//		frame.getContentPane().add(chckbxNewCheckBox);
		////////////////


		btnQgbButton =  new JButton("search");
		btnQgbButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				show_entry();
			}
		});
		btnQgbButton.setBounds(204, 8, 90, 22);
		frame.getContentPane().add(btnQgbButton);

		/////////////////
		txtBook = new JTextField();
		txtBook.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				//T.print(e.keyCode);
				if (e.getKeyCode()==KeyEvent.VK_ENTER) {//������������������=13
					show_entry();
				}
			}
		});
		txtBook.setText("book");
		txtBook.setBounds(10, 10, 181, 22);
		frame.getContentPane().add(txtBook);

		//////////////////
		txtWord =new JTextField();
		txtWord.setEditable(false);
		txtWord.setText("word");
		txtWord.setBounds(10, 48, 103, 22);
		frame.getContentPane().add(txtWord);

		
		btnRead = new JButton();
		btnRead.setEnabled(false);
		btnRead.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				read(btnRead,entry.word.vUK.get());
				}
		});
		btnRead.setBounds(137, 48, 54, 22);
		btnRead.setText("read");
		frame.getContentPane().add(btnRead);

		
		txtPhonetic = new JTextField();
		txtPhonetic.setEditable(false);
		txtPhonetic.setText("phonetic");
		txtPhonetic.setBounds(204, 48, 90, 22);
		frame.getContentPane().add(txtPhonetic);


		
		txtTranslations = new JTextArea();
		txtTranslations.setEditable(false);
		txtTranslations.setLineWrap(true);
		//txtTranslations.set
		txtTranslations.setText("translations");
		txtTranslations.setBounds(313, 8, 297, 62);
		frame.getContentPane().add(txtTranslations);

		
		btnAuto = new JCheckBox();
		btnAuto.setSelected(true);
		btnAuto.setBounds(110, 46, 24, 24);
		btnAuto.setText("auto");
		frame.getContentPane().add(btnAuto);
		///////////
		JMenuBar menuBar = new JMenuBar();
		menuBar.setToolTipText("menu 1");
        JMenu MH_jMenu = new JMenu("MH");
        menuBar.add(MH_jMenu);
        JMenuItem mH_JMenuItem = new JMenuItem("MH");
        MH_jMenu.add(mH_JMenuItem);
        mH_JMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MH.main(new String[]{cst});
				}
		});
		frame.setJMenuBar(menuBar);
		
		
	}

	protected void x(JButton ab, InputStream ais) {
		if (ais == null) {
			T.notify("abis==null");
			return;
		}

		ab.setEnabled(false);
		MP3player.play(ais);
		ab.setEnabled(true);
	}

	private void show_entry(){
		btnQgbButton.setEnabled(false);
		try {
			txtBook.setText(txtBook.getText().trim());//������������
			entry=IO.get_Entry(txtBook.getText());
			if (entry==null) {
				btnQgbButton.setEnabled(true);
				return;
			}
		} catch (Exception e1) {
			// qgbTODO Auto-generated catch block
			e1.printStackTrace();
		}
		//T.print(entry.word.st_w);
		txtWord.setText(entry.word.st_w);
		if (entry.word.vUK.get()!=null) {
			btnRead.setEnabled(true);
			//T.print(entry.phonetic);
			//Entry.print(entry);
			if (btnAuto.isSelected()) {
				read(btnRead,entry.word.vUK.get());
			}
		}

		
		txtPhonetic.setText(entry.phonetic);
		txtTranslations.setText(entry.translations);
		
		if(txten!=null){
			for (int i = 0; i < 5; i++) {
				txten[i].setVisible(false);
				txtcn[i].setVisible(false);
				btnen[i].setVisible(false);
			}
		}
		if (entry.sentence!=null) {
			show_sentence();
		}
		
		btnQgbButton.setEnabled(true);
	}
	
	private void show_sentence(){
		if (txten==null) {
			txten=new JTextField[5];
			txtcn=new JTextField[5];
			btnen=new JButton[5];
			
			for (int i = 0; i < 5; i++) {
				txten[i]=new JTextField();
				txtcn[i]=new JTextField();
				btnen[i]=new JButton();

				frame.getContentPane().add(txten[i]);
				frame.getContentPane().add(txtcn[i]);
				frame.getContentPane().add(btnen[i]);
				set_btnenListener(i);
			}
		}
			
		
//			
//			txten=new Text[entry.sentence.length];
//			txtcn=new Text[entry.sentence.length];
//			btnen=new Button[entry.sentence.length];
		

		Point plocation=txtWord.getLocation();	
		int iy=plocation.y+22+10;
		int iwidget=plocation.x;
		
		Dimension psize=frame.getSize();	
		iwidget=psize.width-iwidget*2-40;
		
		Point ptemp;	
		
		for (int i = 0; i < entry.sentence.length; i++) {
//			if (txten[i]==null) {
//				txten[i]=new Text(shlWord, SWT.BORDER);
//			}
//			if (txtcn[i]==null) {
//				txtcn[i]=new Text(shlWord, SWT.BORDER);
//			}
//			if(btnen[i]==null) {
//				btnen[i]=new Button(shlWord, SWT.BORDER);
//			}
			txten[i].setVisible(true);
			txtcn[i].setVisible(true);
			btnen[i].setVisible(true);
			//T.print(""+i);
			txten[i].setEditable(false);
			txtcn[i].setEditable(false);
			

			//T.print(entry.sentence[i].ds.st_e);
			txten[i].setText(entry.sentence[i].ds.st_e);
			txtcn[i].setText(entry.sentence[i].ds.st_c);
			btnen[i].setText(Integer.toString(i+1));
				
			if (i==0) {
				txten[i].setBounds(plocation.x, iy, iwidget, 22);
				txtcn[i].setBounds(plocation.x, iy+23, iwidget, 22);
				btnen[i].setBounds(plocation.x+iwidget+5, iy, 30, 22);
			}else {
				ptemp=txtcn[i-1].getLocation();
				iy=ptemp.y+22+10;
				txten[i].setBounds(plocation.x, iy, iwidget, 22);
				txtcn[i].setBounds(plocation.x, iy+23, iwidget, 22);
				btnen[i].setBounds(plocation.x+iwidget+5, iy, 30, 22);
			}


		}
	}

	private void set_btnenListener(final int i) {
		btnen[i].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//T.print("btnen "+i);
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							btnen[i].setVisible(false);
							read(btnen[i], entry.sentence[i].v.get());
							btnen[i].setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				///////////////////////
			}
			
		});

			
	}
	
	
	protected void Resize() {
		Font font=new Font(null, 0, frame.getHeight()/5-5);	
	}
}
