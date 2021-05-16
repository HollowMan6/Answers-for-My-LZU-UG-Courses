//第六章 第15题
#include <stdio.h>

int main(void)
{
	char line[255];
	int i = 0; // array index
	printf("输入用来倒序输出的一行:\n");
	while (scanf("%c", &line[i]), line[i] != '\n')
		i++;

	for (; 0 <= i; i--)
		printf("%c", line[i]);

	printf("\n");

	return 0;
}
