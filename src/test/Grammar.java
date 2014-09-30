package test;

abstract class ABSClass {
	public abstract void print();
}

public class Grammar {
	public static void test(final String s) {// 一旦参数在匿名类内部使用,则必须是final
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
