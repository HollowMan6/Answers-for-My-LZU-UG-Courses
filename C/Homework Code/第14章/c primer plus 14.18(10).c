//第14章 第10题
#include <stdio.h>
#include <math.h>
#define NOPTIONS 5

void menu(void)
{
	puts("输入你的选择:");
	puts("a) 加");
	puts("b) 减");
	puts("c) 乘");
	puts("d) 除");
	puts("e) 次方");
	puts("f) 退出");
	return;
}

double sum(double x, double y)
{
	return x + y;
}

double difference(double x, double y)
{
	return x - y;
}

double power(double x, double y)
{
	return pow(x, y);
}

double product(double x, double y)
{
	return x * y;
}

double quotient(double x, double y)
{
	return x / y;
}

int main(void)
{
	double (*funcs[NOPTIONS])(double, double) = {sum, difference, product, quotient, power};
	double x, y;
	int ch;
	
	while (1)
	{
		menu();
		if ((ch = getchar()) != '\n')
			while (getchar() != '\n') continue;
		ch -= 'a';
		if (ch < 0 || ch > 5)
		{
			puts("无效选项，请重试.");
			continue;
		}
		else if (ch == 5)
			break;
		
		printf("输入两个以空格隔开的字母: ");
		while(scanf("%lf %lf", &x, &y) != 2)
		{
			while (getchar() != '\n') continue;
			printf("无效输入. 请重新输入两个数字: ");
		}
		while (getchar() != '\n') continue;

		printf("%f\n", funcs[ch](x, y));
		puts("");
	}
	puts("再见.");
}



