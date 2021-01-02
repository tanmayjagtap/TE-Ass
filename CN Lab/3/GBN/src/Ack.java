import java.io.Serializable;

public class Ack implements Serializable {
    int val;
    Type type;
    enum Type {
        WINDOW("WINDOW"), 
        DATAPACKET("DATAPACKET"),
        END("END");
        String label;
        Type(String label) {this.label = label;}
    }

    public Ack(int val, Type type) {
        this.val = val;
        this.type = type;
    }
}
