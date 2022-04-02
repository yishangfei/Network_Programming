import java.nio.ByteBuffer;


public class NIOBufferPut {

    public static void main(String[] args) {

        ByteBuffer byteBuffer=ByteBuffer.allocate(16);
        ByteBuffer byteBuffer1=ByteBuffer.allocate(4);
        byte[] bytes=new byte[]{1,2,3,4,'a','b','c','d'};
        byteBuffer.put(bytes);
        byteBuffer1.put(bytes,4,4);
        byteBuffer1.rewind();
        byteBuffer.put(byteBuffer1);
        byteBuffer.put((byte) 64);

        System.out.println(byteBuffer.toString());
        byteBuffer.rewind();

        while (byteBuffer.hasRemaining()){
            System.out.println("#"+byteBuffer.position()+":"+byteBuffer.get());
        }
    }
}
