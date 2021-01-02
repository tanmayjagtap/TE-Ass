import java.net.*;
import java.io.*;
class DNS{
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader ( new InputStreamReader (System.in));
		
		try{
			System.out.println("Enter a website name");
			String site = br.readLine();
			InetAddress ip = InetAddress.getByName(site); 
			System.out.println("The IP address is : "+ip);
			System.out.println("Enter an IP address");
			String ip_add = br.readLine();
			byte[] b = new byte[4];
               		String[] bytes = ip_add.split("\\.");
		        for (int i = 0; i < bytes.length; i++){
		        
		            b[i] = new Integer(bytes[i]).byteValue();
		        }
		        ip = InetAddress.getByAddress(b);
			System.out.println("Host name is : " + ip.getHostName());
		}
		catch(UnknownHostException ue){
			System.out.println("Website not found");
		}
		catch(Exception e){
			System.out.println("IP address not found");
		}
		
	}
}

// OutPut
// $ java DNS.java
// Note: DNS.java uses or overrides a deprecated API.
// Note: Recompile with -Xlint:deprecation for details.
// Enter a website name
// tutorialspoint.com
// The IP address is : tutorialspoint.com/95.217.74.146
// Enter an IP address
// 94.130.81.180
// Host name is : static.180.81.130.94.clients.your-server.de
