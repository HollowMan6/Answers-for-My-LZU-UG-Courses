// 第八章 第5题
#include <stdio.h>

int main(void)
{
	// initial search parameters
	int upper_bound = 100;
	int lower_bound = 0;
	int guess = 50;

	char ch;

	printf("在1到100间选择一个数，我将猜它的大小。\n");
	printf("输入 y 代表我猜对了, 输入 h 代表我猜大了，输入l则反之.");
	printf("\n你猜的数是 %d 吗?\n", guess);

	while ((ch = getchar()) != 'y')
	{
		while (getchar() != '\n') 
			;
		if (ch == 'h')
			upper_bound = guess;
		else if (ch == 'l')
			lower_bound = guess;
		else
		{
			printf("无效输入，请重试！\n");
			continue;
		}
		guess = (upper_bound + lower_bound) / 2.0;
		printf("是 %d 吗?\n", guess);
	}

	printf("成功了!\n");
	return 0;
}