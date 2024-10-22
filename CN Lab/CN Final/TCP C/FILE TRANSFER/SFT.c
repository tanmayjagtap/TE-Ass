#include<sys/types.h>
#include<sys/socket.h>
#include<stdio.h>
#include<netinet/in.h> 
#include <unistd.h>
#include<string.h> 
#include <arpa/inet.h>
#include <stdlib.h>
#define MAXBUFLEN 1000000
void main()
{
	int b,sockfd,connfd,sin_size,l,n,len;

	if((sockfd=socket(AF_INET,SOCK_STREAM,0))>=0)  //socket creation 1st agrument is the address domain(AF_INET is used for Internet domain for any two hosts on the Internet.),second argument is the type of socket( a stream socket i.e SOCK_STREAM in which characters are read in a continuous stream as if from a file or pipe), third argument is the protocol. If this argument is zero (and it always should be except for unusual circumstances), the operating system will choose the most appropriate protocol. It will choose TCP for stream sockets and UDP for datagram sockets.
		printf("Socket Created Sucessfully \n");                 //on success 0 otherwise -1

	struct sockaddr_in servaddr;              
	struct sockaddr_in clientaddr;

	servaddr.sin_family=AF_INET;
	/*The variable servaddr is a structure of type struct sockaddr_in. The first field is short sin_family, which contains a code for the address family. It should always be set to the symbolic constant AF_INET.*/
	servaddr.sin_port=6007;
	/*The second field of serv_addr is unsigned short sin_port , which contain the port number.*/
	servaddr.sin_addr.s_addr=inet_addr("127.0.0.1");
/*The third field of sockaddr_in is a structure of type struct in_addr which contains only a single field unsigned long s_addr. This field contains the IP address of the host. For server code, this will always be the IP address of the machine on which the server is running, and there is a symbolic constant INADDR_ANY which gets this address. */
	

	if((bind(sockfd, (struct sockaddr *)&servaddr,sizeof(servaddr)))==0)
	/*The bind() system call binds a socket to an address.It takes three arguments: the socket file descriptor, the address to which is bound, and the size of the address to which it is bound. */
		printf("Bind Sucessful \n");

	if((listen(sockfd,5))==0) //The listen system call allows the process to listen on the socket for connections. The first argument is the socket file descriptor, and the second is the size of the backlog queue, i.e., the number of connections that can be waiting while the process is handling a particular connection. This should be set to 5, the maximum size permitted by most systems. If the first argument is a valid socket, this call cannot fail, and so the code doesn't check for errors.ocket
		printf("Listen Sucessful \n");

	sin_size = sizeof(struct sockaddr_in);
	if((connfd=accept(sockfd,(struct sockaddr *)&clientaddr,&sin_size))>0)
		printf("Accept Sucessful \n");
	char buffer[100];
	char c[10000] = "this is file transfer program";
	//char source[MAXBUFLEN + 1];
	//bzero(buffer,10000);

	FILE *fp; 
	fp= fopen("/home/admin1/send.txt", "w+");
	//fp = fopen("file.txt", "w+");

	/* Write data to the file */
	fwrite(c, 1, strlen(c) + 1, fp);/*e C library function size_t fwrite(const void *ptr, size_t size, size_t nmemb, FILE *stream) writes data from the array pointed to, by ptr to the given stream.
	    ptr − This is the pointer to the array of elements to be written.
	    size − This is the size in bytes of each element to be written.
	    nmemb − This is the number of elements, each one with a size of size bytes.
	    stream − This is the pointer to a FILE object that specifies an output stream.
	*/

	/* Seek to the beginning of the file */
	fseek(fp, 0, SEEK_SET);	/*The C library function int fseek(FILE *stream, long int offset, int whence) sets the file position of the stream to the given offset.stream − This is the pointer to a FILE object that identifies the stream.offset − This is the number of bytes to offset from whence.whence − This is the position from where offset is added SEEK_SET:beginning of the file*/

	/* Read and display data */
	fread(buffer, 1,strlen(c)+1, fp);/*he C library function size_t fread(void *ptr, size_t size, size_t nmemb, FILE *stream) reads data from the given stream into the array pointed to, by ptr.
	    ptr − This is the pointer to a block of memory with a minimum size of size*nmemb bytes.
	    size − This is the size in bytes of each element to be read.
	    nmemb − This is the number of elements, each one with a size of size bytes.
	    stream − This is the pointer to a FILE object that specifies an input stream.
	*/
	write(connfd, buffer, strlen(buffer));
	/*int  write(  int  handle,  void  *buffer,  int  nbyte  );

The write() function attempts to write nbytes from buffer to the file associated with handle. */
	printf("Sent File Contents: %s\n", buffer);
	fclose(fp);
	close(sockfd);
}
