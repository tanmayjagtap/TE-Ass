#include<sys/types.h>
#include<sys/socket.h>
#include<stdio.h>
#include<netinet/in.h> 
#include <unistd.h>
#include<string.h> 
#include<strings.h>
#include <arpa/inet.h>
#include <stdlib.h>
#define MAXBUFLEN 1000000
//#define buffsize  150
void main()
{
	int b,sockfd,sin_size,con,n,len;
	//char buff[256];

	if((sockfd=socket(AF_INET,SOCK_STREAM,0))>=0)
		printf("Socket Created Sucessfully \n");
	
	struct sockaddr_in servaddr;

	servaddr.sin_family=AF_INET;
	servaddr.sin_addr.s_addr=inet_addr("127.0.0.1");
	servaddr.sin_port=6007;

	sin_size = sizeof(struct sockaddr_in);
	if((connect(sockfd,(struct sockaddr *) &servaddr, sin_size))==0) //initiate a connection on a socket
		printf("Connect Sucessful \n");

	char buffer[10000];
	char c[10000];
	FILE *fp; 
	//bzero(buffer,10000);
	//bzero(c,10000);
	read(sockfd, buffer, 10000);
	/*int  read(  int  handle,  void  *buffer,  int  nbyte ): The read() function attempts to read nbytes from the file associated with handle, and places the characters read into buffer.*/

	fp= fopen("/home/admin1/receive.txt", "w+");


	/* Read and display data */
	fwrite(buffer,  1,strlen(buffer) + 1, fp);
	//fseek(fp, 0, SEEK_SET);
	//fread(c, strlen(buffer)+1, 1, fp); 
	printf("Received File Contents :%s \n", buffer);
	fclose(fp);
	close(sockfd);
}
