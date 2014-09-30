package test.thread;

/**
* ���߳�������һ����ʱʱ��
* ���߳̿�ʼ���к󣬾���ָ����ʱʱ�䣬
* ���̻߳��׳�һ��δ����쳣֪ͨ���ø��̵߳ĳ���ʱ
* �ڳ�ʱ����ǰ���Ե��ø����cancel����ȡ����ʱ
* @author solonote
*/
public class TimeoutThread extends Thread{
/**
* ��ʱ����ʱʱ��
*/
private long timeout;
/**
* ��ʱ�Ƿ�ȡ��
*/
private boolean isCanceled = false;
/**
* ����ʱ����ʱʱ�׳����쳣
*/
private TimeoutException timeoutException;
/**
* ������
* @param timeout ָ����ʱ��ʱ��
*/
public TimeoutThread(long timeout,TimeoutException timeoutErr) {
super();
this.timeout = timeout;
this.timeoutException = timeoutErr;
//���ñ��߳�Ϊ�ػ��߳�

this.setDaemon(true);
}
/**
* ȡ����ʱ
*/
public synchronized void cancel()
{
isCanceled = true;
}
/**
* ������ʱ��ʱ��
*/
public void run()
{
try {
Thread.sleep(timeout);
if(!isCanceled)
throw timeoutException;
} catch (InterruptedException e) {
e.printStackTrace();
}
}
}
//ע: ��һ�е�TimeoutException���±ߵ��û��Զ����࣬����java�е�java.util.concurrent.TimeoutException

//��2.�׳��쳣��,����̳���RuntimeException��ԭ����run���������׳��Ѽ���쳣��

public class TimeoutException extends RuntimeException {
/**
* ���л���
*/
private static final long serialVersionUID = -8078853655388692688L;
public TimeoutException(String errMessage)
{
super(errMessage);
}
}
ʹ�÷���:

//��ʼ����ʱ��

TimeoutThread t = new TimeoutThread(5000,new TimeoutException("��ʱ"));
try{
t.start();
//.....Ҫ��ⳬʱ�ĳ����....
t.cancel();
}catch (TimeoutException e)
{
//...�Գ�ʱ�Ĵ���...
}
TimeoutException���Ը���Ϊ����δ����쳣�ࡣ

