// 第十一章 第6题
#include <stdio.h>

#define LIMIT 50

int is_within(char ch, const char *string);
char * get(char *string, int n);

int main(void)
{

	char string[LIMIT];
	char ch;

	printf("输入一个要进行搜索的字符串: ");
	get(string, LIMIT);
	while (string[0] != '\0')
	{
		printf("输入一个要搜索的字符: ");
		ch = getchar();

		if (ch != '\n')
			while (getchar() != '\n')
				continue;

		char *contains = is_within(ch, string) ? "" : "不";

		printf("\"%s\" 中%s包含 %c\n", string, contains, ch);

		printf("输入一个要进行搜索的字符串 (空白退出): ");
		get(string, LIMIT);
	}
	puts("再见");

	return 0;
}

int is_within(char ch, const char *string)
{

	while(*string != '\0')
	{
		if (*string == ch)
			return 1;

		string++;
	}

	return 0;
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