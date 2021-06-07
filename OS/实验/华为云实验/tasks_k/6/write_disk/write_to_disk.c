#include <linux/module.h>
#include <linux/fs.h>
#include <linux/rtc.h>

#define buf_size 1024
#define write_times 524288

MODULE_LICENSE("GPL");

struct timeval tv;

static int __init write_disk_init(void)
{
	struct file *fp_write;
	char buf[buf_size];
	int i;
	int write_start_time;
	int write_start_time_u;
	int write_end_time;
	int write_end_time_u;
	int write_time;
	loff_t pos;
	printk("Start write_to_disk module...\n");
	for(i = 0; i < buf_size; i++)
	{
		buf[i] = i + '0';
	}
	fp_write = filp_open("/home/tmp_file", O_RDWR | O_CREAT,0644);
	if (IS_ERR(fp_write)) {
		printk("Failed to open file...\n");
		return -1;
	}
	pos = 0;
	do_gettimeofday(&tv);
	write_start_time = (int)tv.tv_sec;
	write_start_time_u = (int)tv.tv_usec;
	for(i = 0; i < write_times; i++) {
		kernel_write(fp_write, buf, buf_size, &pos);
	}
	do_gettimeofday(&tv);
	write_end_time = (int)tv.tv_sec;
	write_end_time_u = (int)tv.tv_usec;
	filp_close(fp_write, NULL);
	write_time = (write_end_time - write_start_time)  * 1000000 + (write_end_time_u - write_start_time_u);
	printk(KERN_ALERT "Writing to file costs %d us\n", write_time);	
	printk("Writing speed is %d M/s\n", buf_size * write_times / write_time);
	return 0;
}

static void __exit write_disk_exit(void)
{
	printk("Exit write_to_disk module...\n");
}

module_init(write_disk_init);
module_exit(write_disk_exit);
