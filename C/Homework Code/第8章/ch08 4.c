// 第八章 第4题
#include <stdio.h>
#include <ctype.h>
#include <stdbool.h>

int main(void)
{
	int ch;
	bool in_word = false;
	int letter_count = 0, word_count = 0;

	while ((ch = getchar()) != EOF)
	{
		if (isalpha(ch))
		{
			letter_count++;

			if (!in_word) 
			{
				in_word = true;
				word_count++;
			}
		}

		else 
			in_word = false;
	}

	printf("平均每个单词字母数: %.2f\n", (float) letter_count / word_count);

	return 0;
}