import java.io.*;
import java.net.*;
import java.util.*;
public class Client{
		static DataInputStream din;
	static DataOutputStream dout;
		public static void main(String args[]) throws Exception{
					try{
							Scanner scanner=new Scanner(System.in);
							Socket socket=new Socket("Localhost",8011);		
							System.out.println("Enter no. of frames expected");
							int n=scanner.nextInt();
							int a[]=new int[n];
							din=new DataInputStream(socket.getInputStream());
							dout=new DataOutputStream(socket.getOutputStream());
							dout.write(n);
							for(int i=0;i<n;i++){
								a[i]=din.read();
								dout.write(i);
								dout.flush();
								System.out.println("acknowledgement sent");
							}
				}
				catch(Exception e){
					System.out.println(e);
				}
			finally{
				din.close();
				dout.close();
			}				
		
		}
}
