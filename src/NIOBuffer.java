import java.nio.ByteBuffer;

public class NIOBuffer {
    public static void main(String[] args) {
        ByteBuffer byteBuffer=ByteBuffer.allocate(100);
        System.out.println(byteBuffer.toString());
        System.out.println(byteBuffer.position()+"==="+byteBuffer.limit()+"==="+byteBuffer.capacity());
    }
}
