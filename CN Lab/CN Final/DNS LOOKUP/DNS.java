import java.util.*; 
import java.net.*; 
public class DNS{ 
	public static void main(String args[]){ 
		Scanner in = new Scanner(System.in);				//get host name or ip 
		System.out.println("Enter Host name or ip address:"); 
		String Host=in.nextLine(); 
		try{ 
		InetAddress address = InetAddress.getByName(Host);		//pass host id to InetAddress class method 
 		System.out.println("\n\n");		//print Host name 
		System.out.println("Host Name:"+address.getHostName());		//print Host name 
		System.out.println("ip Address:"+address.getHostAddress());	//print Host Address 
		System.out.println("Host Name and IP Address: "+address); 	//print Host name and Address 
		}catch(UnknownHostException s){ 
			System.out.println("Could not find "+Host); 
			} 
	} 
}
