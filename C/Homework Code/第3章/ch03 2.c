// 编程练习 第三章 第2题
#include <stdio.h>
int main(void)
{
	int ascii_code;
	printf("输入ASCII码值: ");
	scanf("%d", &ascii_code);
	printf("输入的ASCII码 %d 对应的字符: %c\n", ascii_code, ascii_code);

	return 0;
}
