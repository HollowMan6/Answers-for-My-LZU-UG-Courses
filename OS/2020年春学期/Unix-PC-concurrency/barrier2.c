#include <pthread.h>
#include <stdlib.h>
#include <stdio.h>
#define SIZE 8000000L   //数组长度
#define NUM_THREAD 8 //线程个数
#define SIZE_PER (SIZE/NUM_THREAD)//每个线程要处理的数组长度
pthread_barrier_t barrier;//定义屏障
int *a;
/*每个线程的线程处理函数*/
void * thr_fun(void *arg)
{	long n = (long)arg;		
long i;
	for(i=n;i<n+SIZE_PER;i++) 
	{		a[i] = i;			
	}	
	printf("thread %ld done job.\n",(long)pthread_self());	
	pthread_barrier_wait(&barrier);	
	return ((void *)1);
} 
int main()
{	pthread_t tid;	
struct timeval start,end;	
long long startusec,endusec;	
double elapsed;	
int i;	
a = (int *)malloc(SIZE*sizeof(int));  //动态分配数组	
pthread_barrier_init(&barrier,NULL,NUM_THREAD+1);//初始化线程屏障计数为子线程个数加上主线程	
gettimeofday(&start,NULL);//获得起始时间	
for(i=0;i<NUM_THREAD;i++)	
{		pthread_create(&tid,NULL,thr_fun,(void *)(i*SIZE_PER));//创建子线程	
}
pthread_barrier_wait(&barrier);//等待所有子线程处理完成	
gettimeofday(&end,NULL);//获得结束时间	
for(i=0;i<SIZE;i++)//打印数组内容	//
printf("%d ",a[i]);		
startusec = start.tv_sec * 1000000 +  start.tv_usec;
	endusec = end.tv_sec * 1000000 + end.tv_usec;	
	elapsed = (double)(endusec-startusec)/1000000.0;//计算处理所花费的时间	
	printf("sort took %.4f seconds\n",elapsed); 
	return 0;

}
