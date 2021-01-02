import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
        System.out.print("Enter ip address in cidr format: ");
        s.ip = br.readLine();
        s.getSubnetClass();
        s.getNID();   
        s.getFirstAdd();
        s.getLastAdd();
    }
}