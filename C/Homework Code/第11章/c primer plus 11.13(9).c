// 第十一章 第9题
#include <stdio.h>

#define LIMIT 50

void reverse_string(char *string);
char * get(char *string, int n);

int main(void)
{

	char string[LIMIT];

	printf("输入一个需要反转的字符串: ");
	get(string, LIMIT);
	while (string[0] != '\0')
	{
		reverse_string(string);
		printf("反转后的字符串: %s\n", string);

		printf("输入一个需要反转的字符串 (空白退出): ");
		get(string, LIMIT);
	}

	puts("再见");
	return 0;
}

void reverse_string(char *start)
{
	char *end = start;
	char tmp;

	while (*(end + 1) != '\0')
		end++;

	while (start < end)
	{
		tmp = *start;
		*start = *end;
		*end = tmp;

		start++;
		end--;
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