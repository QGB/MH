package test.swing;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import qgb.swing.Field;
import qgb.swing.Set;

public class GridBagEx1 extends JFrame {

	static JPanel north_right;
	static JButton btn_mix;
	static JButton btn_min;
	static JButton btn_close;

	Dimension d_btn_small = new Dimension(20, 20);
	private GridBagConstraints gbc;

	protected void makebutton(String name, GridBagLayout gridbag,
			GridBagConstraints c) {
		JButton button = new JButton(name);
		gridbag.setConstraints(button, c);
		add(button);
	}

	public void init() {
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();

		// setFont(new Font("", Font.BOLD , 44));
		setLayout(gridbag);

		// gridbag.setConstraints(north_right,c);
		// makebutton("button 4", gridbag, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		//c.weightx = 0.0;
		JButton button = new JButton("Button1");
		gridbag.setConstraints(button, c);
		add(button);
		
		
		//c.weightx = 0.0;
		//makebutton("Button1", gridbag, c);
		
		//north_right();
	    JPanel box = new JPanel();
	    box.setLayout(new BoxLayout(box, BoxLayout.X_AXIS)); 
	    for (int i = 0; i < 3; i++) {
	      Button b = new Button("B" + i);
	      //b.setPreferredSize(new Dimension( 20, 20));
	      box.add(b);
	    }
		gbc = new GridBagConstraints(1,//�����еĵ�һ����Ԫ��Ϊ gridx=0��
                0,//ָ��λ�������ʾ����Ķ����ĵ�Ԫ���������ϱߵĵ�Ԫ��Ϊ gridy=0��
                GridBagConstraints.REMAINDER,//ָ�������ʾ�����ĳһ���еĵ�Ԫ������
                1,//ָ���������ʾ�����һ���еĵ�Ԫ����
                1.0,//ָ����ηֲ������ˮƽ�ռ䡣
                1.0,
                GridBagConstraints.NORTHEAST,
                GridBagConstraints.NONE,//���������ʾ������������������ʾ����Ĵ�Сʱʹ�ô��ֶΡ�������ȷ���Ƿ���������С���Լ�����Ҫ��ʱ����ν��е�����
                Field.INSETS_BTN,//���ֶ�ָ��������ⲿ��䣬�����������ʾ�����Ե֮�������С����Ĭ��ֵΪ new Insets(0, 0, 0, 0)��
                 -5,//���ֶ�ָ��������ڲ���䣬�����������С�����Ӷ��Ŀռ䡣����Ŀ������Ϊ����С��ȼ��� ipadx ���ء�
                 -5);
		gridbag.setConstraints(box, gbc);
		add(box);
		
//
		c.weightx = 1.0;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		makebutton("Button2", gridbag, c);
//		c.weightx = 0.0;
//		makebutton("Button3", gridbag, c);
//
//
//		
//		c.fill = GridBagConstraints.NONE;
//		c.weightx = 0.0;
//		//c.anchor=GridBagConstraints.NORTHEAST;
//		c.gridwidth = GridBagConstraints.REMAINDER; // end row
//		c.weightx = 0.0; // reset to the default
//		makebutton("Button5", gridbag, c); // another row
//
//		c.gridwidth = GridBagConstraints.RELATIVE; // next-to-last in row
//		makebutton("Button6", gridbag, c);
//
//		c.gridwidth = GridBagConstraints.REMAINDER; // end row
//		makebutton("Button7", gridbag, c);
//
//		c.gridwidth = 1; // reset to the default
//		c.gridheight = 2;
//		c.weighty = 1.0;
//		makebutton("Button8", gridbag, c);
//
//		c.weighty = 0.0; // reset to the default
//		c.gridwidth = GridBagConstraints.REMAINDER; // end row
//		c.gridheight = 1; // reset to the default
//		makebutton("Button9", gridbag, c);
//		makebutton("Button10", gridbag, c);

		// setSize(300, 100);
	}

	private void north_right() {
		north_right = new JPanel();
		north_right.setBackground(Color.DARK_GRAY);

		btn_min = new JButton("-");
		btn_min = Set.UnifiedLook(btn_min, d_btn_small);
		north_right.add(btn_min);

		btn_mix = new JButton("O");
		btn_mix = Set.UnifiedLook(btn_mix, d_btn_small);

		north_right.add(btn_mix);

		btn_close = new JButton("X");
		btn_close = Set.UnifiedLook(btn_close, d_btn_small);
		north_right.add(btn_close);
		// return north_right;
	}

	public static void main(String args[]) {
		GridBagEx1 f = new GridBagEx1();
		// GridBagEx1 ex1 = new GridBagEx1();

		f.init();

		// f.add( ex1);
		f.pack();
		f.setSize(f.getPreferredSize());
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}
}
