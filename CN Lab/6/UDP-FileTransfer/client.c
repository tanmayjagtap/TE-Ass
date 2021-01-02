#include<stdio.h>
#include<string.h>
#include<arpa/inet.h>
#include<stdlib.h>
#include<sys/socket.h>

#define SERVER "127.0.0.1"
#define BUFLEN 503
#define PORT 8885

void die(char *s)
{
	perror(s);
	exit(0);
}
int main(void)
{
	struct sockaddr_in si_other;
	int s,i,slen=sizeof(si_other);
	char buf[BUFLEN];
	char message[BUFLEN];
	
	if( (s=socket(AF_INET,SOCK_DGRAM,0)) == -1 )
		die("socket");
	
	si_other.sin_family = AF_INET;
	si_other.sin_port = htons(PORT);
	
	if(inet_aton(SERVER,&si_other.sin_addr)==0)
	{
		fprintf(stderr,"inet_aton() failed\n");
		exit(1);
	}
	
	char fname[20],data[200];
	printf("Enter Filename with Extension: ");
	scanf("%s",fname);
	sendto(s,fname,20,0,(struct sockaddr *) &si_other, slen);
	recvfrom(s,data,sizeof(data),0,NULL,NULL);
	printf("======>\n%s\n",data);
	
	if(strcmp(data,"File does not exist!!!\n\n")==0)
	close(s);
	else
	{
		FILE *fp;
		fp=fopen(fname,"w");
		fwrite(data,strlen(data),1,fp);
		fclose(fp);
	}
	return 0;
}
