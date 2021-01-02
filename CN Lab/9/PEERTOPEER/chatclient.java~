import java.io.*;
import java.net.*;

public class chatclient
{
	public static void main(String args[]) throws IOException
	{
		Socket cs = null;
		
		String message = null;
		
		cs = new Socket("127.0.0.1",8002);
		System.out.println("Client socket is created and waiting for server");
		DataOutputStream ostream = new DataOutputStream(cs.getOutputStream());//This creates a new data output stream to write data to the specified underlying output stream.
		DataInputStream istream = new DataInputStream(cs.getInputStream());//This creates a DataInputStream that uses the specified underlying InputStream.
		
		while(true)
		{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			// To read from console
			System.out.println("Client Says:");
			message = br.readLine();
			ostream.writeUTF(message);//The java.io.DataOuputStream.writeUTF(String str) method writes a string to the underlying output stream using modified UTF-8 encoding.
			ostream.flush();//This method flushes this data output stream.
			if(message.equalsIgnoreCase("end"))
			{
				cs.close();
				ostream.close();
				istream.close();
				System.exit(0);
			 }
			 message = istream.readUTF();//The java.io.DataInputStream.readUTF() method reads in a string that has been encoded using a modified UTF-8 format. The string of character is decoded from the UTF and returned as String
			 System.out.println("Server Says:" +message);	
		}
	}
}
