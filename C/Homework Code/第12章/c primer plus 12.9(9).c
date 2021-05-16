//第12章 第9题
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

#define LIMIT 400

char ** get_words(int n);
static void find_word(char **start, char **end);

int main(void)
{
	int n;
	char ** words;

	printf("How many words do you wish to enter?\n");
	if (scanf("%d", &n) == 1 && n > 0)
	{
		while (getchar() != '\n')
			continue;
		words = get_words(n);

		printf("Here are your words:\n");
		for (int i = 0; i < n; i++)
			printf("%s\n", words[i]);
	}

	return 0;
}

char ** get_words(int n)
{
	char tmp[LIMIT];
	int i;
	char * word_start;
	char * word_end;
	int word_length;

	char ** word_array = (char **) malloc(n * sizeof(char *));

	printf("Enter %d words now:\n", n);
	word_start = fgets(tmp, LIMIT, stdin);

	for (i = 0; i < n; i++)
	{
		find_word(&word_start, &word_end);
		
		if (*word_start == '\0')
			break;

		word_length = word_end - word_start;
		word_array[i] = (char *) malloc((word_length + 1) * sizeof(char));
		if (word_array[i] != NULL)
		{
			strncpy(word_array[i], word_start, word_length);
			word_array[i][word_length] = '\0';
		}
		
		word_start = word_end;
	}

	if (i < n)
		for (; i < n; i++)
		{	
			word_array[i] = (char *) malloc(sizeof(char));
			*word_array[i] = '\0';
		}

	return word_array;
}

static void find_word(char **start, char **end)
{

	while (isspace(**start))
		(*start)++;

	*end = *start;

	while (!isspace(**end) && **end != '\0')
		(*end)++;
}