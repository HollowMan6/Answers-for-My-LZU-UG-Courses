#include <stdio.h>
#include <string.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <arpa/inet.h>
#include <unistd.h>

#define	PORT		40000
#define	BUF_SIZE	1024

int main(void)
{
	int sock_fd;
	int len;
	char buffer[BUF_SIZE];
	struct sockaddr_in server_addr, client_addr;
	if(-1 == (sock_fd = socket(AF_INET, SOCK_DGRAM, 0)) )
	{
		printf("Failed to create a socket!\n");
		return 0;
	}
	//server information
	memset(&server_addr, 0, sizeof(server_addr));
	server_addr.sin_family = AF_INET;
	server_addr.sin_port = htons(PORT);
	server_addr.sin_addr.s_addr = htonl(INADDR_ANY);
	if(-1 == bind(sock_fd, (struct sockaddr*)&server_addr, sizeof(server_addr)))
	{
		printf("Failed to bind the socket!\n");
		return 0;
	}
	len = sizeof(client_addr);
	
	//rec and print
	while(1)
	{
		bzero(buffer, BUF_SIZE);
		if(-1 != (recvfrom(sock_fd, buffer, BUF_SIZE, 0, (struct sockaddr*)&client_addr, &len)) )
		{
			printf("The message received is: %s", buffer);
		}
	}
	return 0;
}
