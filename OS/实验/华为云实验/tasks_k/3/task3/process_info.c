#include <linux/module.h>
#include <linux/sched/signal.h>
#include <linux/sched.h>

MODULE_LICENSE("GPL");

struct task_struct *p;

static int __init process_info_init(void)
{
	printk("Start process_info!\n");
	for_each_process(p){
		if(p->state == 0)
			printk("1)name:%s  2)pid:%d  3)state:%ld\n", p->comm, p->pid, p->state);
	}
	return 0;
}

static void __exit process_info_exit(void)
{
	printk("Exit process_info!\n");
}

module_init(process_info_init);
module_exit(process_info_exit); 
