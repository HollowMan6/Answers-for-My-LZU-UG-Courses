//第五章 第1题
#include <stdio.h>
const int MINUTES_PER_HOUR = 60;
int main(void)
{
	int minutes;

	printf("输入分钟: "); 
	scanf("%d", &minutes);

	while (minutes > 0)
	{
		printf("%d 分钟是 %d 小时和 %d 分钟.\n",
			minutes,
			minutes / MINUTES_PER_HOUR, // hours
			minutes % MINUTES_PER_HOUR); // minutes

		printf("输入分钟: "); 
		scanf("%d", &minutes);
	}

	return 0;
}