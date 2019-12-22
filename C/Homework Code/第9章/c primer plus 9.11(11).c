// 第九章 第11题
#include <stdio.h>	

long Fibonacci(long n);

int main(void) {
	long n;

	printf("输入一个整数n: ");

	while (scanf("%ld", &n) == 1)
	{
		printf("斐波那契 #%ld = %ld\n", n, Fibonacci(n));

		printf("输入一个整数n: ");
	}

	return 0;
}

long Fibonacci(long n)
{

	if (n < 1)
	{
		printf("错误: n 必须是一个正整数.\n");
		return -1;
	}

	long fib_n1 = 0, fib_n = 1, fib_n2;

	if (n == 1) return 0;
	if (n == 2) return 1;

	for (long i = 3; i <= n; i++)
	{
		fib_n2 = fib_n1;
		fib_n1 = fib_n;
		fib_n = fib_n1 + fib_n2;
	}

	return fib_n;
}