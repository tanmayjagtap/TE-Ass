
/* 
Problem Statement: Write a program to demonstrate subletting and find the subnet masks. 
*/ 
import java.util.*; 
 
 
public class Subnet2 
    { 
        
    	public static void main(String[] args) 
    	{ 
							//get network address 
    	   Scanner sc=new Scanner(System.in); 
    	   System.out.print("Enter IP address:"); 
    	   String ip=sc.nextLine(); 
 
									//get subnet number 
    	   	System.out.print("\nEnter the number of sub-networks: "); 
    		int n = sc.nextInt(); 
 
									//calculate no of subnet bits  
		n=(int)Math.ceil(Math.log(n)/Math.log(2)); 
    		System.out.println("\nNumber of bits required for sub-network addressing = "+n); 
 
 
										//check class of ip address 
    		String checkclass=ip.substring(0,3); 
    		int cc=Integer.parseInt(checkclass); 
 
    	    if(cc>0 && cc<224) 
            { 
                if(cc<128) 
                { 
					System.out.println("\nIP belongs to Class :A"); 
					System.out.println("\nDefault Subnet Mask :255.0.0.0"); 
           	   System.out.println("\nTotal Number of network ID's:"+(Math.pow(2,7)-2));  
           	   System.out.println("\nTotal Number of Host ID's:"+(Math.pow(2, 24)-2)); 
                   subnetMask(n+8); 
                } 
                else if(cc>127 && cc<192) 
                { 
		   System.out.println("\nIP belongs to Class :B\n"); 
		   System.out.println("\nDefault Subnet Mask :255.255.0.0"); 
           	   System.out.println("\nTotal Number of network ID's:"+(Math.pow(2, 14)));  
           	   System.out.println("\nTotal Number of Host ID's:"+(Math.pow(2, 16)-2));  
                   subnetMask(n+16); 
                } 
                else if(cc>191) 
                { 
		   System.out.println("\nIP belongs to Class :C"); 
		   System.out.println("\nDefault Subnet Mask :255.255.255.0"); 
           	   System.out.println("\nTotal Number of network ID's:"+(Math.pow(2, 21))); 
           	   System.out.println("\nTotal Number of Host ID's:"+(Math.pow(2, 8)-2));  
                   subnetMask(n+24); 
                } 
            } 
    		 
    	} 
 
	//claculate Subnet Mask 
    	public static void subnetMask(int n) 
    	{ 
    		int x=0,y=8; 
    		String s=""; 
 
    		for (int i = 0; i <32; i++)  
    		{ 
    		    	if(i<n)  //till n set value as 1 
    		    	s+="1"; 
    		    	else     //after n set value as 0 
    		    	s+="0"; 
 
		}
 
		System.out.print("\nSubnet mask is:"); 
		for(int i=0;i<4;i++) 
		{ 
			System.out.print(Integer.parseInt(s.substring(x,y),2)+".");//convert binary to decimal 
			x+=8;		//increment by 8 bits 
			y+=8;		//increment by 8 bits 
		} 
           System.out.println("\n\nNumber of Host ID's in subnet:"+(Math.pow(2, 32-n))); 
 
    	} 
    } 

//Output
//
//$ java Subnet2
//Enter IP address:192.168.107.162
//Enter the number of sub-networks: 4
//Number of bits required for sub-network addressing = 2
//IP belongs to Class :C
//Default Subnet Mask :255.255.255.0
//Total Number of network ID's:2097152.0
//Total Number of Host ID's:254.0
//Subnet mask is:255.255.255.192.
//Number of Host ID's in subnet:64.0


