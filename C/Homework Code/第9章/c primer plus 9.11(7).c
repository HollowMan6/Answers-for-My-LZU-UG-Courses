// 第九章 第7题
#include <stdio.h>
#include <ctype.h>

int letter_location(char ch);

int main(void)
{
	char ch;
	int location;

	while ((ch = getchar()) != EOF)
	{
		if ((location = letter_location(ch)) == -1)
			printf("%c 不是一个字母\n", ch);
		else
			printf("%c 是一个字母: 位置为 %d\n", ch, location);
	} 

	return 0;
}

int letter_location(char ch)
{
	if (isalpha(ch))
	{
		ch = tolower(ch);
		return (ch - 'a' + 1);
	}
	else
		return -1;
}