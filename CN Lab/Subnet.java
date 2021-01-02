import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;

class Subnet {
    
    String ip;
    String mask;
    public void getSubnetClass() {
        String subnetClass = ip.substring(0, 3);
        int sc = Integer.parseInt(subnetClass);

        if(sc > 0 && sc < 255) {
            if(sc <= 127) {
                mask = "255.0.0.0";
            }
            else if (sc >= 128 && sc<= 191) {
                mask = "255.255.0.0";
            }
            else if(sc > 191){
                mask = "255.255.255.0";
            }
        }
        System.out.println("The mask is: "+mask);
    }

    public void getNID() {
        String[] ip1 = ip.split("\\.");
        String[] mask1 = mask.split("\\.");

        System.out.print("The NID is: ");
        for(int i=0; i<4; i++ ){
            int x = Integer.parseInt(ip1[i]);
            int y = Integer.parseInt(mask1[i]);
            int z = x&y;
            if(i<3){
                System.out.print(z+".");
            }
            else{
                System.out.println(z);
            }
        }
    }

    public void getFirstAdd() {
        String[] ip1 = ip.split("\\.");
        String[] mask1 = mask.split("\\.");

        System.out.print("The First Address is: ");
        for(int i=0; i<4; i++ ){
            int x = Integer.parseInt(ip1[i]);
            int y = Integer.parseInt(mask1[i]);
            int z = x&y;
            if(i<3){
                System.out.print(z+".");
            }
            else{
                System.out.println(z);
            }
        }
    }

    static int onesComplement(int n) { 
        if(n == 255) {
            return 0;
        }
        else 
            return 255;
    } 

    public void getLastAdd() {
        String[] ip1 = ip.split("\\.");
        String[] mask1 = mask.split("\\.");

        System.out.print("The Last Address is: ");
        for(int i=0; i<4; i++ ){
            int x = Integer.parseInt(ip1[i]);
            int y = onesComplement(Integer.parseInt(mask1[i]));
            int z = x|y;
            if(i<3){
                System.out.print(z+".");
            }
            else{
                System.out.println(z);
            }
        }
    }
 
    public static void main(String[] args) throws IOException{
        Subnet s = new Subnet();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter ip address: ");
        s.ip = br.readLine();
        s.getSubnetClass();
        s.getNID();   
        s.getFirstAdd();
        s.getLastAdd();

        System.out.print("Enter ip address in cidr format: ");
        String addr = br.readLine();
        String[] parts = addr.split("/");
        s.ip = parts[0];
        int prefix;
        if (parts.length < 2) {
            prefix = 0;
        } else {
            prefix = Integer.parseInt(parts[1]);
        }
        s.mask = String.valueOf(0xffffffff << (32 - prefix));
        System.out.println("Prefix=" + prefix);
        System.out.println("Address=" + s.ip);

        int value = Integer.parseInt(s.mask);
        byte[] bytes = new byte[]{ 
                (byte)(value >>> 24), (byte)(value >> 16 & 0xff), (byte)(value >> 8 & 0xff), (byte)(value & 0xff) };

        InetAddress netAddr = InetAddress.getByAddress(bytes);
        s.mask = String.valueOf(netAddr.getHostAddress());
        System.out.println("Mask=" + s.mask);
        s.getNID();
        s.getFirstAdd();
        s.getLastAdd();
    }
}

/* output:

    Enter ip address: 192.168.10.0
    The mask is: 255.255.255.0
    The NID is: 192.168.10.0
    The First Address is: 192.168.10.0
    The Last Address is: 192.168.10.255
    Enter ip address in cidr format: 192.168.10.0/24
    Prefix=24
    Address=192.168.10.0
    Mask=255.255.255.0
    The NID is: 192.168.10.0
    The First Address is: 192.168.10.0
    The Last Address is: 192.168.10.255

*/