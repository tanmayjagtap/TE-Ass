#include<stdio.h>
#include<sys/types.h>
#include<sys/socket.h>
#include<netinet/in.h>
#include<string.h>
#include<stdlib.h>
#include<sys/wait.h>
void main()
{
	int sockfd,conn,val=0;
	char buffer[1000],str[10000];
	
	if((sockfd=socket(AF_INET,SOCK_STREAM,0))==-1)
	{
		perror("SOCKET");
		exit(0);
	}
	else
		printf("\nSOCKET CREATED SUCCESSFUL");

	struct sockaddr_in client;

	client.sin_family=AF_INET;
	client.sin_port=6008;
	client.sin_addr.s_addr=inet_addr("127.0.0.1");

	if(connect(sockfd,(struct sockaddr *)&client,sizeof(client))==-1)
	{
		perror("CONNECT");
		exit(0);
	}
	else	
		printf("\nCONNECT SUCCESSFUL");

	while(1)
	{
		val=read(sockfd,buffer,sizeof(buffer));
		printf("\nSERVER SAYS : %s ",buffer);
		printf("\nENTER YOUR MESSAGE : ");
		scanf("%s",str);
		write(sockfd,str,sizeof(str));
		if(strcmp(str,"exit")==0)
			break;
	}
	close(sockfd);
}
