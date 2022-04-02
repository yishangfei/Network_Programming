import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

public class MulticastSender {
    public static void main(String[] args) {
        try {
            InetAddress inetAddress=InetAddress.getByName("226.0.0.1");
            MulticastSocket multicastSocket =new MulticastSocket();
            multicastSocket.joinGroup(inetAddress);
            byte[] b ="Hello,everybody!".getBytes(StandardCharsets.UTF_8);
            DatagramPacket datagramPacket=new DatagramPacket(b, b.length,inetAddress,5678);
            multicastSocket.send(datagramPacket);
            multicastSocket.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
