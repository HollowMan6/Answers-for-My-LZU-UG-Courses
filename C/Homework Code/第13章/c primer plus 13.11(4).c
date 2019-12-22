//第13章 第4题
#include <stdio.h>
#include <stdlib.h>

int main(int argc, char *argv[])
{
	FILE * fp;
	int ch;

	if (argc == 1)
	{
		printf("用法: %s 文件1 文件2 ...\n", argv[0]);
		exit(EXIT_FAILURE);
	}
	for (int i = 1; i < argc; i++)
	{
		if ((fp = fopen(argv[i], "r")) == NULL)
		{
			fprintf(stderr, "无法打开文件 %s.\n", argv[i]);
			exit(EXIT_FAILURE);
		}

		while ((ch = getc(fp)) != EOF)
			putc(ch, stdout);

		fclose(fp);
	}

	return 0;
}