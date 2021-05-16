#include<stdio.h>
#include<string.h>
int main()
{
	char s[20],str[3][20];
	int i;
	
	for(i=0;i<3;i++)
		gets(str[i]);
	strcpy(s,strcmp(str[0],str[1])<0?str[0]:str[1]);
	if(strcmp(str[2],s)<0)
		strcpy(s,str[2]);
	printf("%s\n",s);
	
	return 0;
}
