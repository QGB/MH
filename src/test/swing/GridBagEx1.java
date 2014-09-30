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
		gbc = new GridBagConstraints(1,//其中行的第一个单元格为 gridx=0。
                0,//指定位于组件显示区域的顶部的单元格，其中最上边的单元格为 gridy=0。
                GridBagConstraints.REMAINDER,//指定组件显示区域的某一行中的单元格数。
                1,//指定在组件显示区域的一列中的单元格数
                1.0,//指定如何分布额外的水平空间。
                1.0,
                GridBagConstraints.NORTHEAST,
                GridBagConstraints.NONE,//当组件的显示区域大于它所请求的显示区域的大小时使用此字段。它可以确定是否调整组件大小，以及在需要的时候如何进行调整。
                Field.INSETS_BTN,//此字段指定组件的外部填充，即组件与其显示区域边缘之间间距的最小量。默认值为 new Insets(0, 0, 0, 0)。
                 -5,//此字段指定组件的内部填充，即给组件的最小宽度添加多大的空间。组件的宽度至少为其最小宽度加上 ipadx 像素。
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
