//第14章 第2题
#include<stdio.h>
#include<string.h>
struct month
{
	char name[10];
	char abrev[4];
	char monthno[3];
	int days;
};

struct month months[12] = {
	{"January", "JAN", "01", 31},
	{"February", "FEB", "02", 28},
	{"March", "MAR", "03", 31},
	{"April", "APR", "04", 30},
	{"May", "MAY", "05", 31},
	{"June", "JUN", "06", 30},
	{"July", "JUL", "07", 31},
	{"August", "AUG", "08", 31},
	{"September", "SEP", "09", 30},
	{"October", "OCT", "10", 31},
	{"November", "NOV", "11", 30},
	{"December", "DEC", "12", 31}
};

int main(void)
{
    int year, day, i, total;
	char month[10];

    printf("请输入年份: ");
    scanf("%d", &year);
    getchar();
    printf("请输入月份（支持月份号、英文月份名及其缩写，例如 January, JAN , 01）:\n");
	gets(month);
	printf("请输入日期: ");
	scanf("%d", &day);
	for (i = 0; i < 12; i++)
	{
		if (strcmp(month, months[i].name) == 0 ||
			strcmp(month, months[i].abrev) == 0 ||
			strcmp(month, months[i].monthno) == 0)
			break;
	}
	if (i == 12)
		printf("错误！无效月份！");
	if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0)
		months[1].days = 29;
	if (day > months[i].days)
		printf("错误！无效的日期！");
	total = 0;
	for (int j = 0; j < i; j++)
		total += months[j].days;
	total += day;
	printf("从该年开始到这年的这一天一共有 %d 天\n",total);

	return 0;
}