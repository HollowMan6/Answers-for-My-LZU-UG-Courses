//第13章 第7题 a
#include <stdio.h>
#include <stdlib.h>

#define LLEN 70 

void fget(char * string, int n, FILE *file);

int main(int argc, char *argv[])
{
	FILE *file1, *file2;
	char tmp[LLEN];
	int ch;

	if (argc < 3)
	{
		printf("用法: %s 文件1 文件2\n", argv[0]);
		exit(EXIT_FAILURE);
	}

	if ((file1 = fopen(argv[1], "r")) == NULL)
	{
		fprintf(stderr, "无法打开文件 %s.\n", argv[1]);
		exit(EXIT_FAILURE);
	}
	if ((file2 = fopen(argv[2], "r")) == NULL)
	{
		fprintf(stderr, "无法打开文件 %s.\n", argv[2]);
		exit(EXIT_FAILURE);
	}

	while (1)
	{
		fget(tmp, LLEN, file1);
		printf("%-*s\n", LLEN, tmp);
		fget(tmp, LLEN, file2);
		if (feof(file1) && feof(file2))
			break;
	}

	return 0;
}

void fget(char * string, int n, FILE *file)
{
	fgets(string, n, file);

	while (*string != '\0')
	{
		if (*string == '\n')
		{
			*string = '\0';
			break;
		}
		string++;
	}
}
