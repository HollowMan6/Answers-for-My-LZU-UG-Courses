// 第十一章 第10题
#include <stdio.h>

#define LIMIT 50

void remove_spaces(char *string);
char * get(char *string, int n);

int main(void)
{

	char string[LIMIT];

	printf("输入一个字符串: ");
	get(string, LIMIT);
	while (string[0] != '\0')
	{
		remove_spaces(string);
		printf("去除空格后的字符串: %s\n", string);

		printf("输入一个字符串 (空白退出): ");
		get(string, LIMIT);
	}
	puts("再见");

	return 0;
}

void remove_spaces(char *string)
{

	unsigned long spaces_found = 0;

	while (1)
	{
		if (*string == ' ')
			spaces_found++;
		else
			*(string - spaces_found) = *string;

		if (*string == '\0')
			break;

		string++;
	}
}

char * get(char *string, int n)
{

	char *return_value = fgets(string, n, stdin);

	while (*string != '\0')
	{
		if (*string == '\n')
		{
			*string = '\0';
			break;
		}

		string++;
	}

	return return_value;
}
