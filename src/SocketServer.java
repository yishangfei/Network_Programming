import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class SocketServer {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket();

            InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 9999);
            int queue = 100;
            serverSocket.bind(inetSocketAddress, queue);
            System.out.println("服务器开始运行");
            System.out.println(serverSocket.getInetAddress());
            System.out.println(serverSocket.getLocalPort());
            System.out.println(serverSocket.getLocalSocketAddress());

            while (true){
                new Client(serverSocket.accept()).start();
//                Socket sc=serverSocket.accept();
//                DataOutputStream dataOutputStream=new DataOutputStream(sc.getOutputStream());
//                dataOutputStream.writeUTF("现在时间："+new Date()+"。");
//                dataOutputStream.close();
////                serverSocket.close();/
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class Client extends Thread{
        private Socket socket;
        DataInputStream dataInputStream;

        public Client(Socket socket){
            this.socket=socket;
        }

        public void run(){
            try {
                dataInputStream=new DataInputStream(socket.getInputStream());
                System.out.println(dataInputStream.readUTF()+"加入！");
                dataInputStream.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
