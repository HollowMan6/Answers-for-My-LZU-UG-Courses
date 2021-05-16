// 第十一章 第7题
#include <stdio.h>

#define LIMIT 50

char * mystrcpy(char *s1, char *s2, int n);
char * get(char *string, int n);
void clear_string(char * string, int n);

int main(void)
{
	char s1[LIMIT];
	char s2[LIMIT];
	int n;

	printf("输入要复制的字符串: ");
	get(s2, LIMIT);
	while (s2[0] != '\0')
	{
		printf("你想要复制多少字母? (最大 %d) ", LIMIT);
		scanf("%d", &n);

		while (getchar() != '\n')
			continue;

		if (n > LIMIT)
			n = LIMIT;

		printf("原字符串: %s\n", s2);
		mystrcpy(s1, s2, n);
		printf("复制: %s\n", s1);

		clear_string(s1, LIMIT);

		printf("输入要复制的字符串 (空白退出): ");
		get(s2, LIMIT);
	}

	puts("再见");

	return 0;
}

char * mystrcpy(char *s1, char *s2, int n)
{

	int i = 0;

	while (s2[i] != '\0' && i < n)
	{
		s1[i] = s2[i];
		i++;
	}

	while (i < n)
	{
		s1[i] = '\0';
		i++;
	}

	return s1;
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

void clear_string(char *string, int n)
{

	for (int i = 0; i < n; i++)
		string[i] = '\0';
}