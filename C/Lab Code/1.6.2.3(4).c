#include<stdio.h>
#include<string.h>
int main(void)
{
	int j, i, t;
	t = 0;
	char str[50], strl[10];
	scanf("%s %s\"", str, strl);
	strcat(str, strl);
	for (i = 0; i < 50; i++)
	{
		if (str[i] == '\0')
			break;
		for (j = i + 1; j < 50; j++)
		{
			if (str[j] == '\0')
				break;
			if (str[i] > str[j])
			{
				t = str[i];
				str[i] = str[j];
				str[j] = t;
			}
		}
	}
	printf("str = \"%s\"", str);
}
