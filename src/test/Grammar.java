package test;

abstract class ABSClass {
	public abstract void print();
}

public class Grammar {
	public static void test(final String s) {// һ���������������ڲ�ʹ��,�������final
		ABSClass c = new ABSClass() {
			public void print() {
				System.out.println(s);
			}
		};
		c.print();
	}

	public static void main(String[] args) {
		test("Hello World!");
	}
}
