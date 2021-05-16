#include <pthread.h>
#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <errno.h>
#include <sys/timeb.h> 
static int num1 = 0;
static int num2 = 0;
static int count1 = 10000000;
static int count2 = 20000000;
static pthread_barrier_t barrier; 
void Perror(const char *s)
{    perror(s);    
     exit(EXIT_FAILURE);
} 
long long getSystemTime() 
{    struct timeb t;    
     ftime(&t);    
	 return 1000 * t.time + t.millitm;
} 
void* fun2(void *arg)
{    pthread_t thread_id = pthread_self();    
     printf("the thread2 id is %ld\n", (long)thread_id);    
	 int i = 1;    
	 long long t1 = getSystemTime();    
	 for (; i<=count2; ++i) 
	 {     num2 += 1; 
	 }    
	 long long t2 = getSystemTime();    
	 printf("The thread2 num2 is %d, pay %lld ms\n", num2, (t2-t1));    
	 pthread_barrier_wait(&barrier);
} 

int main()
{    int err; 
     pthread_t thread1; 
	 pthread_t thread2;    
     const int thread_num = 2;     
	 thread1 = pthread_self();    
	 printf("the thread1 id is %ld\n", (long)thread1);     
	 //init    
	 pthread_barrier_init(&barrier, NULL, thread_num);     
	 // Create thread    
	 err = pthread_create(&thread2, NULL, fun2, NULL);    
	 if (err != 0) 
	 {        Perror("can't create thread2\n");    
	 }    
	 err = pthread_detach(thread2);    
	 if (err != 0) 
	 {        Perror("can't detach thread2\n");    
	 }     
	 int i = 1;    
	 long long t1 = getSystemTime();    
	 for (; i<=count1; ++i) 
	 {        num1 += 1;   
	 }    
	 long long t2 = getSystemTime();    
	 printf("The thread1 num1 is %d, pay %lld ms\n", num1, (t2-t1));     
	 pthread_barrier_wait(&barrier);    
	 long long t3 = getSystemTime();    
	 printf("the thread1 get SERIAL, num1+num2=%d, pay %lld ms\n", num1+num2, t3-t1);     
	 pthread_barrier_destroy(&barrier);    
	 return 0;
}
