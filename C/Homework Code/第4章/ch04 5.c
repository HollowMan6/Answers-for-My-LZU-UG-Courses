//第四章 第5题
#include<stdio.h>
int main(void)
{
	const float BITS_PER_BYTE = 8;
	float download_speed_Mps;
	float file_size_MB;

	printf("输入你的兆位每秒下载速度: ");
	scanf("%f", &download_speed_Mps);
	printf("输入以兆字节为单位的文件大小: ");
	scanf("%f", &file_size_MB);
	printf("At %.2f megabits per second, a file of %.2f megabytes"
		" downloads in %.2f seconds.\n", download_speed_Mps, file_size_MB,
		file_size_MB * BITS_PER_BYTE / download_speed_Mps);

	return 0;
}