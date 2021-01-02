import java.io.*;
import java.net.*;

public class Udpserver
{
	public static void main(String args[]) throws IOException
	{
		DatagramSocket ds = new DatagramSocket(7000);
		System.out.println("SERVER WAITING FOR CLIENT REQUEST..");
		byte receivedata[] = new byte[512];
		byte senddata[] = new byte[512];
		boolean running = true;


		while(running)
		{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

			DatagramPacket receivepacket = new DatagramPacket(receivedata,receivedata.length);

			ds.receive(receivepacket);

			String message = new String(receivepacket.getData(),0,receivepacket.getLength());

			InetAddress ip = receivepacket.getAddress();

			System.out.println("CLIENT WITH Address : "+ip+" SAYS : "+message);

			int port = receivepacket.getPort();

			System.out.println("ENTER SERVER MESSAGE :  ");
			message = br.readLine();

			senddata = message.getBytes();

			DatagramPacket sendpacket = new DatagramPacket(senddata,senddata.length,ip,port);

			ds.send(sendpacket);

			if(message.equalsIgnoreCase("Stop"))
			{
				running = false;
				break;
			}
		}
		ds.close();
		System.out.println("CONNECTION STOPPED");
	}
}
