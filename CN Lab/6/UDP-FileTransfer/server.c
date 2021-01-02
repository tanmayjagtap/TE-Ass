#include"stdio.h"
#include"string.h"
#include"stdlib.h"
#include<arpa/inet.h>
#include<sys/socket.h>

#define BUFLEN 503
#define PORT 8885

void die(char *s)
{
	perror(s);
	exit(1);
}
int main(void)
{
	struct sockaddr_in si_me, si_other;
	int s,i,j,slen=sizeof(si_other),recv_len;
	char buf[BUFLEN];
	if((s=socket(AF_INET,SOCK_DGRAM,IPPROTO_UDP))==-1)
	die("socket");
	
	si_me.sin_family = AF_INET;
	si_me.sin_port= htons(PORT);
	si_me.sin_addr.s_addr=htonl(INADDR_ANY);
	
	if(bind(s,(struct sockaddr*)&si_me,sizeof(si_me))==-1)
	die("bind");
	
	recv_len = recvfrom(s,buf,20,0,(struct sockaddr *)&si_other,&slen);
	
	printf("Filename is : %s\n",buf);
	
	FILE *fp;
	fp=fopen(buf,"r");
	char data[200];
	if(fp==NULL)
		sendto(s,"File does not exist!!!\n\n",30,0,(struct sockaddr *)&si_other,slen);
	else
	{
		fread(data,strlen(data),1,fp);
		sendto(s,data,sizeof(data),0,(struct sockaddr *)&si_other,slen);
		fclose(fp);
	}
	return 0;
}
