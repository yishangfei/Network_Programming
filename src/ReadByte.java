import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class ReadByte {
    public static void main(String[] args) throws IOException {
        InputStream is =new FileInputStream("C:\\Users\\Administration\\Desktop\\a.txt");
        System.out.println("文件的长度是"+is.available());
        int b=is.read();
        System.out.println(b);
        while (b!=-1){
            b= is.read();
            System.out.println(b);
        }
        is.close();
    }
}
