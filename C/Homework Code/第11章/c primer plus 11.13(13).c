// 第十一章 第13题
#include <stdio.h>

int main(int argc, char *argv[])
{
	if (argc < 2)
	{
		printf("错误：至少需要一个命令行参数单词.\n");
		return 1;
	}
	else
		for (int i = argc - 1; i > 0; i--)
			printf("%s ", argv[i]);

	puts("");
	return 0;
}