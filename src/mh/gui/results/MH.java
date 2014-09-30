package mh.gui.results;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import mh.gui.test.Search;
import qgb.T;

public class MH extends JFrame {
	public static Boolean csb_IsTest=true;

	static MH frame;
	static JPanel contentPane;
	static GridBagLayout gridBagLayout;
	static GridBagConstraints gbc;
	static JPanel north_jPanel;
		static JPanel northEast;
			static FlowLayout fl_NE;
			static JButton btn_mix;
			static ActionListener fullscreen_ActionListener=new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (btn_mix.getText().equals("O")) {
						frame.dispose();
						frame.setUndecorated(true);
						frame.getGraphicsConfiguration().getDevice()
								.setFullScreenWindow(frame);
						frame.setVisible(true);
						btn_mix.setText("o");
					} else {
						frame.dispose();
						frame.setUndecorated(true);
						frame.getGraphicsConfiguration().getDevice()
								.setFullScreenWindow(null);
						frame.setVisible(true);
						btn_mix.setText("O");
					}

				}
			};
			static JButton btn_min;
			static JButton btn_close;
		
			static JPanel north_left;
			static PropertyChangeListener changefontListener=new PropertyChangeListener() {
				@Override
				public void propertyChange(PropertyChangeEvent arg0) {
					JComponent jc= (JComponent) arg0.getSource();
					if (jc.getHeight()>0) {
						jc.setFont(new Font("", 0,Math.min(frame.getHeight()/20, 40) ));
					}
					//T.print(arg0.getSource());
					// TODO Auto-generated method stub
					
				}
			};
			static JTextField txt_word;
			static JTextField txt_phonetic;
	
			static JTextArea txtTranslations;
			
	private JButton btn1;
	ActionListener al_btn1 = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			printHeight();
		}
	};
	static int gi=0;
	private JButton btn2;
	ActionListener al_btn2 = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			gi=gi+1;
			btn1.addActionListener( new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					T.print(gi);
				}
			}
			
			);
		}
	};
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		if (args.length>0) {
			if (args[0]==Search.cst) {
				csb_IsTest=false;
			}
		}
		///////////////////////////////////////
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new MH();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	private void creat_componet() {
		gridBagLayout = new GridBagLayout();
		
		getContentPane().setLayout(gridBagLayout);
		gbc = new GridBagConstraints();
		

		txt_phonetic= new JTextField("phonetic ");
		//txt_phonetic.setPreferredSize(Field.D_TXT_WORD);
		txt_phonetic.setBackground(Color.BLACK);
		txt_phonetic.setForeground(Color.GREEN);
		txt_phonetic.addPropertyChangeListener( changefontListener);
		gbc.weightx = 0.3;
		gbc.weighty=0.1;
		gbc.fill=GridBagConstraints.BOTH;
		gridBagLayout.setConstraints(txt_phonetic,gbc);
		getContentPane().add(txt_phonetic);
		
		txt_word = new JTextField("word ");
		//txt_word.setEditable(false);
		//txt_word.setFont(qgb.swing.Field.);
		txt_word.setBackground(Color.BLACK);
		txt_word.setForeground(Color.GREEN);
		txt_word.addPropertyChangeListener( changefontListener);
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0;
		gridBagLayout.setConstraints(txt_word,gbc);
		getContentPane().add(txt_word);
		
		
			
		northEast();
		gbc.gridwidth=GridBagConstraints.REMAINDER;
		gbc.weightx = 0.0;
		gbc.weighty=0;
		gbc.anchor=GridBagConstraints.NORTHEAST;
		gridBagLayout.setConstraints(northEast, gbc);
		getContentPane().add(northEast);
			
		
		txtTranslations = new JTextArea("Translations");
		txtTranslations.setLineWrap(true);
		txtTranslations.setBackground(Color.BLACK);
		txtTranslations.setForeground(Color.GREEN);
		txtTranslations.addPropertyChangeListener( changefontListener);
		//txtTranslations.setEditable(false);
		//txtTranslations.setText(T.repeat(99, "translations") );
		//gbc.weightx = 1.0;
		gbc.weighty = 2;
		gbc.fill = GridBagConstraints.BOTH;
		gridBagLayout.setConstraints(txtTranslations, gbc);
		getContentPane().add(txtTranslations);
		//contentPane.setLayout(gridBagLayout);;
		//frame.setContentPane(contentPane);
		
		btn1=new JButton();
		btn1.addActionListener(al_btn1);
		gridBagLayout.setConstraints(btn1, gbc);
		getContentPane().add(btn1);	
		
		btn2=new JButton("btn2");
		btn2.addActionListener(al_btn2);
		gridBagLayout.setConstraints(btn2, gbc);
		getContentPane().add(btn2);	
	}

	protected static void exit() {
		// TODO Auto-generated method stub
		frame.dispose();
		if (csb_IsTest) {
			System.exit(0);
		}
		
	}


	protected static boolean IsFullScreen() {
		if (btn_mix.getText().equals("O")) {
			return(false);
		}
		return(true);
	}
	
	private void northEast() {
		northEast = new JPanel();
		northEast .setLayout(new BoxLayout(northEast , BoxLayout.X_AXIS ));
		northEast.setBackground(Color.DARK_GRAY);
		
			btn_min = new JButton("-");
			//btn_min=Set.UnifiedLook(btn_min, Field.D_BTN_SMALL);
			btn_min.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {					
					if (IsFullScreen()) {
						fullscreen_ActionListener.actionPerformed(e);
					}
					frame.setExtendedState(JFrame.ICONIFIED);
				}
			});
			northEast.add(btn_min);
	
			btn_mix = new JButton("O");
			//btn_mix=Set.UnifiedLook(btn_mix, Field.D_BTN_SMALL);
			btn_mix.addActionListener(fullscreen_ActionListener);
			northEast.add(btn_mix);
	
			btn_close = new JButton("X");
			//btn_close=Set.UnifiedLook(btn_close, Field.D_BTN_SMALL);
			btn_close.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					exit();
				}
			});
			northEast.add(btn_close);		
		
	}
	
	
	protected void printHeight() {
		btn1.removeActionListener(al_btn1);
		btn1.setBackground(Color.black);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					T.print("qgb1_"+txt_phonetic.getHeight());
					T.print("qgb2_"+txt_word.getHeight());
					T.print("qgb3_"+txtTranslations.getHeight());
						Thread.sleep(2200);
					btn1.setBackground(new Color(238, 238, 238));
					//btn.addActionListener(al_btn);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		T.print("al end");

	}


	/**
	 * Create the frame.
	 */
	public MH() {
		//contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		//contentPane = new JPanel();
		//contentPane.setBackground(Color.BLACK);
		
		setBounds(100, 100, 450, 300);
		setUndecorated(true);
		creat_componet();
		setLocationRelativeTo(null);
        getContentPane().setBackground(Color.BLACK);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		//contentPane.setLayout(gridBagLayout);	
		//frame.setContentPane(contentPane);

	}

}
