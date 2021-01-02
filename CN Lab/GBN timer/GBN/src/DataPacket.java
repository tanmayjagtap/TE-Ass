import java.io.Serializable;
import java.util.Random;

public class DataPacket implements Serializable {
    
    private static final long serialVersionUID = 1L;
    public int num;
    public int data;

    //probabilty of being successfully sent
    public double prob;

    Random random;
    public DataPacket(int num, int data){
        random = new Random();
        this.num=num;
        this.data=data;
        this.prob=random.nextDouble();
    }

    @Override
    public String toString() {
        return "["+num+","+data+","+prob+"]";
    }
}
