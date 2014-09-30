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
    
	gbc = new GridBagConstraints(1,//�����еĵ�һ����Ԫ��Ϊ gridx=0��
            0,//ָ��λ�������ʾ����Ķ����ĵ�Ԫ���������ϱߵĵ�Ԫ��Ϊ gridy=0��
            GridBagConstraints.REMAINDER,//ָ�������ʾ�����ĳһ���еĵ�Ԫ������
            1,//ָ���������ʾ�����һ���еĵ�Ԫ����
            1.0,//ָ����ηֲ������ˮƽ�ռ䡣
            1.0,
            ,
            GridBagConstraints.NONE,//���������ʾ������������������ʾ����Ĵ�Сʱʹ�ô��ֶΡ�������ȷ���Ƿ���������С���Լ�����Ҫ��ʱ����ν��е�����
            Field.INSETS_BTN,//���ֶ�ָ��������ⲿ��䣬�����������ʾ�����Ե֮�������С����Ĭ��ֵΪ new Insets(0, 0, 0, 0)��
             -5,//���ֶ�ָ��������ڲ���䣬�����������С�����Ӷ��Ŀռ䡣����Ŀ������Ϊ����С��ȼ��� ipadx ���ء�
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
