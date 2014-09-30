package qgb.interfaces;

import qgb.T;

public class Test implements GetSet {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Test().set("");
	} 

	@Override
	public Object get() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void set(Object at) {
		T.print("Test.set");
		// TODO Auto-generated method stub
		
	}}
