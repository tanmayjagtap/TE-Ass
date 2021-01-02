import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Client {

    static Timer timer;
    static int cnt = 0;
    static boolean task = true;
    static int index = 0;
    static ObjectOutputStream out=null;

    public static void start() {
        timer = new Timer();
    }

    public static void pause() {
        timer.cancel();
    }

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("Localhost", 666);
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            DataInputStream din = new DataInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
            ArrayList<DataPacket> dp = new ArrayList<>();

            int size = din.readInt();
            int max = 5;
            boolean outOfOrder = true;
            
            DataPacket d;
            while(((d=(DataPacket)in.readObject())!=null) && (size!=0)) {
                
                //scheduling timer once first data packet is recevied
                if(task) {
                    start();
                    timer.schedule(new TimerTask(){
                        public void run() {
                            Ack ack = new Ack(index, Ack.Type.DATAPACKET);
                            try {
                                out.writeObject(ack);
                                out.flush();
                            } catch (IOException e){
                                e.printStackTrace();
                            }
                            System.out.println("Expecting packet with seq_no: "+index);
                            cnt=0;
                        }
                    }, 4000, 4000);
                    task = false;
                }
                if(d.num == index) {
                    System.out.println("Received packet: "+d);
                    cnt++;
                    dp.add(d);
                    index = (index+1)%max;
                    outOfOrder = false;
                    size--;
                    if(size == 0) {
                        Ack ack = new Ack(index, Ack.Type.END);
                        out.writeObject(ack);
                        out.flush();
                        break;
                    }
                 }

                 //if an out of order frame is received, 
                 //client immediately sends ack of which fram to expect
                else {
                    System.out.println("Discarding packet: "+d);
                    outOfOrder = true;
                }

               
                //if all packets in current window are received in order, we send ack
                if(!outOfOrder && (cnt==5)){
                    Ack ack = new Ack(index, Ack.Type.WINDOW);
                    out.writeObject(ack);
                    out.flush();
                    System.out.println("Expecting next frame: ");
                    cnt=0;
                    pause();
                    task=false;
                }
            }
            timer.cancel();
            System.out.println("All packet successfully received.");
            Ack ack = new Ack(index, Ack.Type.END);
            out.writeObject(ack);
            out.flush();
            socket.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
       
    }
}
