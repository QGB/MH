package qgb.interfaces;

/**���ɶ�θı� gbXXX ��ֵ**/
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
