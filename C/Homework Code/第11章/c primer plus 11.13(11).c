// 第十一章 第11题
#include <stdio.h>
#include <string.h>
#include <ctype.h>

#define COUNT 10
#define LIMIT 50

void sort_ASCII(char *strings[], int n);
void sort_length(char *strings[], int n);
int fwlen(char *string);
void sort_firstword_length(char *strings[], int n);
char * get(char *string, int n);
void print_menu(void);

int main (void)
{
	char strings[COUNT][LIMIT];
	char *strptrs[COUNT];
	char * success;
	char ch;

	for (int i = 0; i < COUNT; i++)
		strptrs[i] = strings[i];

	printf("输入最多10个字符串 (EOF 结束): \n");

	for (int i = 0; i < COUNT; i++)
	{
		printf("%d: ", i + 1);
		success = get(strings[i], LIMIT);

		if (!success)
			break;
	}

	printf("\n");

	print_menu();
	while ((ch = getchar()) != 'q')
	{
		if (ch != '\n')
			while (getchar() != '\n')
				continue;

		switch (ch)
		{
			case ('a'):
				sort_ASCII(strptrs, COUNT);
				break;
			case ('l'):
				sort_length(strptrs, COUNT);
				break;
			case ('f'):
				sort_firstword_length(strptrs, COUNT);
				break;
			case ('o'):
				break;
			default:
				printf("Invalid input. Try again.\n\n");
				print_menu();
				continue;
		}

		puts("");
		for (int i = 0; i < COUNT; i++)
			puts(strptrs[i]);

		puts("");
		print_menu();
	}

	puts("Bye");
	return 0;
}

void sort_ASCII(char *strings[], int n)
{

	char *tmp;

	for (int i = 0; i < n - 1; i++)
		for (int j = i + 1; j < n; j++)
		{
			if (strcmp(strings[i], strings[j]) > 0)
			{
				tmp = strings[i];
				strings[i] = strings[j];
				strings[j] = tmp;
			}
		}
}

void sort_length(char *strings[], int n)
{

	char *tmp;

	for (int i = 0; i < n - 1; i++)
		for (int j = i + 1; j < n; j++)
		{
			if (strlen(strings[i]) > strlen(strings[j]))
			{
				tmp = strings[i];
				strings[i] = strings[j];
				strings[j] = tmp;
			}
		}
}

int fwlen(char *string)
{

	int length = 0;

	while (isspace(*string))
		string++;

	while(!isspace(*string))
	{
		length++;
		string++;
	}

	return length;
}

void sort_firstword_length(char *strings[], int n)
{

	char *tmp;

	for (int i = 0; i < n - 1; i++)
		for (int j = i + 1; j < n; j++)
		{
			if (fwlen(strings[i]) > fwlen(strings[j]))
			{
				tmp = strings[i];
				strings[i] = strings[j];
				strings[j] = tmp;
			}
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

void print_menu(void)
{
	puts("菜单:");
	puts("(o) 打印源字符串列表.");
	puts("(a) 以 ASCII 中的顺序打印字符串.");
	puts("(l) 按长度递增的顺序打印字符串.");
	puts("(f) 按字符串中第一个单词的长度打印字符串.");
	puts("(q) 退出.");
	puts("");
	puts("请输入您的选择: ");
}