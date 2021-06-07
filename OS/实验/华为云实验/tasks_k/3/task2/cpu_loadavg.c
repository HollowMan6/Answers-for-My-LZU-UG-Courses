#include <linux/module.h>
#include <linux/fs.h>

MODULE_LICENSE("GPL");

char tmp_cpu_load[5] = {'\0'};

static int get_loadavg(void)
{
	struct file *fp_cpu;
	loff_t pos = 0;
	char buf_cpu[10];
	fp_cpu = filp_open("/proc/loadavg", O_RDONLY, 0);
	if (IS_ERR(fp_cpu)){
		printk("Failed to open loadavg file!\n");
		return -1;
	}
	kernel_read(fp_cpu, buf_cpu, sizeof(buf_cpu), &pos);
	strncpy(tmp_cpu_load, buf_cpu, 4);
	filp_close(fp_cpu, NULL);
	return 0;
}

static int __init cpu_loadavg_init(void)
{
	printk("Start cpu_loadavg!\n");
	if(0 != get_loadavg()){
		printk("Failed to read loadarvg file!\n");
		return -1;
	}
	printk("The cpu loadavg in one minute is: %s\n", tmp_cpu_load);
	return 0;
}

static void __exit cpu_loadavg_exit(void)
{
	printk("Exit cpu_loadavg!\n");
}

module_init(cpu_loadavg_init);
module_exit(cpu_loadavg_exit); 
