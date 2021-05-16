// 第八章 第3题
#include <stdio.h>
#include <ctype.h>

int main(void)
{
	int ch;
	int uppercase_count = 0, lowercase_count = 0, other_count = 0;

	while ((ch = getchar()) != EOF)
	{
		if (isupper(ch))
			uppercase_count++;
		else if (islower(ch))
			lowercase_count++;
		else
			other_count++;
	}

	printf("字符个数\n");
	printf("大写字母: %d\n", uppercase_count);
	printf("小写字母: %d\n", lowercase_count);
	printf("其它: %d\n", other_count);

	return 0;
}