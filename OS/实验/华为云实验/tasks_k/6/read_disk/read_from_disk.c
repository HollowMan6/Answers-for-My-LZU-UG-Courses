#include <linux/module.h>
#include <linux/fs.h>
#include <linux/rtc.h>

#define buf_size 1024
#define read_times 524288

MODULE_LICENSE("GPL");

struct timeval tv;

static int __init read_disk_init(void)
{
	struct file *fp_read;
	char buf[buf_size];
	int i;
	int read_start_time;
	int read_start_time_u;
	int read_end_time;
	int read_end_time_u;
	int read_time;
	loff_t pos;
	printk("Start read_from_disk module...\n");
	fp_read = filp_open("/home/tmp_file", O_RDONLY, 0);
	if (IS_ERR(fp_read)) {
		printk("Failed to open file...\n");
		return -1;
	}

	do_gettimeofday(&tv);
	read_start_time = (int)tv.tv_sec;
	read_start_time_u = (int)tv.tv_usec;
	pos = 0;
	for(i = 0; i < read_times; i++)	{
		kernel_read(fp_read, buf, buf_size, &pos);
	}
	do_gettimeofday(&tv);
	read_end_time = (int)tv.tv_sec;
	read_end_time_u = (int)tv.tv_usec;
	filp_close(fp_read, NULL);
	read_time = (read_end_time - read_start_time)  * 1000000 + (read_end_time_u - read_start_time_u);
	printk(KERN_ALERT "Read file costs %d us\n", read_time);	
	printk("Reading speed is %d M/s\n", buf_size * read_times / read_time);
	return 0;
}

static void __exit read_disk_exit(void)
{
	printk("Exit read_from_disk module...\n");
}

module_init(read_disk_init);
module_exit(read_disk_exit);
