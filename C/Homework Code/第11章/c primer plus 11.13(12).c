// 第十一章 第1题
#include <stdio.h>
#include <ctype.h>
#include <stdbool.h>

int main(void)
{
	char ch;
	int upcase_letters, lowcase_letters, punct_chars, digits, words;
	upcase_letters = lowcase_letters = punct_chars = digits = words = 0;
	bool inword = false;

	while ((ch = getchar()) != EOF)
	{
		if (isalpha(ch))
		{
			if (!inword)
			{
				inword = true;
				words++;
			}

			if (isupper(ch))
				upcase_letters++;
			if (islower(ch))
				lowcase_letters++;
		}
		else if (ispunct(ch))
		{
			punct_chars++;

			if (ch != '-' && ch != '\'')
				inword = false;
		}
		else if (isdigit(ch))
		{
			digits++;
			inword = false;
		}
		else if (isspace(ch))
			inword = false;
	}

	printf("%s个数: %d\n", "小写字母", lowcase_letters);
	printf("%s个数: %d\n", "大写字母", upcase_letters);
	printf("%s个数: %d\n", "标点符号", punct_chars);
	printf("%s个数: %d\n", "数字字符", digits);
	printf("%s个数: %d\n", "单词", words);

	return 0;
}
