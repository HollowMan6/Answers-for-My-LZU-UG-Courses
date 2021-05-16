//第13章 第11题
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define LINEMAX 255

int main(int argc, char *argv[])
{
	FILE *fp;
	char line[LINEMAX];

	if (argc != 3)
	{
		fprintf(stderr, "用法: %s <string> <filename>\n", argv[0]);
		exit(EXIT_FAILURE);
	}

	if ((fp = fopen(argv[2], "r")) == NULL)
	{
		fprintf(stderr, "无法打开文件 %s.\n", argv[2]);
		exit(EXIT_FAILURE);
	}

	while (fgets(line, LINEMAX, fp) != NULL)
	{
		if (strstr(line, argv[1]) != NULL)
			fputs(line, stdout);
	}

	fclose(fp);
	return 0;
}