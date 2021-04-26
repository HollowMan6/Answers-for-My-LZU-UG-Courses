/*	process using time  */
#include<stdio.h>
#include<stdlib.h>
#include<sys/times.h>
#include<time.h>
#include<unistd.h>

void time_print(char *,clock_t);

int main(void)
{
		clock_t start, end;
		struct tms t_start, t_end;
		start = times(&t_start);
		double i=0;
		int j=0,x=0,y=0,z=0;
		while (i<=100000&&j<=100000)
		{
			i++;
			j++;
			z++;
			x=y/z;
			z++;
			x++;
			y++;
			int k;
			for(k=0;k<10000;k++){
				z+=k;
				x=y/z;
			}
		}
/*找到了输出到dev//null，寻找错误输出到dev//null2*/
		end=times(&t_end);

		time_print("elapsed",end-start);
		puts("parent times");
		time_print("\tuser CPU",t_end.tms_utime); /*进程花在执行用户模式代码上的时间*/
		time_print("\tsys CPU",t_end.tms_stime); /*进程花在执行内核代码上的时间*/


		puts("child times");
		time_print("\tuser CPU",t_end.tms_cutime); /*子进程花在执行用户模式代码上的时间*/
		time_print("\tsys CPU",t_end.tms_cstime); /*子进程花在执行内核代码上的时间*/


		exit(EXIT_SUCCESS);
}

void time_print(char *str, clock_t time)
{
		long tps = sysconf(_SC_CLK_TCK);
		printf("%s: %6.2f secs\n",str,(float)time/tps);//计算秒数
}
