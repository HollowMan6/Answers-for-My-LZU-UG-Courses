// 第十一章 第8题
#include <stdio.h>

#define LIMIT 50

char * string_in(char *string, char *substring);
char * get(char *string, int n);

int main(void)
{

	char string[LIMIT];
	char substring[LIMIT];

	char *substr_loc;

	printf("输入一个字符串: ");
	get(string, LIMIT);
	while (string[0] != '\0')
	{
		printf("输入另外一个需要搜索的字符串: ");
		get(substring, LIMIT);

		substr_loc = string_in(string, substring);

		if (substr_loc == NULL)
			printf("%s 不在 %s 中\n", substring, string);
		else
			printf("%s 在 %s 中第 %lu 个位置\n",
				   substring, string, substr_loc - string);

		printf("输入一个字符串 (空白退出): ");
		get(string, LIMIT);
	}

	puts("再见");

	return 0;
}

char * string_in(char *string, char *substring)
{
	int i;

	while (*string != '\0')
	{
		i = 0;

		while (*(string + i) == *(substring + i))
		{
			i++;

			if (*(substring + i) == '\0')
				return string;
		}

		string++;
	}

	return NULL;
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