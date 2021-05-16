#include<stdio.h>
#include<stdlib.h>
int main()
{
	int a = 10, b = 0, c = 2;
	printf("请分析运行结果:\n");
	b = a += a;
	printf("(1)b = a + = a; a = %d b = %d \n\n", a, b);
	a = 10;
	b = 3;
	printf("(2)a > b > c \n\n", a > b > c);
	a = 10;
	b = a *= 2 + 5;
	printf("(3)a* = 2 + 5; a = %d b = %d \n\n", a, b);
	a = 10;
	b = a %= 4;
	printf("(4)b = a% % = 4; a = %d b = %d \n\n", a, b);
	a = 10;
	b = a += a -= a *= a;
	printf("(5)b = a+ = a; a = %d b = %d \n\n", a, b);
	a = 10;
	b = (a = 10, a %= 5, a + 3);
	printf("(6)b = (a = 10, a% = 5, a + 3); a = %d b = %d \n\n", a, b);
	a = 10;
	b = (a > 10) ? (a = 12) : (a = 15);
	printf("(7)b = a + = a; a = %d b = %d \n\n", a, b);
	a = 10;
	b = a++ + a;
	printf("(8)b = a++ + a; a = %d b = %d \n\n", a, b);
	a = 10;
	b = a++ + a++;
	printf("(9)b = a++ + a++; a = %d b = %d \n\n", a, b);
	return 0;
}