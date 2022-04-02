import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Scanner;

public class SocketClient {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str;
        System.out.println("请输入用户名：");
        str = scanner.next();

        try {
            SocketAddress socketAddress = new InetSocketAddress("127.0.0.1", 9999);
            //创建Socket
            Socket socket = new Socket();
            //连接超时时间设置为10S  如果10秒内没连接上127.0.0.1:9999,系统就会提示"connect timed out"
            socket.connect(socketAddress, 10000);
            System.out.println("服务器已连接");
            DataOutputStream dataOutputStream=new DataOutputStream(socket.getOutputStream());
            dataOutputStream.writeUTF(str);
            dataOutputStream.close();

//            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
//            System.out.println(dataInputStream.readUTF());
//            dataInputStream.close();
            if (!socket.isClosed()) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
