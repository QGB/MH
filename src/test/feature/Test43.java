package test.feature;

interface Singer {
	public void sing();
	public void sleep();
}
interface Painter {
	public void paint();
	public void eat();
}

class Student implements Singer {
	private String name;
	Student(String name) {
		this.name = name;
	}
	public void study() {
		System.out.println("studying");
	}
	public String getName() {
		return name;
	}
	public void sing() {
		System.out.println("studeng is singing");
	}
	public void sleep() {
		System.out.println("student is sleeping");
	}
}

class Teacher implements Singer,Painter {
	private String name;
	public String getString() {
		return name;
	}
	Teacher(String name){
		this.name = name;
	}
	public void teach(){
		System.out.println("teaching");
	}
	public void sing() {
		System.out.println("teacher is singing");
	}
	public void sleep() {
		System.out.println("teacher is sleeping");
	}
	public void paint() {
		System.out.println("teacher is painting");
	}
	public void eat() {
		System.out.println("teacher is eating");
	}
}

public class Test43 {
	public static void main(String[] args) {
		Singer s1 = new Student("le");
		s1.sing();
		s1.sleep();
		Singer s2 = new Teacher("steven");
		Painter p1 = (Painter)s2;
		p1.paint();
		p1.eat();
	}
}