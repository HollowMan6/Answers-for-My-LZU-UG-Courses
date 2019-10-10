import java.util.Arrays;

public class shiyan6_2_5 {
	public static void main(String[] args) {
		int[] a = { 5, 1, 3, 2, 4, 8, 7, 9, 10, 6 };
		System.out.print("排序前：");
		for (int i = 0; i < a.length; i++)
			System.out.print(" " + a[i]);
		Arrays.sort(a, 0, a.length);
		System.out.print("\n排序后：");
		for (int i = 0; i < a.length; i++)
			System.out.print(" " + a[i]);
		System.out.print("\n查找元素值4");
		int k = Arrays.binarySearch(a, 4);
		if (k < 0)
			System.out.print("\n没有找到元素值4");
		else
			System.out.print("\n元素值4在数组中的下标为：" + k);
		int[] c = new int[10];int[] d=new int[10];
		Arrays.fill(c, 4);Arrays.fill(d, 4);
		System.out.print("\n数组c:");
		for(int i=0;i<c.length;i++)System.out.print(" "+c[i]);
		System.out.println("\nquals(a,c) = "+Arrays.equals(a, c));
		System.out.println("equals(c,d) = "+Arrays.equals(c, d));
	}
}