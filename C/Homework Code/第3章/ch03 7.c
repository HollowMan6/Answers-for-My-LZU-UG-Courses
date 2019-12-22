// 编程练习 第三章 第7题
#include <stdio.h>
int main(void)
{
	float CM_PER_INCH = 2.54;
	float height;

	printf("输入你的英尺身高: ");
	scanf("%f", &height);
	printf("你总共 %f 厘米高.\n", height * CM_PER_INCH);

	return 0;
}