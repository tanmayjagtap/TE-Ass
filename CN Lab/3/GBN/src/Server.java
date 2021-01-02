//GBN where N=5

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

public class Server {
    
    static ArrayList<DataPacket> dp;
    private static Socket socket = null;
    private static ServerSocket server = null;
    public static DataInputStream din = null;
    public static DataOutputStream dout = null;
    public static ObjectOutputStream out = null;
   
    public static void setValues() {
        final int max = 5;
        dp = new ArrayList<>();
        for(int i=0; i<10; i++) {
            // sequence numbers are modulo-N
            dp.add(new DataPacket(i%max, ((i+1)*10)));
        }
    }

    public static void refresh(){
        Random random = new Random();
        for(DataPacket d : dp){
            d.prob = random.nextDouble();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException{

        setValues();
        ArrayList<DataPacket> window = new ArrayList<>(5);
        server = new ServerSocket(666);
        System.out.println("Waitng for client");
        socket = server.accept();
        din = new DataInputStream(socket.getInputStream());
        dout = new DataOutputStream(socket.getOutputStream());
        out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        socket.setSoTimeout(1000);
        
        //sending how much data to receive
        dout.writeInt(dp.size());
        dout.flush();
        //creating window
        int index;
        for(index=0; index<5; index++) {
            window.add(dp.get(index));
        }
        while(!window.isEmpty()){
            //sending entire window
            for(int i=0;i<window.size();i++){
                try {
                    Ack ack =(Ack) in.readObject();
                    if(ack.type == Ack.Type.DATAPACKET) {
                        System.out.println("Received ack: "+ack.val);
                        while(window.get(0).num != ack.val){
                            System.out.println("removing: "+window.get(0));
                            window.remove(0);
                            if(index < dp.size()){
                                window.add(dp.get(index));
                                index++;
                            }
                        }
                        refresh();
                        i=0;
                    }
                    else if(ack.type == Ack.Type.END) break;
                }catch(Exception e) {
                    ;
                }
                Thread.sleep(500);
                System.out.println("Sending packet: "+window.get(i));
                if(window.get(i).prob > 0.15) {
                    out.writeObject(window.get(i));
                }
            }

            Thread.sleep(1000);

            try {
                Ack ack =(Ack) in.readObject();
                if(ack.type == Ack.Type.DATAPACKET) {
                    while(window.get(0).num != ack.val){
                        window.remove(0);
                        if(index < dp.size()){
                            window.add(dp.get(index));
                            index++;
                        }
                        
                    }
                    refresh();
                }
                else if(ack.type == Ack.Type.WINDOW) {
                    int n=5;
                    while(n>0) {
                        window.remove(0);
                        if(index < dp.size()){
                            window.add(dp.get(index));
                            index++;
                        }
                        n--;
                    }
                }
                else 
                    break;
            }
            catch(Exception e) {
                System.out.println("Server timed out.");
                System.out.println("Resending current frame.");
                refresh();
            }
        }
        System.out.println("All packets sent succesfully");

        din.close();
        out.close();
        server.close();
    }

}
