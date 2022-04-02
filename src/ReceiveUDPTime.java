import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class ReceiveUDPTime {
    public static void main(String[] args) throws IOException, InterruptedException {

        DatagramChannel dc =DatagramChannel.open();
        dc.configureBlocking(false);

        ByteBuffer bB=ByteBuffer.allocateDirect(100);
        SocketAddress local =new InetSocketAddress(InetAddress.getByName("localhost"),5678);
        dc.bind(local);

        Charset charset=Charset.forName("GBK");
        CharsetDecoder decoder=charset.newDecoder();

        while (true){
            bB.clear();
            SocketAddress src=dc.receive(bB);
            if (src!=null){
                bB.flip();
                System.out.println("接收："+decoder.decode(bB).toString());
            }
            Thread.sleep(1000);
        }
    }
}
