import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SendUDPTime {
    public static void main(String[] args) throws IOException, InterruptedException {
        DatagramChannel dc=DatagramChannel.open();

        SocketAddress local=new InetSocketAddress(InetAddress.getByName("localhost"),1234);

        SocketAddress dst=new InetSocketAddress(InetAddress.getByName("localhost"),5678);

        dc.bind(local);
        SimpleDateFormat sdf=new SimpleDateFormat("HH:mm:ss");
        String msg;
        ByteBuffer bB=ByteBuffer.allocateDirect(100);
        while (true){
            msg="time"+sdf.format(new Date());
            bB.put(msg.getBytes(StandardCharsets.UTF_8));
            bB.flip();
            int count=dc.send(bB,dst);
            bB.clear();
            System.out.println("发送"+msg);
            Thread.sleep(1000);
        }
    }
}
