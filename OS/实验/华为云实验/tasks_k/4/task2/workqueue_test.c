#include <linux/module.h>
#include <linux/workqueue.h>
#include <linux/delay.h>

MODULE_LICENSE("GPL");
static struct workqueue_struct *queue = NULL;
static struct delayed_work mywork;
static int i = 0;

//work handle
void work_handle(struct work_struct *work)
{
	printk(KERN_ALERT "Hello World!\n");
}

static int __init timewq_init(void)
{
	printk(KERN_ALERT "Start workqueue_test module.");
	queue = create_singlethread_workqueue("workqueue_test");
	if(queue == NULL){
		printk(KERN_ALERT "Failed to create workqueue_test!\n");
		return -1;
	}
	INIT_DELAYED_WORK(&mywork, work_handle);
	for(;i <= 3; i++){
		queue_delayed_work(queue, &mywork, 5 * HZ);
		ssleep(15);
	}
	return 0;
}

static void __exit timewq_exit(void)
{
	flush_workqueue(queue);
	destroy_workqueue(queue);
	printk(KERN_ALERT "Exit workqueue_test module.\n");
}

module_init(timewq_init);
module_exit(timewq_exit);
