public class Outer {
	private static int i = 1;
	private int j = 10;
	public static void outer_f1() {
	}

	public void outer_f2() {
	}

// 静态内部类可以用public,protected,private修饰
// 静态内部类中可以定义静态或者非静态的成员
static class Inner {
	static int inner_i = 100;
	int inner_j = 200;
	static void inner_f1() {
	//静态内部类只能访问外部类的静态成员(包括静态变量和静态方法)
	System.out.println("Outer.i=" + i);
	outer_f1();
	}

	void inner_f2() {
	// 静态内部类不能访问外部类的非静态成员(包括非静态变量和非静态方法)
	// System.out.println("Outer.i"+j);
	// outer_f2();
	}
}

	public void outer_f3() {
		// 外部类访问内部类的静态成员：内部类.静态成员
		System.out.println(Inner.inner_i);
		Inner.inner_f1();
		// 外部类访问内部类的非静态成员:实例化内部类即可
		Inner inner = new Inner();
		inner.inner_f2();
	}

	public static void main(String[] args) {
		new Outer().outer_f3();
	}
}
