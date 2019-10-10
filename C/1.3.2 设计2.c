#include<math.h>
#include<stdio.h>
main()
{
	double r, h, c, s, sb, v, zv;
	double p = 3.14;
	printf("input r, h\n");
	scanf("%lf, %lf", &r, &h);
	c = 2 * p * r;
	s = p * r * r;
	sb = 4 * p * r * r;
	v = p * r * r * r * 4 / 3;
	zv = p * r * r * h;
	printf("%.2f %.2f %.2f %.2f %.2f ", c, s, sb, v, zv);
}