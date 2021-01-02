import java.io.*;
import java.net.*;

public class udpchatserver
{
	public static void main(String args[]) throws IOException 
	{
		DatagramSocket ss = null; 
		//ServerSocket serversocket = null; Socket socket =null;
			
		ss = new DatagramSocket(9001);  
		//serversocket = new ServerSocket(8000);
			  
		byte[] receiveData = new byte[512]; 
		//DataInputStream istream = new DataInputStream(socket.getInputStream());
		byte[] sendData  = new byte[512]; 
		//DataOutputStream ostream = new DataOutputStream(socket.getOutputStream());
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));   
		System.out.println(" UDP Server socket is created and waiting for client");
		
		while(true) 
		{ 
		  		  
			DatagramPacket receivePacket =new DatagramPacket(receiveData, receiveData.length); 
			/*public DatagramPacket(byte[] buf,
					      int length)
				Constructs a DatagramPacket for receiving packets of length length.
				The length argument must be less than or equal to buf.length.*/
				  
			ss.receive(receivePacket); /*Receives a datagram packet from this socket. When this method returns, the DatagramPacket's buffer is filled with the data received. The datagram packet also contains the sender's IP address, and the port number on the sender's machine.*/
				  
			String message = new String(receivePacket.getData(), 0, receivePacket.getLength());  /*myoperation = istream.readUTF()
				String(byte[] bytes, int offset, int length)
				bytes - The bytes to be decoded into characters
				offset - The index of the first byte to decode
				length - The number of bytes to decode*/
			System.out.println("Client Says: "+message);
			if(message.equals("end")) 
				break;
			// To read from console
			System.out.println("Enter Server Message:");
			message = br.readLine();
						  
			InetAddress IPAddress = receivePacket.getAddress(); /*Returns the IP address of the machine to which this datagram is being sent or from which the datagram was received.*/
				  
			int port = receivePacket.getPort(); 
						  
			sendData = message.getBytes(); /*Encodes this String into a sequence of bytes using the platform's default charset, storing the result into a new byte array.*/
				  
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress,port); 
			/*public DatagramPacket(byte[] buf,
				      int length,
				      InetAddress address,
				      int port)
			Constructs a datagram packet for sending packets of length length to the specified port number on the specified host. The length argument must be less than or equal to buf.length.
			Parameters:
			buf - the packet data.
			length - the packet length.
			address - the destination address.
			port - the destination port number.*/

			ss.send(sendPacket); /*Sends a datagram packet from this socket. The DatagramPacket includes information indicating the data to be sent, its length, the IP address of the remote host, and the port number on the remote host.*/
			//ostream.writeUTF(myoperation);
		} 	
		ss.close();
		System.out.println("Server Stopped by User program");
	}
}
