import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NioDataServer {
    private Selector selector = null;
    private ServerSocketChannel ssc = null;
    private ServerSocket ss = null;

    public void init() throws IOException {
        selector = Selector.open();
        ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);

        InetSocketAddress is = new InetSocketAddress("localhost", 5678);
        ssc.bind(is);

        ssc.register(selector, SelectionKey.OP_ACCEPT);
    }

    public void service() throws IOException {
        System.out.println("服务启动.....");
        while (true) {
            selector.select();
            Iterable it = selector.selectedKeys();
            while (it.iterator().hasNext()) {
                SelectionKey skey = (SelectionKey) it.iterator().next();
                if (skey.isAcceptable()) {
                    doaccpect(skey);
                } else if (skey.isReadable()) {
                    doread(skey);
                }
                it.iterator().remove();
            }
        }
    }

    private void doaccpect(SelectionKey skey) throws IOException {
        System.out.println("Accept......");
        ServerSocketChannel ssc = (ServerSocketChannel) skey.channel();
        SocketChannel sc = ssc.accept();
        sc.configureBlocking(false);
        sc.register(selector, SelectionKey.OP_READ);
    }

    private void doread(SelectionKey skey) throws IOException {
        SocketChannel sc = (SocketChannel) skey.channel();
        ByteBuffer buffer = ByteBuffer.allocateDirect(100);
        int count = sc.read(buffer);
        System.out.println(sc.toString());
        datetimeservice(buffer, sc);
        sc.close();
        clearBuffer(buffer);
    }

    private void datetimeservice(ByteBuffer buffer, SocketChannel sca) throws IOException {
        buffer.flip();
        byte[] by = new byte[100];
        buffer.get(by, 0, buffer.limit());
        String msg = new String(by, 0, buffer.limit());
        System.out.println(msg);
        buffer.clear();
        if (msg.equalsIgnoreCase("data")) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
            buffer.put(("date:" + sdf.format(new Date())).getBytes());
        } else if (msg.equalsIgnoreCase("time")) {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            buffer.put(("time" + sdf.format(new Date())).getBytes(StandardCharsets.UTF_8));
        } else {
            buffer.put(msg.getBytes(StandardCharsets.UTF_8));
        }
        buffer.flip();
        sca.write(buffer);
        buffer.rewind();
    }

    private void clearBuffer(ByteBuffer buffer) {
        if (buffer != null) {
            buffer.clear();
            buffer = null;
        }
    }

    public static void main(String[] args) throws IOException {
            NioDataServer nioDataServer=new NioDataServer();
            nioDataServer.init();
            nioDataServer.service();
    }
}
