package test.swing;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import qgb.T;

public class Test extends JFrame {

	private JPanel contentPane;
	static JFrame jFrame=new JFrame("Test!");
	static int gi=0;
	
	public static void main(String[] args) {
		//jFrame.setDefaultCloseOperation(operation);
		jFrame.setVisible(true);
		for ( int i = 0; i < 9; i++) {
			EventQueue.invokeLater(new Runnable() {
			public void run() {
				invokeTest();	
				
			}
		});
			
			//TimeUnit.SECONDS.sleep(1);
		}

		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setBounds(300, 300, 450, 300);
		
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Test frame = new Test();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
	}

	/**
	 * Create the frame.
	 */
	public Test() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 300, 450, 300);
//		contentPane = new JPanel();
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//		contentPane.setLayout(new BorderLayout(0, 0));
//		setContentPane(contentPane);
	}
	
	private static void invokeTest() {
		gi=gi+1;
		jFrame.setBounds(0, 0, 99+gi*99, 99+gi*99);
		T.sleep(999);
		T.print(T.Thread());
		
	}
	
	
}
