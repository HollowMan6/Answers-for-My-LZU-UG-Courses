// 第十一章 第1题
#include <stdio.h>
#include <ctype.h>

void map_identity(void);
void map_uppercase(void);
void map_lowercase(void);

int main(int argc, char *argv[])
{
	if (argc == 1)
	{
		map_identity();
		return 0;
	}

	else if (argc > 2 || (argc == 2 && argv[1][0] != '-'))
	{
		printf("输入格式错误！\n");
		return 1;
	}

	else
		switch(argv[1][1])
		{
			case ('p'):
				map_identity();
				break;
			case ('u'):
				map_uppercase();
				break;
			case ('l'):
				map_lowercase();
				break;
			default:
				printf("输入格式错误！\n");
				return 1;
		}

	return 0;
}

void map_identity(void)
{
	char ch;

	while((ch = getchar()) != EOF)
		putchar(ch);
}

void map_uppercase(void)
{
	char ch;

	while((ch = getchar()) != EOF)
	{
		if (islower(ch))
			ch = toupper(ch);
		putchar(ch);
	}
}

void map_lowercase(void)
{
	char ch;

	while((ch = getchar()) != EOF)
	{
		if (isupper(ch))
			ch = tolower(ch);
		putchar(ch);
	}
}