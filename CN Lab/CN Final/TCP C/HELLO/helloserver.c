// Hello Server-Side Program  

#include<sys/types.h> // contains declaration of function that requires i/o operations at the os level
#include<sys/socket.h> // used to define socket address structure
#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<netinet/in.h> // used to fetch the ip address...used to define sochaddr_in struct...
#include<stdlib.h>

#define buffsize  150

int main(void)
{
	struct sockaddr_in servaddr,clientaddr; // sockaddr_in contains the field sin_family,sin_addr.s_addr,sin_port
	char buff[buffsize];
	int listenfd,connfd;
	int sin_size;

	if((listenfd=socket(AF_INET,SOCK_STREAM,0))==-1) // used to create socket descriptor
// The call to the function ‘socket()’ creates an UN-named socket inside the kernel and returns an integer known as socket descriptor.This function takes domain/family as its first argument. For Internet family of IPv4 addresses we use AF_INET.
// The second argument ‘SOCK_STREAM’ specifies that the transport layer protocol that we want should be reliable ie it should have acknowledgement techniques.
// The third argument is generally left zero to let the kernel decide the default protocol to use for this connection. For connection oriented reliable connections
		perror("Socket Creation Error.\n");  
	else
		printf("Socket Created Successfully\n"); 
	bzero((char *) &servaddr, sizeof(servaddr)); // The function bzero() sets all values in a buffer to zero. It takes two arguments, the first is a pointer to the buffer and the second is the size of the buffer. Thus, this line initializes serv_addr to zeros. 
	servaddr.sin_family=AF_INET;
	servaddr.sin_addr.s_addr=inet_addr("192.168.107.162");
	servaddr.sin_port=htons(5000);
/*it is necessary to convert this to network byte order using the function htons() which converts a port number in host byte order to a port number in network byte order. */
	if(bind(listenfd, (struct sockaddr *)&servaddr,sizeof(servaddr)) == -1) // The call to the function ‘bind()’ assigns the details specified in the structure ‘serv_addr’ to the socket created in the step above. 
		perror("Bind Error\n"); 
	listen(listenfd,5); // The call to the function ‘listen()’ with second argument as ’4’ specifies maximum number of client connections that server will queue for this listening socket.
// After the call to listen(), this socket becomes a fully functional listening socket.
	sin_size = sizeof(struct sockaddr_in);
	for(;;)
	{
		if((connfd=accept(listenfd,(struct sockaddr *)&clientaddr, &sin_size))==-1) // In the call to accept(), the server is put to sleep and when for an incoming client request, the function accept () wakes up and returns the socket descriptor representing the client socket.
			perror("Accept Error\n");
		strcpy(buff,"Hello Client - Server\n");
		write(connfd,buff,strlen(buff));
		close(connfd);
	}
}
