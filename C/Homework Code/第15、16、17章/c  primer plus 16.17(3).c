#include <stdio.h>
#include <math.h>

void clearinput(void) 
{
	while (getchar() != '\n') 
		continue;
}

typedef struct polar 
{
	double theta;
	double p;
} Polar;

typedef struct thd
{
	double x;
	double y;
} Thd;

Thd thdfrPolar(Polar cds)
{
	Thd th;

	th.x = cds.p * cos(cds.theta);
	th.y = cds.p * sin(cds.theta);

	return th;
}

int main()
{
	Polar polarcds;
	Thd Thdcds;

	printf("输入 theta 值: ");
	while (scanf("%lf", &polarcds.theta) == 1)
	{
		clearinput();
		printf("输入 p 值: ");
		while (scanf("%lf", &polarcds.p) != 1)
		{
			clearinput();
			printf("输入 p 值: ");
		}

		Thdcds = thdfrPolar(polarcds);

		printf("theta: %.2f  p: %.2f\n", polarcds.theta,
			   polarcds.p);
		printf("x: %.2f  y: %.2f\n", Thdcds.x, Thdcds.y);

		printf("输入 theta 值(任意字母退出):");
	}
	return 0;
}