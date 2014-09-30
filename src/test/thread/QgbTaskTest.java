package test.thread;

import java.util.Timer;
import java.util.TimerTask;

import qgb.T;
import qgb.net.Get;


public class QgbTaskTest {
	
	public static void main(String[] args)  {	
		//Timer t=new Timer("QGB Timer thread ");
		Runnable tTask=new Runnable() {
			@Override
			public void run() {
				T.print(T.Thread()+"|start|"+T.time());
				StringBuffer sb=new StringBuffer();
				for (int i = Integer.MIN_VALUE; i < Integer.MAX_VALUE; i++) {
//					T.print("html");
//					try {
						sb.append(Get.html("http://www.baidu.com/#wd="+i));
//					} catch (Exception e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					T.print("html");
					//T.print(i-Integer.MIN_VALUE);
					if (i % 10000000==0) {
						T.print("tTask:"+i);
					}
				}
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				T.print(T.Thread()+"|end|"+T.time());
				//this.cancel();
			}
		};
		Qgb qgb=new Qgb(tTask, 1000) ;
		
		T.print(T.Thread());
		T.print(T.time());
		qgb.start();
		//t.cancel();
		T.print(T.time());
		
	}
	
	
	
}


class Qgb extends Thread{
	Runnable gRunnable;
	long gl;
	
	
	public Qgb(Runnable aRunnable,long al_delay) {
		super("Qgb exeute Thread");
		gRunnable=aRunnable;
		gl=al_delay;
	}
	
	
	@Override
	public void run() {
		T.print(T.Thread());
		Thread ttask=new Thread(gRunnable,"task thread");
		T.print(this.getClass()+"| task start|"+T.time());

		ttask.start();
		try {
			Thread.sleep(gl);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//gt.cancel();
		ttask.stop();

		T.print(this.getClass()+"| task canceled|"+T.time());
		//T.exit();		
	}

}