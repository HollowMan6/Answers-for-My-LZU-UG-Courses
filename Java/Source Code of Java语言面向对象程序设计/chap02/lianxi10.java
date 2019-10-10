public class lianxi10 {
	public static void main(String[] args) {
		double h = 100,s = 100;
		for(int i=1; i<10; i++) {
			s = s + h;
			h = h / 2;
		}
		System.out.println("经过路程：" + s);
		System.out.println("反弹高度：" + h / 2);
	}
} 