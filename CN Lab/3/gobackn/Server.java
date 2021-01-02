import java.io.*;
import java.net.*;
import java.util.*;
public class Server{
	static DataInputStream din;
	static DataOutputStream dout;
	public static void main(String args[]) throws Exception{
			try{
						ServerSocket ssocket=new ServerSocket(8011);
						System.out.println("Waiting for Client request");
						Socket socket=ssocket.accept();
						din=new DataInputStream(socket.getInputStream());
						 dout=new DataOutputStream(socket.getOutputStream());
						int n=din.read();
						for(int i=0;i<n;i++){
									dout.write(i);
									dout.flush();
									System.out.println(i+"send");
									System.out.println("Waiting for acknowledgement");
									Thread.sleep(7000);
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
