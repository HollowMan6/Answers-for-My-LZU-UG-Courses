// 第十一章 第1题
#include <stdio.h>
#include <string.h>

#define SIZE 20

char * sgetnchar(char *array, int n);

int main(void)
{
	char hello[SIZE] = "Hello, ";
	int space = SIZE - strlen(hello) - 1;

	printf("What's your name? (输入 %d 个字母)\n", space);
	sgetnchar(hello + 7, space);
	puts(hello);

	return 0;
}

char * sgetnchar(char *array, int n)
{
	char ch;

	for (int i = 0; i < n; i++)
	{
		if ((ch = getchar()) == EOF)
			break;

		*(array + i) = ch;
	}

	return array;
}