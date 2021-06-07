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
			sendto(sock_fd, buffer, size, 0, (struct sockaddr*)&server_addr, len);
			bzero(buffer, BUF_SIZE);
		}
	}
	close(sock_fd);
	return 0;
}
