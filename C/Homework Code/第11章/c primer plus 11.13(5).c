// 第十一章 第5题
#include <stdio.h>

#define LIMIT 50

char * findchar(char *str, const char ch);

int main(void)
{
	char string[LIMIT];
	char *chloc;
	char ch;

	printf("输入一个要进行搜索的字符串: ");
	fgets(string, LIMIT, stdin);
	while (string[0] != '\n')
	{
		printf("输入一个要搜索的字符: ");
		ch = getchar();

		if (ch != '\n')
			while (getchar() != '\n')
				continue;

		chloc = findchar(string, ch);
		if (chloc == NULL)
			printf("字符 %c 在 %s 中未找到", ch, string);
		else
			printf("字符 %c 在 %s 中首次出现在第 %lu 位", ch, string, chloc - string);

		printf("输入一个要进行搜索的字符串 (空白退出): ");
		fgets(string, LIMIT, stdin);
	}

	puts("再见");

	return 0;
}

char * findchar(char *str, const char ch)
{
	while (*str != '\0')
	{
		if (*str == ch)
			return str;
		str++;
	}

	return NULL;
}
