//第14章 第5题
#include <stdio.h>
struct Name
{
	char first[20];
	char last[20];
};

struct Student
{
	struct Name name;
	float grades[3];
	float average;
};

void getgrades(struct Student * student)
{

	for (int i = 0; i < 3; i++)
	{
		printf("为学生 %s %s 输入成绩 %d : ", student->name.first,student->name.last,i + 1);
		while (scanf("%f", student->grades+i) != 1)
			while (getchar() != '\n') continue;
		while (getchar() != '\n') continue;
	}
	puts("");
}

void getaverage(struct Student * student)
{

	float total = 0;
	for (int i = 0; i < 3; i++)
		total += student->grades[i];
	student->average = total / 3;
}

void showstudentinfo(struct Student * student)
{
	printf("姓名: %s %s\n", student->name.first, student->name.last);
	printf("成绩1: %.1f\n", student->grades[0]);
	printf("成绩2: %.1f\n", student->grades[1]);
	printf("成绩3: %.1f\n", student->grades[2]);
	printf("平均分: %.1f\n", student->average);
	puts("");
}

int main(void)
{
	struct Student students[4] = {
		{.name = {"A", "B"}},
		{.name = {"C", "D"}},
		{.name = {"E", "F"}},
		{.name = {"G", "H"}}
	};

	for (int i = 0; i < 4; i++)
		getgrades(&students[i]);

	for (int i = 0; i < 4; i++)
		getaverage(&students[i]);

	for (int i = 0; i < 4; i++)
		showstudentinfo(&students[i]);

	return 0;
}