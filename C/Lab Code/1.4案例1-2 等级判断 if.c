#include<stdio.h>
main()
{
	float score;
	char grade;
	printf("\nplease input a student score:");
	scanf("%f",&score);
	if(score > 100||score < 0)
	  printf("\ninput error!\n");
	else
	{
		if(score >= 90)
		  grade = 'A';
		else
		  {
		  	if(score >= 80)
		  	  grade = 'B';
		  	else
		  	{
		  		if(score >= 70)
		  		  grade = 'C';
		  		else
		  		  {
					if(score >= 60)
		  		      grade = 'D';
		  		      else grade = 'E';
		  		  }
		  	}
		  		
		  	}
		  	printf("nthe student grade:%c\n",grade);
		  }  
	}
