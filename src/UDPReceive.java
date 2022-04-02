import java.io.IOException;
import java.net.*;

public class UDPReceive {
    public static void main(String[] args) {
        try {
            DatagramSocket datagramSocket=new DatagramSocket(5678, InetAddress.getByName("127.0.0.1"));
            byte[] b=new byte[100];
            DatagramPacket datagramPacket=new DatagramPacket(b,b.length);
            datagramSocket.receive(datagramPacket);
            String str=new String(datagramPacket.getData(),0, datagramPacket.getLength());
            System.out.println(str+"==="+datagramPacket.getAddress());
            datagramSocket.close();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
