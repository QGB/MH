package qgb.interfaces;

/**不可多次改变 gbXXX 的值**/
public interface QRunable extends Runnable{
//	boolean gb_NotStop=true;
//	boolean gb_IsDone=false;
//	
	public void stop();
	public boolean isStop();
	public boolean isDone();
	/**
	private boolean gbNotStop=true;
	@Override
	public void stop(){
		gbNotStop=false;
	}
	@Override
	public boolean isStop() {
		return !gbNotStop;
	}
	private boolean gbIsDone=false;
	@Override
	public boolean isDone() {
		return gbIsDone;
	}
	**/
	
}
