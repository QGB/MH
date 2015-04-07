package test;

import java.sql.Date;
import java.sql.Time;

import javax.xml.crypto.Data;

import qgb.T;


public class StringBuilder_test {
	static StringBuilder sbd=new StringBuilder();
	//static StringBuffer sbf=new StringBuffer();
	static java.util.Date date =new java.util.Date();
	
	
	public static void main(String[] args)  {	
//		Sub s1=new Sub(1);
//		Sub s2=new Sub(2);
//		Sub s3=new Sub(3);
		sbd.ensureCapacity(9999);

		
		Thread t1 = new Sub(300,"t1");
		Thread t2 = new Sub(200,"t2");
		Thread t3 = new Sub(100,"t3");
		
		
		t1.start();
		T.print("1");
		t2.start();
		T.print("2");
		t3.start();
		T.print("3");
		T.print(System.currentTimeMillis()+"main thread end");
		
		boolean ba=true;
		while (ba) {
			//T.print("check");
			if (t1.isAlive()||t2.isAlive()||t3.isAlive()) {
			}else {
				T.print(sbd.toString());
				ba=false;
			}
		}
		T.print("end!");
	}
	
	static class Sub extends Thread{
		private int gi;
		private String gst;
		public Sub(int ai,String ast) {
			super(ast);
			gi=ai;

			
		}

		@Override
		public void run() {
			gst=currentThread().getName()+"|"+gi+"|";
			//T.msgbox(gst);
			
			
			T.print(gst+" start!");
			
			for (int i = 0; i < 10; i++) {
				try {
					Thread.sleep(gi);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				sbd.append(gst+i+"|"+System.currentTimeMillis()+"\n");
				
			}
			

			T.print(gst+" end");
	}
		
	}

	
}

