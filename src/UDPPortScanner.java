import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPPortScanner {
    public static void main(String[] args) {
        DatagramSocket datagramSocket = null;
        for (int i = 0; i < 1025; i++) {
            try {
                datagramSocket=new DatagramSocket(i);
            } catch (SocketException e) {
                System.out.println("服务占用的端口"+i);
                e.printStackTrace();
            }
            finally {
                if (datagramSocket!=null){
                    datagramSocket.close();
                }
            }
        }
    }
}
