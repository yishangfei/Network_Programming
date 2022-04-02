import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class UDPSender {
    public static void main(String[] args) {
        try {
            DatagramSocket ds=new DatagramSocket(1234);
            byte[] b="This is a message.".getBytes(StandardCharsets.UTF_8);
            DatagramPacket dp= new DatagramPacket(b,b.length, InetAddress.getByName("127.0.0.1"),5678);
            ds.send(dp);
            ds.close();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
