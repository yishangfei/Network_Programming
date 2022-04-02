import java.net.InetAddress;
import java.net.UnknownHostException;

public class GetByName {
    public static void main(String[] args) {
        try {
            InetAddress inetAddress1 = InetAddress.getByName("127.0.0.1");
            InetAddress inetAddress2 = InetAddress.getByName("localhost");
            System.out.println(inetAddress1.getHostAddress());
            System.out.println(inetAddress2.getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
