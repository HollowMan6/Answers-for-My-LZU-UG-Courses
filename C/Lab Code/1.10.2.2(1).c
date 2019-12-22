#include<stdio.h>
#include<stdlib.h>
char *substr(const char *s,int n1,int n2)
{
	char *sp=malloc(sizeof(char)*(n2-n1+2));
	int i,j=0;
	for(i=n1;i<=n2;i++)
		sp[j++]=s[i];
	sp[j]='\0';
	return sp;
}

int main(void)
{
	char s[80],*sub;
	scanf("%s",s);
	sub=substr(s,0,5);
	printf("substr:%s\n",sub);
	free(sub);
	return 0;
}
