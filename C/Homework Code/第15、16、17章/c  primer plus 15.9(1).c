#include <stdio.h>
int btoh(char * str)
{
	int h = 0;
	while (*str != '\0')
	{
		h <<= 1;
		if (*str == '1')
			h |= 1;
		str++;
	}
	return h;
}

int main(void)
{
	char * str = "01001001";
	printf("%s 的十进制形式是 %d.\n", str, btoh(str));
	system("pause");
	return 0;
}