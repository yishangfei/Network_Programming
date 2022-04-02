import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

public class MulticastReceive {
    public static void main(String[] args) {
        try {
            InetAddress inetAddress=InetAddress.getByName("226.0.0.1");
            MulticastSocket multicastSocket =new MulticastSocket(5678);
            multicastSocket.joinGroup(inetAddress);
            byte[] b=new byte[100];
            DatagramPacket datagramPacket=new DatagramPacket(b, b.length);
            String str= new String(datagramPacket.getData(),0,datagramPacket.getLength());
            System.out.println(str);
            multicastSocket.leaveGroup(inetAddress);
            multicastSocket.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
