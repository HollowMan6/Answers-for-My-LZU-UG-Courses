// 第十一章 第15题
#include <stdio.h>
#include <ctype.h>

int my_atoi(char *string);

int main(int argc, char *argv[])
{

	int input;

	if (argc != 2)
		puts("输入格式错误！");
	else
	{
		input = my_atoi(argv[1]);
		printf("%d + 5 = %d\n", input, input + 5);
		printf("%d / 2 = %d\n", input, input / 2);
		printf("%d %% 3 = %d\n", input, input % 3);
	}

	return 0;
}

int my_atoi(char *string)
{
	int total = 0;

	while (*string != '\0')
	{
		if (!isdigit(*string))
			return 0;
		else
		{
			total *= 10; 
			total += *string - '0';
		}

		string++;
	}

	return total;
}