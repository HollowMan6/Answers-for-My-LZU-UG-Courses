#include<stdio.h>
#include<string.h>
void string_sort(char *[],int);
void string_out(char *[],int);
int main(void)
{
	char *days[7]={"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
	string_sort(days,7);
	string_out(days,7);	
}
void string_sort(char *string[],int n)
{
	char *temp;
	int i,j;
	for(i=1;j<n-i-1;j++)
	{
		for(j=0;j<n-1;j++)
		if(strcmp(string[j],string[j+1])>0)
		{
			temp=string[j];
			string[j]=string[j+1];
			string[j+1]=temp;
		}
	}
}

void string_out(char *string[],int n)
{
	int i;
	for(i=0;i<n;i++)
		printf("%s",string[i]);
}
