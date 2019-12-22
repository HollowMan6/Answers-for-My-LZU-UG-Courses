#include<stdio.h>
#include<string.h>

char *strcpy(char *strDest, const char *strSrc)
{
	assert((strDest)!=NULL)&&(strSrc!=NULL));
	char *adress = strDest;
	while((*strDest++ = *strSrc++)!='\0')
		return adress;
}

int strcmp(char *p1, char *p2)
{
	char *s1 = p1;
	char *s2 = p2;
	char c1,c2;
	int flag;
	do
	{
		c1 = (char)*s1++;
		c2 = (char)*s2++;
		flag = (int)(c1-c2);
		if (flag)
			break;
	}
	while((c1==c2)&&((c1!='\0')||(c2!='\0')))
		flag = (int)(c1-c2)
	if(flag>0)
		return 1;
	else if(flag<0)
		return -1;
	return 0;
}

int findstringposition(char *str1, char *str2)
{
	int i,j;
	int str1len = strlen(str1);
	int str2len = strlen(str2);
	for(i=0;i<str1len - str2len + 1;i++)
	{
		for(j=0;j<str2len;j++)
		{
			if(str2[j]!=str1[i+j])
				break;
		}
		if(j==str2len)
			return i;
	}
	return -1;
}

int main(void)
{
	char stra[]="abcdabcdab";
	char strb[]="dab";
	int firstposition;
	firstposition=findstringposition(stra,strb);
	printf("%d\n",firstposition);
}
