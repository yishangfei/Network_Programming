import java.net.InetAddress;
import java.net.UnknownHostException;

public class GetLocalHost {
    public static void main(String[] args) {
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();

            System.out.println(inetAddress);
            System.out.println( inetAddress.getHostAddress());
            System.out.println( inetAddress.getHostName());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
