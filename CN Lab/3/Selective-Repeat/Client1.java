import java.io.*;
import java.net.*;
import java.lang.*;
import java.util.*;
class Cliet1{	
		static DataInputStream din;
			static DataOutputStream dout;
			static Scanner sc;
	public static void main(String args[]) throws Exception{
		try{
		sc=new Scanner(System.in);
		int k;
		int a[]=new int[5];	
		Socket s=new Socket("Localhost",8011);
					din=new DataInputStream(s.getInputStream()); 
					dout=new DataOutputStream(s.getOutputStream());
		System.out.println("connection established");
		System.out.println("Choose what you want to experience");
		System.out.println("1)damaged frame\n2)lost ack 3)lost frame");
		int c=sc.nextInt();
		dout.write(c);
		dout.flush();
		 k=din.read();
		System.out.println(" no. of frames to be sent"+k);
		switch(c){
				case 1:		
							
								for(int i=0;i<5;i++){
										a[i]=din.read();
							}
							a[3]=-1;
							dout.write(3);
							dout.flush();
							a[3]=din.read();
							System.out.println(" frames  received :");
							for(int i=0;i<5;i++){
									System.out.println(a[i]);
							}
								dout.write(0);
								break;
						case 2:
								for(int i=0;i<5;i++){
										a[i]=din.read();
							}	
								 Thread.sleep(5000);					
								dout.write(1);
								for(int i=0;i<5;i++){
										a[i]=din.read();
							}
							System.out.println(" frames  received :");
							for(int i=0;i<5;i++){
									System.out.println(a[i]);
							}	
							break;
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
