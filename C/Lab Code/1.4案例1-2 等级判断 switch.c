#include<stdio.h>
main()
{
	float score;
	int n;
	char grade;
	printf("\ninput a student score:");
	scanf("%f",&score);
	if(score < 0||score > 100)
	  printf("\ninput error!\n");
	else
	{
		n = score/10;
		switch(n)
		{
			case 10:
			case 9: grade = 'A';break;
			case 8: grade = 'B';break;
			case 7: grade = 'C';break;
			case 6: grade = 'D';break;
			default: grade = 'E';
		}
		printf("\nthe student score:%c\n",grade);
	}
}
