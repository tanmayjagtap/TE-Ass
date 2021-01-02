import java.io.*;
import java.net.*;


class ClientThread implements Runnable{
	
	DataInputStream in;
	DataOutputStream out;
	Socket s;
	Thread t;
	BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
	public ClientThread(Socket s) {
		this.s=s;
		try {
			in=new DataInputStream(s.getInputStream());
			out=new DataOutputStream(s.getOutputStream());
			t=new Thread(this);
			t.setName(in.readUTF());
			System.out.println("\n"+t.getName()+" joined Chat....");
			t.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void run() {
		String str="",str2="sd";
	
		try {
				while(!str2.equalsIgnoreCase("end")) 
				{
					str=in.readUTF();
					System.out.println(t.getName()+" says: "+str);
					System.out.print("Enter Your Message to "+t.getName()+" : ");
					str2=read.readLine();
					out.writeUTF(str2);
					out.flush();
					}
					in.close();
					out.close();
					s.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		
	}
	
}

public class Server {

	public static void main(String[] args) throws IOException {
		ServerSocket ss = new ServerSocket(8033);
		System.out.println("Server is created");
		while(true)
			new ClientThread(ss.accept());

	}

}
