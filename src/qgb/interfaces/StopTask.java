package qgb.interfaces;

/**	//�����ʹ�� gbXXX ��ֵ</br>
	protected volatile boolean gbNotStop=true,gbIsDone=false;
 	@Override public void stop(){gbNotStop=false;}
	@Override public boolean isStop() {return !gbNotStop;}
	@Override public boolean isDone() {return gbIsDone;}
**/
public interface StopTask {
	public void stop();
	public boolean isStop();
	public boolean isDone();
}
