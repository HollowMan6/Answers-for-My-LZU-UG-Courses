#include <linux/module.h>
#include <linux/time.h>

MODULE_LICENSE("GPL");

#define NUM 100000
struct timeval tv;

static long sum(int num)
{
	int i;
	long total = 0;
	for (i = 1; i <= NUM; i++)
		total = total + i;
	printk("The sum of 1 to %d is: %ld\n", NUM, total);
	return total;
}

static int __init sum_init(void)
{
	int start;
	int start_u;
	int end;
	int end_u;
	long time_cost;
	long s;

	printk("Start sum_time module...\n");
	do_gettimeofday(&tv);
	start = (int)tv.tv_sec;
	start_u = (int)tv.tv_usec;
	printk("The start time is: %d s %d us \n", start, start_u);

	s = sum(NUM);

	do_gettimeofday(&tv);
	end = (int)tv.tv_sec;
	end_u = (int)tv.tv_usec;
	printk("The end time is: %d s %d us \n", end, end_u);
	time_cost = (end - start) * 1000000 + end_u - start_u;
	printk("The cost time of sum from 1 to %d is: %ld us \n", NUM, time_cost);
	return 0;
}

static void __exit sum_exit(void)
{
	printk("Exit sum_time module...\n");
}

module_init(sum_init);
module_exit(sum_exit);
