package test.swing;

import java.awt.Button;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Panel;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import qgb.swing.Field;

// HBox.java
// A quick test of the BoxLayout manager using the Box utility class.
//
//import java.awt.*;
//import java.awt.event.*;
//import javax.swing.*;

public class HBox extends JFrame {

  public HBox() {
   super("Horizontal Box Test Frame");
   setSize(200, 100);
   
   JPanel rootJPanel =(JPanel) getContentPane();   
   
   //rootJPanel.setLayout(new BoxLayout(rootJPanel,BoxLayout.X_AXIS));
	GridBagLayout gridbag = new GridBagLayout();
	GridBagConstraints gbc = new GridBagConstraints();
   rootJPanel.setLayout(new GridLayout());
    
    // Use BoxLayout.Y_AXIS below if you want a vertical box
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
            ,
            GridBagConstraints.NONE,//当组件的显示区域大于它所请求的显示区域的大小时使用此字段。它可以确定是否调整组件大小，以及在需要的时候如何进行调整。
            Field.INSETS_BTN,//此字段指定组件的外部填充，即组件与其显示区域边缘之间间距的最小量。默认值为 new Insets(0, 0, 0, 0)。
             -5,//此字段指定组件的内部填充，即给组件的最小宽度添加多大的空间。组件的宽度至少为其最小宽度加上 ipadx 像素。
             -5);
	gridbag.setConstraints(box, gbc);
    rootJPanel.add(box);
    
    Button b = new Button("B" );
	gridbag.setConstraints(b, gbc);
    rootJPanel.add(b);
    
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setVisible(true);
    setLocationRelativeTo(null);
  }

  public static void main(String args[]) {
    HBox bt = new HBox();
  }
}
