//第五章 第3题
#include<stdio.h>
const int DAYS_PER_WEEK = 7;
int main(void)
{
	int days;

	printf("输入天数(或者输入 0 退出): ");
	scanf("%d", &days);
	while (days > 0)
	{
		printf("%d days are %d weeks, %d days.\n", days, days / DAYS_PER_WEEK,
			days % DAYS_PER_WEEK);

		printf("输入天数(或者输入 0 退出): ");
		scanf("%d", &days);
	}

	return 0;
}