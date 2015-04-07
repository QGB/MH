package test.swing.component;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import qgb.T;

public class JTextArea2 {
	public static void main(String[] args) {
		JFrame f = new JFrame("JTextArea2");
		Container contentPane = f.getContentPane();
		contentPane.setLayout(new BorderLayout());

		JPanel p1 = new JPanel();
		p1.setLayout(new GridLayout(1, 1));
		p1.setBorder(BorderFactory
				.createTitledBorder("����TextArea��ʹ��GridLayout,��ScrollBar"));

		JTextArea t1 = new JTextArea(5, 25);
		//t1.setTabSize(10);
		t1.setFont(new Font("�꿬��", Font.BOLD, 16));
		t1.setLineWrap(true);// �����Զ����й���
		t1.setWrapStyleWord(true);// ������в����ֹ���
		t1.setText(T.getSource(JTextArea2.class));
		/**************************/
		
		p1.add(new JScrollPane(t1));// ��JTextArea����JScrollPane�У������������ù�����Ч���������볬��JTextArea�߶ȵ�
		// ����.
		contentPane.add(p1);
		f.pack();
		f.show();
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
	

}
