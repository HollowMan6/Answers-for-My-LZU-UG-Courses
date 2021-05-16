import java.util.Scanner;

class test {
	public static void main(String[] args) {
		int x, y, result = 0;
		char op;
		try {
			x = Integer.parseInt(args[0]);
			y = Integer.parseInt(args[2]);
			op = args[1].charAt(0);
			switch (op) {
			case '+':
				result = x + y;
				break;
			case '-':
				result = x - y;
				break;
			case '*':
				result = x * y;
				break;
			case '/':
				result = x / y;
				break;
			default:
				System.out.println("Error op!");
				break;
			}
			System.out.println("result=" + result);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("捕获到了数组下标越界异常，传入参数错误");
		} catch (ArithmeticException e) {
			System.out.println("捕获到了数学类异常，除数不能为0");
			System.out.println("请重新输入除数：");
			Scanner scanner = new Scanner(System.in);
			y = scanner.nextInt();
			x = Integer.parseInt(args[0]);
			result = x / y;
			System.out.println("result=" + result);
		} catch (NumberFormatException e) {
			System.out.println("捕获到非法字符异常");
		} finally {
			System.out.println("不管是否有异常，总是要执行");
		}
	}
}