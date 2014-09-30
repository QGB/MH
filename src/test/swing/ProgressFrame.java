package test.swing;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

import qgb.T;

public class ProgressFrame extends JFrame implements ActionListener {

    private JButton btn = new JButton("Start");
    private JProgressBar bar = new JProgressBar(){
        public void paint(Graphics g) {
            super.paint(g);
            System.out.println("paint");
        }
    };

	Runnable runb=new Runnable() {
		@Override
		public void run() {
	    	T.print("runab:"+SwingUtilities.isEventDispatchThread());
		}
	};
    
    public ProgressFrame() {
    	T.print("runa1:"+SwingUtilities.isEventDispatchThread());
    	runb.run();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300,200);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());
        add(btn);
        add(bar);
        btn.addActionListener(this);
        setVisible(true);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        Runnable runnable = new Runnable() {
            int i = 0;
            @Override
            public void run() {
                for (i = 0; i <= 10; i++) {
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                    SwingUtilities.invokeLater(new Runnable() {
                        
                        @Override
                        public void run() {
                            bar.setValue(i * 10);
                        }
                    });
                    
                }
            }
        };//new Runnable() 
        
        new Thread(runnable).start();
    }
 
    
    
    public static void main(String[] args) {
    	T.print(SwingUtilities.isEventDispatchThread());
    	
    	Runnable runa=new Runnable() {
			@Override
			public void run() {
				T.print("runa:"+SwingUtilities.isEventDispatchThread());
				new ProgressFrame();
			}
		};
    	
		
		
    	try {
			SwingUtilities.invokeLater(runa);
		} catch (InvocationTargetException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
        //new ProgressFrame();
    }


}