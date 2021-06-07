#include <stdio.h>
#include <string.h>
#include <sys/socket.h>
#include <arpa/inet.h>
#include <unistd.h>

#define PORT		40000
#define BUF_SIZE        1024

int main(void)
{
	int sock_fd;
	char buffer[BUF_SIZE];
	char rrbuf[28];
	int size;
	int len;
	int ret;

	struct sockaddr_in server_addr;

	if(-1 == (sock_fd = socket(AF_INET, SOCK_DGRAM, IPPROTO_IP)) ){
		printf("Failed to create a socket!\n");
		return 0;
	}

	//server infomation
	memset(&server_addr, 0, sizeof(server_addr));
	server_addr.sin_family = AF_INET;
	server_addr.sin_port = htons(PORT);
	server_addr.sin_addr.s_addr = inet_addr("127.0.0.1");
	bzero(buffer, BUF_SIZE);
	len = sizeof(server_addr);

	//read from stdin and send to server
	while(1){
		printf("Please enter the content to be sent:\n");
		size = read(0, buffer, BUF_SIZE);
		if(size){
			bzero(rrbuf,sizeof(rrbuf));
			rrbuf[0] = 0x07;
			rrbuf[1] = sizeof(rrbuf) - 1;
			rrbuf[2] = 4;
			rrbuf[sizeof(rrbuf) - 1] = 0;
			
			ret = setsockopt(sock_fd,IPPROTO_IP,IP_OPTIONS,(void*)rrbuf,sizeof(rrbuf));
			if(-1 == ret){
				printf("setsockopt error!\n");
				return 0;
			}

			sendto(sock_fd, buffer, size, 0, (struct sockaddr*)&server_addr, len);
			bzero(buffer, BUF_SIZE);
		}
	}
	close(sock_fd);
	return 0;
}
