/**
 * 
 */
package qgb.interfaces;

import java.io.InputStream;

import org.omg.CORBA.Object;

/**
 * @author Administrator
 * TODO:	protected static void test();���벻�ᱨ��
 * 
 */
public interface GetSet<T>{
	T get();
	void set(T at);
}
