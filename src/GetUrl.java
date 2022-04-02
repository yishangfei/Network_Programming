import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class GetUrl {
    public static void main(String[] args) {
        try {
            URL url = new URL("file:////C://Users//Administration//Desktop//a.txt");
            BufferedReader stream = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
            String c;
            while ((c = stream.readLine()) != null) {
                System.out.println(c);
            }
            stream.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
