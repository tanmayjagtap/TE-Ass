import java.io.*;
import java.net.*;


public class Client {

	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket s = new Socket("Localhost", 8033);
		DataInputStream in = new DataInputStream(s.getInputStream());
		DataOutputStream out =  new DataOutputStream(s.getOutputStream());
		String str;
		BufferedReader read =  new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Enter Your name : ");
		str=read.readLine();
		out.writeUTF(str);
		out.flush();
		while(true)
		{
			System.out.print("Enter Your message: ");
			str=read.readLine();
			out.writeUTF(str);
			out.flush();
			if(str.equalsIgnoreCase("end"))
			{
				in.close();
				out.close();
				s.close();
				System.err.println("Connection Ended");
				System.exit(0);
			}
			str=in.readUTF();
			System.out.println("Server Says: "+str);
		}
	}

}
