import java.io.*;
import java.net.*;
import java.lang.*;
class Server1{
			static DataInputStream din;
			static DataOutputStream dout;
			public static void main(String args[]) throws Exception{
					try{
					int i=0;
					ServerSocket ss=new ServerSocket(8011);
					System.out.println("Server is ready....");
					Socket s=ss.accept();
					din=new DataInputStream(s.getInputStream()); 
					dout=new DataOutputStream(s.getOutputStream());
					int c=din.read();
					int n=5;
					int a[]={10,20,30,40,50};
					dout.write(n);		
					dout.flush();
					switch(c){
					case 1:
					for(i=0;i<n;i++){
						dout.write(a[i]);		
						dout.flush();
					}
					int z=din.read();
					dout.write(a[z]);		
					dout.flush();
					z=din.read();
					System.out.println("ACK received");
					break;
					case 2:
							for(i=0;i<n;i++){
						dout.write(a[i]);		
						dout.flush();
					}
							Thread.sleep(2000);
							System.out.println("ACK not received....sending again");								
							z=din.read();	
							for(i=0;i<n;i++){
						dout.write(a[i]);		
						dout.flush();
					}
					}
					}
					catch(Exception e){
						e.printStackTrace();
					}			
					finally{
							din.close();
							dout.close();					
					}
			}
}
