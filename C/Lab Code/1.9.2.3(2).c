int Root(double a,double b,double c,double r[2][2])
{
	double disc;
	disc = b*b - 4*a*c;
	if(fabs(a)<0.00001)
	{
		if(fabs(b)>0.00001)
		{
			r[0][0] = -c/b;
			return 4;
		}
		else if(fabs(c)<0.00001)
			return 5;
		else return 0; 
	}
	else
	{
		if(disc>0.00001)
		{
			r[0][0] = (-b+sqrt(disc))/(2*a);
			r[1][0] = (-b-sqrt(disc))/(2*a);
			return 2;
		}
		else if(fabs(disc)<0.00001)
		{
			r[0][0] = -b/(2*a);
			r[1][0] = -b/(2*a);
			return 1;
		}
		else
		{
			r[0][0] = -b/(2*a);
			r[0][1] = (sqtr(-disc))/(2*a);
			r[1][0] = -b/(2*a);
			r[1][1] = (sqrt(-disc))/(2*a);
			return 3;
		}
	}
}
void show(int num,double x[2][2])
{
	switch(num)
	{
		case 1:
			printf("X1 = X2 = %lf\n",x[0][0]);
			break;
		case 2:
			printf("X1 = %lf, X2 = %lf\n",x[0][0],x[1][0]);
			break;
		case 3:
			printf("X1 = %lf + %lfi, X2 = %lf - %lfi\n",x[0][0],x[0][1],x[1][0],x[1][1]);
			break;
		case 4:
			printf("该一元二次方程有一个根 x = %lf\n",x[0][0]);
			break;
		case 5:
			printf("该一元二次方程有无穷根\n");
			break;
		case 0:
			printf("方程无根\n");
	}
}
