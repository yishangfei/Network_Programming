import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.StandardCharsets;

public class NioDataClient {
    private Selector selector=null;
    private SocketChannel sc=null;

    private Charset charset=null;
    private CharsetDecoder decoder=null;

    public NioDataClient(){
        charset=Charset.forName("GBK");
        decoder=charset.newDecoder();
    }

    public void init()throws IOException{
        selector=Selector.open();
        sc=SocketChannel.open(new InetSocketAddress("localhost",5678));
        sc.configureBlocking(false);
        sc.register(selector, SelectionKey.OP_READ);
    }
    public void service() throws IOException{
        dowrite();
        doread();
    }
    private void dowrite(){
        Thread t=new NioThread(sc);
        t.start();
    }
    private void doread() throws IOException {
        System.out.println("read......");
        while (true){
            selector.select();
            Iterable it=selector.selectedKeys();
            while (it.iterator().hasNext()){
                SelectionKey skey= (SelectionKey) it.iterator().next();
                if (skey.isReadable()){
                    read(skey);
                }
                it.iterator().remove();
            }
        }
    }

    private void read(SelectionKey skey)throws  IOException{
        SocketChannel sc= (SocketChannel) skey.channel();
        ByteBuffer buffer=ByteBuffer.allocateDirect(100);
        int count=0;
        count=sc.read(buffer);
        sc.close();
        buffer.flip();
        System.out.println("Message-"+decoder.decode(buffer).toString());
        clearBuffer(buffer);
    }
    private void clearBuffer(ByteBuffer buffer){
        if (buffer!=null){
            buffer.clear();
            buffer=null;
        }
    }

    class NioThread extends Thread{
        private  SocketChannel sc=null;
        public NioThread(SocketChannel sc){
            this.sc=sc;
        }

        @Override
        public void run() {
            ByteBuffer buffer=ByteBuffer.allocateDirect(100);
            try {
                while (!Thread.currentThread().isInterrupted()){
                    buffer.clear();
                    BufferedReader in =new BufferedReader(new InputStreamReader(System.in));
                    String message=in.readLine();
                    if (message.equals("quit")){
                        System.exit(0);
                    }
                    buffer.put(message.getBytes(StandardCharsets.UTF_8));
                    buffer.flip();
                    sc.write(buffer);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                clearBuffer(buffer);
            }

        }
    }

    public static void main(String[] args) throws IOException {
        NioDataClient ncc=new NioDataClient();
        ncc.init();
        ncc.service();
    }
}
