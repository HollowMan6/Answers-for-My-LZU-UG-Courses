#include<stdio.h>
int main(void){
	char major[50];
	char class[10];
	char name[50];
	printf("请输入专业 班级 姓名:\n");
	scanf("%s %s %s",major,class,name);
	printf("武汉，加油！我是兰州大学 2018级%s专业-%s班-%s\n",major,class,name);
	return 0;
}
