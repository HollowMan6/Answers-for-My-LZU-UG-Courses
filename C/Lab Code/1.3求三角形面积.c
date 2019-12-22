#include<math.h>
#include<stdio.h>
main()
{
	double a, b, c, s, area;
	printf("input a, b, c\n");
	scanf("%lf, %lf, %lf", &a, &b, &c);
	s = 1.0/2 * (a + b + c);
	area = sqrt(s * (s - a) * (s - b) * (s - c));
	printf("area = % lf", area);
}
