package test.thread;

//注: 类一中的TimeoutException是下边的用户自定义类，不是java中的java.util.concurrent.TimeoutException

//类2.抛出异常类,该类继承了RuntimeException，原因是run方法不能抛出已检测异常。

public class QgbTimeoutException extends RuntimeException {
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
.....要检测超时的程序段....
t.cancel();
}catch (TimeoutException e)
{
...对超时的处理...
}
TimeoutException可以更换为其他未检查异常类。
