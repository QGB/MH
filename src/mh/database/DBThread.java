package mh.database;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import qgb.T;


public class DBThread implements Runnable{
	public static void main(String[] args) {
		DBThread dbRun=new DBThread();
		new Thread(dbRun).start();
		for (int i = 0; i < 99; i++) {
			final int index=i;
			Runnable run=new Runnable() {
				@Override
				public void run() {
					T.print("%d at %s start!",index,T.getCurrentThreadName());
					T.sleep(2000);
					T.print("%d at %s stop!",index,T.getCurrentThreadName());
				}
			};
			dbRun.invoke(run);	
			T.msgbox();
		}
		
	}
////////////////////////////////////////////
	LinkedBlockingQueue<Runnable> gQRunQueue=new LinkedBlockingQueue<Runnable>();
	
	public boolean invoke(Runnable aqr) {
		if (aqr==null)T.argsError(aqr);
		gQRunQueue.add(aqr);
		return false;
	}

	@Override
	public void run() {
		Runnable qRun=null;
		try {
			qRun=gQRunQueue.poll(9,TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		qRun.run();
		run();
	}
}
