package test.thread;

//ע: ��һ�е�TimeoutException���±ߵ��û��Զ����࣬����java�е�java.util.concurrent.TimeoutException

//��2.�׳��쳣��,����̳���RuntimeException��ԭ����run���������׳��Ѽ���쳣��

public class QgbTimeoutException extends RuntimeException {
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
.....Ҫ��ⳬʱ�ĳ����....
t.cancel();
}catch (TimeoutException e)
{
...�Գ�ʱ�Ĵ���...
}
TimeoutException���Ը���Ϊ����δ����쳣�ࡣ
