//第四章 第4题
#include<stdio.h>
int main()
{
	const float INCHES_PER_FEET = 12;
	float height;
	char name[40];

	printf("你的名字是: ");
	scanf("%s", name);
	printf("你的英尺身高是?: ");
	scanf("%f", &height);
	printf("%s, you are %.3f feet tall.\n", name, height / INCHES_PER_FEET);

	return 0;
}