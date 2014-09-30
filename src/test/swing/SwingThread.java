package test.swing;

import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;

import qgb.T;

public class SwingThread{
	
	 static Runnable doHelloWorld = new Runnable() {
	     public void run() {
	    	 try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	         System.out.println("doHelloWorld:" + Thread.currentThread());
	     }
	 };
	 
	public static void main(String[] args){
		Thread aThread =new Thread(doHelloWorld);
		aThread.run();

			return;

//		 try {
//			SwingUtilities.invokeAndWait(doHelloWorld);
//		} catch (InvocationTargetException | InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		SwingUtilities.invokeLater(doHelloWorld);
		
		 try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 System.out.println("main:"+ Thread.currentThread());
	}
}