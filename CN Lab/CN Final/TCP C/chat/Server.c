#include<stdio.h>
#include<sys/types.h>
#include<sys/socket.h>
#include<netinet/in.h>
#include<string.h>
#include<stdlib.h>
#include<error.h>
#include<netdb.h>
#include<sys/wait.h>
void main()
{
	int sockfd,conn,clilen,val=0;
	char buffer[10000],str[1000];
	
	if((sockfd=socket(AF_INET,SOCK_STREAM,0))==-1)
	{	perror("SOCKET");
		exit(0);
	}
	else	
		printf("\nSOCKET CREATED SUCCESSFUL");

	struct sockaddr_in seraddr;
	struct sockaddr_in clientaddr;

	seraddr.sin_family=AF_INET;
	seraddr.sin_port=6008;
	seraddr.sin_addr.s_addr=inet_addr("127.0.0.1");

	if((bind(sockfd,(struct sockaddr *)&seraddr,sizeof(seraddr)))==-1)
	{
		perror("BIND");
		exit(1);
	}
	else
		printf("\nBIND SUCCESSFUL");

	if((listen(sockfd,5))==-1)
	{
		perror("LISTEN");
		exit(0);
	}
	else
		printf("\nLISTEN SUCCESSFUL");

	for(;;)
	{
		clilen=sizeof(clientaddr);
		if((conn=accept(sockfd,(struct sockaddr *)&clientaddr,&clilen))==0)
		{
			perror("ACCEPT");
			exit(0);
		}
		else
			printf("\nACCEPT SUCCESSFUL");

		while(1)
		{
			printf("\nENTER YOUR MESSAGE : ");
			scanf("%s",str);
			write(conn,str,sizeof(str));
			if(strcmp(buffer,"exit")==0)
				break;
			val=read(conn,buffer,sizeof(buffer));
			printf("\nCLIENT SAYS : %s ",buffer);
		}
	}
	close(conn);
}
