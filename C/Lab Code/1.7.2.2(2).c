#include<stdio.h>
int maxword(char word[256], int wordp[100], int m, int i, int b, int count)
{
	gets(word);
	while (word[i] != '\0')
	{
		i++;
		if (word[i] == ' ')
		{
			wordp[m] = i;
			m++;
		}
		if (word[i] == '\0')
			break;
	}
	for (; m >= 0; m--)
	{
		b = wordp[0];
		if (b < wordp[m + 1] - wordp[m])
		{
			i = m;
			b = wordp[m + 1] - wordp[m];
		}
	}
	return i;
}
int main(void)
{
	char word[256];
	int wordp[100];
	int m, i, b, count;
	i = 0;
	count = 1;
	b = 0;
	m = 0;
	maxword(word, wordp, m, i, b, count);
	for (; i <= wordp[i + 1]; i++)
		printf("%c", word[i]);
}
