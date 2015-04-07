package test.thread;

/**
* 本线程设置了一个超时时间
* 该线程开始运行后，经过指定超时时间，
* 该线程会抛出一个未检查异常通知调用该线程的程序超时
* 在超时结束前可以调用该类的cancel方法取消计时
* @author solonote
*/
public class TimeoutThread extends Thread{
/**
* 计时器超时时间
*/
private long timeout;
/**
* 计时是否被取消
*/
private boolean isCanceled = false;
/**
* 当计时器超时时抛出的异常
*/
private TimeoutException timeoutException;
/**
* 构造器
* @param timeout 指定超时的时间
*/
public TimeoutThread(long timeout,TimeoutException timeoutErr) {
super();
this.timeout = timeout;
this.timeoutException = timeoutErr;
//设置本线程为守护线程

this.setDaemon(true);
}
/**
* 取消计时
*/
public synchronized void cancel()
{
isCanceled = true;
}
/**
* 启动超时计时器
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
//注: 类一中的TimeoutException是下边的用户自定义类，不是java中的java.util.concurrent.TimeoutException

//类2.抛出异常类,该类继承了RuntimeException，原因是run方法不能抛出已检测异常。

public class TimeoutException extends RuntimeException {
/**
* 序列化号
*/
private static final long serialVersionUID = -8078853655388692688L;
public TimeoutException(String errMessage)
{
super(errMessage);
}
}
使用方法:

//初始化超时类

TimeoutThread t = new TimeoutThread(5000,new TimeoutException("超时"));
try{
t.start();
//.....要检测超时的程序段....
t.cancel();
}catch (TimeoutException e)
{
//...对超时的处理...
}
TimeoutException可以更换为其他未检查异常类。

