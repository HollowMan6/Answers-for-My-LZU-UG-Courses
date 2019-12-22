//第13章 第3题
#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>

#define SLEN 81

void get(char * string, int n);

int main(void)
{
	int ch;
	FILE * fsource;
	FILE * fdest;
	char source[SLEN];
	char dest[SLEN];

	printf("输入读取文件: ");
	get(source, SLEN);
	printf("输入写入文件: ");
	get(dest, SLEN);

	if ((fsource = fopen(source, "r")) == NULL)
	{
		fprintf(stderr, "无法打开并读取文件 %s.\n", source);
		exit(EXIT_FAILURE);
	}
	if ((fdest = fopen(dest, "w")) == NULL)
	{
		fprintf(stderr, "无法打开并写入文件 %s.\n", dest);
		exit(EXIT_FAILURE);
	}
	while ((ch = getc(fsource)) != EOF)
	{
		if (islower(ch))
			ch = toupper(ch);
		putc(ch, fdest);
	}

	fclose(fsource);
	fclose(fdest);

	return 0;
}

void get(char * string, int n)
{
	fgets(string, n, stdin);

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