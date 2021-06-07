#include <linux/kernel.h>
#include <linux/module.h>
#include <linux/stat.h>
 
MODULE_LICENSE("GPL");

static int a = 0;
static int b = 0;
static char * c = "Hello, World";
 
module_param(a, int, 0);
MODULE_PARM_DESC(a, "An invisible int under sysfs");
module_param(b, int, S_IRUSR | S_IWUSR | S_IRGRP | S_IROTH);
MODULE_PARM_DESC(b, "An visible int under sysfs");
module_param(c, charp, S_IRUSR | S_IWUSR | S_IRGRP | S_IROTH);
MODULE_PARM_DESC(c, "An visible string under sysfs");
 
static int __init sysfs_exam_init(void)
{
	printk("Start sysfs_exam module...\n");
	printk("a = %d\n", a);
	printk("b = %d\n", b);
	printk("c = '%s'\n", c);
	return 0; 
}

static void __exit sysfs_exam_exit(void) 
{
	printk("Exit sysfs_exam module...\n");
	printk("a = %d\n", a);
	printk("b = %d\n", b);
	printk("c = '%s'\n", c); 
}
 
module_init(sysfs_exam_init);
module_exit(sysfs_exam_exit);
