import java.io.*;
import java.net.*;

public class udpchatclient
{
	public static void main(String args[]) throws IOException
	{
		String message = null;
		DatagramSocket cs = null; 
				 			
		cs = new DatagramSocket();  
				  
		byte[] receiveData = new byte[512];   
		byte[] sendData  = new byte[512]; 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));  
		System.out.println(" UDP Client socket is created and waiting for server");
		
		InetAddress IPAddress = InetAddress.getByName("127.0.0.1"); 
		 
		int port = 9001;
				  
		while(true)
		{
			System.out.println("Client Says:");
						
			message = br.readLine();	
						 
			sendData = message.getBytes(); 
					  
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress,port); 
			  
			cs.send(sendPacket);  

			DatagramPacket receivePacket =new DatagramPacket(receiveData, receiveData.length); 
			  
			cs.receive(receivePacket); 
			  
			message = new String(receivePacket.getData(), 0, receivePacket.getLength());  //myoperation = istream.readUTF();

			System.out.println("Server Says: "+message);

			if(message.equalsIgnoreCase("End"))
				break; 
		}
		cs.close();
		System.out.println("Client Stopped by User program");
	}
}
