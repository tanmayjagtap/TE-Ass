//############QJ Technologies###########
//#Author: Qaidjohar Jawadwala		 # 
//#Email: info@qjtechnologies.com 	 #
//#Website: www.qjtechnologies.com     #
//#Code Description: Wireshark .pcap file analyser using libpcap library
//######################################

#include<pcap.h>
#include<stdio.h>
#include<net/ethernet.h>
#include<netinet/ip.h>    //Provides declarations for ip header
 
void process_packet(u_char *, const struct pcap_pkthdr *, const u_char *);
int tcp=0,udp=0,icmp=0,others=0,igmp=0,total=0,i,j; 
 
void main()
{
	char errbuf[PCAP_ERRBUF_SIZE];//PCAP_ERRBUF_SIZE: is the default size of pcap header
	//Open the pcap wireshark file
	pcap_t *handle = pcap_open_offline("p.pcap", errbuf);//pcap_t is inbuilt datatype pcap_open_offline:is inbult fuctionto open the pcap file in offline mode it opens the p.pap file n if any error occurs the error is stored in errbuf array

	//Put the device in sniff loop
	pcap_loop(handle , -1 , process_packet , NULL);//same as fread()   -1:checks full file 
	printf("TCP : %d   UDP : %d   ICMP : %d   IGMP : %d   Others : %d   Total : %d\n", tcp , udp , icmp , igmp , others , total);
  
}

void process_packet(u_char *args, const struct pcap_pkthdr *header, const u_char *buffer)//u_char : unsigned char   buffer mai site ki information store hoti
{
	int size = header->len;//store the length of header in size
	//Get the IP Header part of this packet , excluding the ethernet header
	struct iphdr *iph = (struct iphdr*)(buffer + sizeof(struct ethhdr));//(buffer + sizeof(struct ethhdr): this part of the struct is excluded
	++total;
	switch (iph->protocol) //Check the Protocol and do accordingly...
	{
		case 1:  //ICMP Protocol  : The Internet Control Message Protocol (ICMP) is a supporting protocol in the Internet protocol suite. It is used by network devices, including routers, to send error messages and operational information indicating, for example, that a requested service is not available or that a host or router could not be reached
			 ++icmp;
			 break;
			 
		case 2:  //IGMP Protocol :The Internet Group Management Protocol (IGMP) is a communications protocol used by hosts and adjacent routers on IPv4 networks to establish multicast group memberships. IGMP is an integral part of IP multicast.
			 ++igmp;
			 break;

		case 6:  //TCP Protocol
			 ++tcp;
			 //print_tcp_packet(buffer , size);
			 break;

		case 17: //UDP Protocol
			 ++udp;
			 //print_udp_packet(buffer , size);
			 break;

		default: //Some Other Protocol like ARP etc.
			 ++others;
			 break;
	}
	//printf("TCP : %d   UDP : %d   ICMP : %d   IGMP : %d   Others : %d   Total : %d\n", tcp , udp , icmp , igmp , others , total);
}



/*outpot:-
root@ACA8FD7F:~/Downloads/CN TOtal Final Programs/CNP/Group A/A9_analyzer# g++ -o wireshark_analyzer wireshark_analyzer.cpp -lpcap

root@ACA8FD7F:~/Downloads/CN TOtal Final Programs/CNP/Group A/A9_analyzer# ./wireshark_analyzer 
TCP : 4   
UDP : 193   
ICMP : 0   
IGMP : 0  
Others : 511  
Total : 708
*/
 
