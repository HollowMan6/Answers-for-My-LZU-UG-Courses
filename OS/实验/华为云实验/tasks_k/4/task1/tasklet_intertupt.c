#include <linux/module.h>
#include <linux/interrupt.h>

MODULE_LICENSE("GPL");

static struct tasklet_struct my_tasklet;

static void tasklet_handler(unsigned long data)
{
	printk("Hello World! tasklet is working...\n");
}

static int __init mytasklet_init(void)
{
	printk("Start tasklet module...\n");
	tasklet_init(&my_tasklet, tasklet_handler, 0);
	tasklet_schedule(&my_tasklet);
	return 0;
}

static void __exit mytasklet_exit(void)
{
	tasklet_kill(&my_tasklet);
	printk("Exit tasklet module...\n");
}

module_init(mytasklet_init);
module_exit(mytasklet_exit);
