package test.thread;

import java.util.Timer;
import java.util.TimerTask;

import qgb.T;


public class TimerTest {
	
	public static void main(String[] args)  {	
		Timer t=new Timer("QGB Timer thread ");
		TimerTask tt=new TimerTask() {
			
			@Override
			public void run() {
				T.print(T.Thread()+"|start|"+T.time());
				try {
					Thread.sleep(7000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				T.print(T.Thread()+"|end|"+T.time());
				this.cancel();
			}
		};
		
		T.print(T.Thread());
		T.print(T.time());
		t.schedule(new QgbTimerTask(t,tt,500), 0);
		//t.cancel();
		T.print(T.time());
		
	}
	
	
	
}


class QgbTimerTask extends TimerTask{
	Timer gt;
	TimerTask gtt; 
	long gl;
	public QgbTimerTask(Timer at,TimerTask att,long al_delay) {
		gt=at;
		gtt=att;
		gl=al_delay;
	}
	@Override
	public void run() {
		Timer timer=new Timer("task thread");
		T.print(this.getClass()+"| task start|"+T.time());
		T.print(T.Thread());
		timer.schedule(gtt, 0,gl);
		try {
			Thread.sleep(gl);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//gt.cancel();
		this.cancel();
		T.print(this.getClass()+"| task canceled|"+T.time());
		//T.exit();
		
	}
	
	
	
}