#include <linux/module.h>
#include <linux/fs.h>

MODULE_LICENSE("GPL");

static struct file_system_type myfs_type = {
	.name	= "myfs",
	.owner	= THIS_MODULE,
};
MODULE_ALIAS_FS("myfs");

static int __init register_newfs_init(void)
{
	printk("Start register_newfs module...\n");
	return register_filesystem(&myfs_type);
}

static void __exit register_newfs_exit(void)
{
	printk("Exit register_newfs module...\n");
	unregister_filesystem(&myfs_type);
}

module_init(register_newfs_init);
module_exit(register_newfs_exit);
